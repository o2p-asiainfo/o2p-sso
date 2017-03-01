package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.DeptDataBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.model.SvcDeptBean;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.SysRole;
import com.ailk.eaap.op2.sso.main.service.ISysCustomerService;
import com.ailk.eaap.op2.sso.main.service.ISysDeptService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.ailk.eaap.op2.sso.main.service.SysCustomerService;
import com.ailk.eaap.op2.sso.main.service.SysDeptService;
import com.ailk.eaap.op2.sso.main.service.SysRoleService;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 部门管理
 * @author zhaobl
 *
 */
public class SysDeptAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISysDeptService sysDeptServ;
	private ISysCustomerService sysCustomerServ ;
	
	private List stateList = new ArrayList();
	private List customerList = new ArrayList();
	private List parentDeptList = new ArrayList();
	private SysDept sysDept = new SysDept();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ; 
	/**
	 * 跳转到部门页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		List<SysCustomer>  sysCustomerList = getSysCustomerServ().queryAll();
		for(int i=0; i<sysCustomerList.size(); i++){
			Map map = new HashMap();
			SysCustomer sysCustomer = sysCustomerList.get(i);
			if(sysCustomer.getSysCustomerName() == null || sysCustomer.getSysCustomerName().equals(""))
				continue;
			map.put("sysCustomerId", sysCustomer.getSysCustomerId());
			map.put("sysCustomerName", sysCustomer.getSysCustomerName());
			customerList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示部门表
	 * @param para
	 * @return
	 */
	public Map showGrid(Map para){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("部门管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysDeptAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<SysDept> sysDeptList = new ArrayList<SysDept>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		String deptName = getRequest().getParameter("deptName");//部门名称
		if(deptName != null)
			deptName = deptName.trim();
		String sysCustomerNameId = getRequest().getParameter("sysCustomerName");//客户ID
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "deptName: "+deptName +",sysCustomerNameId: "+sysCustomerNameId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(deptName) || !StringUtil.isEmpty(sysCustomerNameId) || !StringUtil.isEmpty(stateId)){
			hashMap.put("deptName", deptName);
			hashMap.put("sysCustomerNameId", sysCustomerNameId);
			hashMap.put("stateId", stateId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysDeptList = sysDeptServ.queryAllSysDept(hashMap);
			total = sysDeptServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysDeptList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysDeptList = sysDeptServ.queryAllSysDept(hashMap);
			total = sysDeptServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysDeptList));
		}
		return hashMap;
	}
	/**
	 * 跳转到添加部门页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "废除");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		List<SysCustomer>  sysCustomerList = getSysCustomerServ().queryAll();
		for(int i=0; i<sysCustomerList.size(); i++){
			Map map = new HashMap();
			SysCustomer sysCustomer = sysCustomerList.get(i);
			map.put("sysCustomerId", sysCustomer.getSysCustomerId());
			map.put("sysCustomerName", sysCustomer.getSysCustomerName());
			customerList.add(map);
		}

		List<SysDept> sysDeptList = getSysDeptServ().queryAll();
		for(int i=0; i<sysDeptList.size(); i++){
			Map map = new HashMap();
			SysDept sysDept = sysDeptList.get(i);
			if(sysDept.getDeptName() == null || sysDept.getDeptName().equals(""))
				continue;
			map.put("parentDeptId", sysDept.getDeptId());
			map.put("parentDeptName", sysDept.getDeptName());
			parentDeptList.add(map);
		}
		return SUCCESS;
	}
	/**
	 * 判断新添加部门名称是否存在， 1存在
	 */
	public void isDeptExist(){
		Map map = new HashMap();
		String deptName = getRequest().getParameter("deptName");
		if(deptName != null)
			deptName = deptName.trim();
		map.put("deptName", deptName);
		boolean flag = getSysDeptServ().isDeptExist(map);
		String stringJson;
		if(flag){
			stringJson ="{\"result\":\"1\"}";
		}else{
			stringJson ="{\"result\":\"0\"}";
		}	
		try {
			writeString(stringJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 添加部门
	 * @return
	 */
	public String addSysDept(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("部门管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysDeptAction");
		sysLogOperate.setMethodName("addSysDept");
		
		Map hashMap = new HashMap();
		String deptName = getRequest().getParameter("deptName");//部门名称
		if(deptName != null)
			deptName = deptName.trim();
		String sysCustomerNameId = getRequest().getParameter("sysCustomerName");//客户ID 
		String parentDeptNameId = getRequest().getParameter("parentDeptName"); //父部门ID
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "deptName: "+deptName +",sysCustomerNameId: "+sysCustomerNameId +",parentDeptNameId: "+parentDeptNameId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("deptName", deptName);
		hashMap.put("sysCustomerNameId", sysCustomerNameId);
		hashMap.put("parentDeptNameId", parentDeptNameId);
		hashMap.put("stateId", stateId);
		getSysDeptServ().insertSysDept(hashMap);
		return SUCCESS;
	}
	/**
	 * 删除选中部门
	 */
	public void deleteSysDept(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("部门管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysDeptAction");
		sysLogOperate.setMethodName("deleteSysDept");
		
		String deptId = getRequest().getParameter("deptId");
		
		//操作日志参数
		String param = "deptId: "+deptId  ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		String stringJson;
		if(deptId != null && !deptId.equals("")){
			int flag = getSysDeptServ().deleteSysDept(deptId);
			if(flag == 0){
				stringJson ="{\"result\":\"0\"}";
			}else{
				stringJson ="{\"result\":\"1\"}";
			}	
			try {
				writeString(stringJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 跳转到修改部门页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preUpdate(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "废除");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		List<SysCustomer>  sysCustomerList = getSysCustomerServ().queryAll();
		for(int i=0; i<sysCustomerList.size(); i++){
			Map map = new HashMap();
			SysCustomer sysCustomer = sysCustomerList.get(i);
			map.put("sysCustomerId", sysCustomer.getSysCustomerId());
			map.put("sysCustomerName", sysCustomer.getSysCustomerName());
			customerList.add(map);
		}

		List<SysDept> sysDeptList = getSysDeptServ().queryAll();
		for(int i=0; i<sysDeptList.size(); i++){
			Map map = new HashMap();
			SysDept sysDept = sysDeptList.get(i);
			if(sysDept.getDeptName() == null || sysDept.getDeptName().equals(""))
				continue;
			map.put("parentDeptId", sysDept.getDeptId());
			map.put("parentDeptName", sysDept.getDeptName());
			parentDeptList.add(map);
		}
		
		String deptId = getRequest().getParameter("deptId");
		sysDept = getSysDeptServ().querySysDeptById(deptId);
		return SUCCESS;
	}
	/**
	 * 判断新修改部门名称是否存在， 1存在
	 */
	public void isUpdateDeptExist(){
		Map map = new HashMap();
		String deptId = getRequest().getParameter("deptId");
		String deptName = getRequest().getParameter("deptName");
		if(deptName != null)
			deptName = deptName.trim();
		map.put("deptId", deptId);
		map.put("deptName", deptName);
		boolean flag = getSysDeptServ().isUpdateDeptExist(map);
		String stringJson;
		if(flag){
			stringJson ="{\"result\":\"1\"}";
		}else{
			stringJson ="{\"result\":\"0\"}";
		}	
		try {
			writeString(stringJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 修改选中部门信息
	 * @return
	 */
	public String updateSysDept(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("部门管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysDeptAction");
		sysLogOperate.setMethodName("updateSysDept");
		
		Map hashMap = new HashMap();
		String deptId = getRequest().getParameter("deptId");
		String deptName = getRequest().getParameter("deptName");//部门名称
		if(deptName != null)
			deptName = deptName.trim();
		String sysCustomerNameId = getRequest().getParameter("sysCustomerName");//客户ID 
		String parentDeptNameId = getRequest().getParameter("parentDeptName"); //父部门ID
		if(parentDeptNameId.equals(""))
			parentDeptNameId = null;//mysql int类型字段插入空字符串时自动转为0
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "deptName: "+deptName +",sysCustomerNameId: "+sysCustomerNameId +",parentDeptNameId: "+parentDeptNameId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("deptId", deptId);
		hashMap.put("deptName", deptName);
		hashMap.put("sysCustomerNameId", sysCustomerNameId);
		hashMap.put("parentDeptNameId", parentDeptNameId);
		hashMap.put("stateId", stateId);
		if(deptId != null && !deptId.equals(""))
			getSysDeptServ().updateSysDept(hashMap);
		return SUCCESS;
	}
	
	
	public ISysDeptService getSysDeptServ() {
		sysDeptServ = (ISysDeptService)SpringBeanInvoker.getBean("eaap-op2-sso-sysDeptServ");
		return sysDeptServ;
	}

	public void setSysDeptServ(ISysDeptService sysDeptServ) {
		this.sysDeptServ = sysDeptServ;
	}

	public ISysCustomerService getSysCustomerServ() {
		sysCustomerServ = (ISysCustomerService)SpringBeanInvoker.getBean("eaap-op2-sso-sysCustomerServ");
		return sysCustomerServ;
	}

	public void setSysCustomerServ(ISysCustomerService sysCustomerServ) {
		this.sysCustomerServ = sysCustomerServ;
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}

	public List getStateList() {
		return stateList;
	}

	public void setStateList(List stateList) {
		this.stateList = stateList;
	}

	public List getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}

	public List getParentDeptList() {
		return parentDeptList;
	}

	public void setParentDeptList(List parentDeptList) {
		this.parentDeptList = parentDeptList;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
//	private static final long serialVersionUID = 1L;
//	
//	private SysDeptService sysDeptService;
//	private SysRoleService sysRoleService;
//	private SysCustomerService sysCustomerService;
//	private Map<String,Object> paramMap;
//	
//	private String customerId;
//	private String deptName;
//	private String compId;
//	private String preDept;
//	private String deptState;
//	
//	private String editDeptId;
//	private String editType;
//	
//	public List<SysDept> deptList ;
//	public List<SysRole> roleList ;
//	public List<SysCustomer> customerList;
//	private String currPage;
//	private String pageNum;
//	
//	private String[] deptId;
//	private String checkedId;
//	
//	private String jsonResult;
//	
//	public void searchDeptList(){
//		StringBuffer stringJson = new StringBuffer();
//		stringJson.append("[");
//		if(pageNum.equals("10")){
//			if(currPage.equals("1")){
//				stringJson.append("{\"deptId\":\"1\",\"deptName\":\"电信运营中心\",\"company\":\"电信集团\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//						"{\"deptId\":\"100\",\"deptName\":\"EOP系统组\",\"company\":\"亚信联创\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//						"{\"deptId\":\"10001\",\"deptName\":\"龙计划平台\",\"company\":\"广东CRM\",\"hight\":\"\",\"deptStatus\":\"正常\"}");
//			}else{
//				stringJson.append("{\"deptId\":\"1\",\"deptName\":\"电信运营中心\",\"company\":\"电信集团\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//						"{\"deptId\":\"100\",\"deptName\":\"EOP系统组\",\"company\":\"亚信联创\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//						"{\"deptId\":\"10001\",\"deptName\":\"龙计划平台\",\"company\":\"广东CRM\",\"hight\":\"\",\"deptStatus\":\"正常\"}");
//			}
//		}else{
//			stringJson.append("{\"deptId\":\"1\",\"deptName\":\"电信运营中心\",\"company\":\"电信集团\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//					"{\"deptId\":\"100\",\"deptName\":\"EOP系统组\",\"company\":\"亚信联创\",\"hight\":\"\",\"deptStatus\":\"正常\"}," +
//					"{\"deptId\":\"10001\",\"deptName\":\"龙计划平台\",\"company\":\"广东CRM\",\"hight\":\"\",\"deptStatus\":\"正常\"}");
//		}
//		stringJson.append("]");
//		try {
//			writeString(stringJson.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		SvcDeptBean bean = new SvcDeptBean();
////		bean.setCompId(compId);
////		bean.setDeptName(deptName);
////		bean.setDeptState(deptState);
////		bean.setPreDept(preDept);
////		PageBean pageBean = new PageBean();
////		if(currPage!=null){
////			pageBean.setCurrentPage(currPage);
////		}
////		if(pageNum!=null){
////			pageBean.setPageCount(pageNum);
////		}
////		bean.setPageBean(pageBean);
////		//增加数据域判断条件 根据session 里取出的 SysIdTypeId、CustomerName进行判断
////		SYSPERSON person  = (SYSPERSON)session.get("userbean");
////		String idType = person.getSysIdTypeId();
////		String cusName = person.getCustomerName();
////		sysDeptService.doSearchDeptList(bean,idType,cusName);
////		//sysDeptService.doSearchDeptList(bean);
////		paramMap = new HashMap<String, Object>();
////		paramMap.put("pageBean", bean.getPageBean());
////		paramMap.put("searchResult",bean.getSearchResult());
////		return SUCCESS;
//	}
//	public String gotoEditDept(){
//		if("edit".equals(editType)){
//			DeptDataBean dept = sysDeptService.doSearchEditDept(editDeptId);
//			compId = dept.getCustomerId();
//			preDept = dept.getParentDeptId();
//			deptState = dept.getStatusId();
//			deptName = dept.getDeptName();
//		}		
//		return SUCCESS;
//	}
//	public String editDept(){
//		SvcDeptBean bean = new SvcDeptBean();
//		bean.setEditDeptId(editDeptId);
//		bean.setEditType(editType);
//		bean.setCompId(compId);
//		bean.setDeptName(deptName);
//		bean.setDeptState(deptState);
//		bean.setPreDept(preDept);
//		PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		bean.setPageBean(pageBean);
//		String errorMsg = sysDeptService.doEditDept(bean);
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("pageBean", bean.getPageBean());
//		paramMap.put("searchResult",bean.getSearchResult());
////		saveErrorMsg(errorMsg);
//		return SUCCESS;
//	}
//	public String deleteDept(){
//		SvcDeptBean bean = new SvcDeptBean();
//		bean.setCompId(compId);
//		bean.setDeptName(deptName);
//		bean.setDeptState(deptState);
//		bean.setPreDept(preDept);
//		bean.setDeptId(deptId);
//		bean.setCheckedId(checkedId);
//		PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		bean.setPageBean(pageBean);
//		String errorMsg = sysDeptService.doDeleteDept(bean);
//		checkedId = bean.getCheckedId();
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("pageBean", bean.getPageBean());
//		paramMap.put("searchResult",bean.getSearchResult());
////		saveErrorMsg(errorMsg);
//		return SUCCESS;
//	}
//	//加载 所有客户//
//	public String loadSelect(){
//		//3-16 加 数据域 条件 加载用户信息方法
//		SYSPERSON person  = (SYSPERSON)session.get("userbean");
//		String idType = person.getSysIdTypeId();
//		String cusName = person.getCustomerName();
//		String cusId = person.getSysCustomerId();
//		jsonResult =  null;
//		if((idType.equals("1"))||(idType.equals("2") && cusName.equals("电信集团"))){
//			jsonResult = sysDeptService.doLoadSelect();
//		}
//		//if(idType.equals("2") && !cusName.equals("电信集团"))
//		else{
//			jsonResult = sysDeptService.doLoadSelect(Long.valueOf(cusId));
//		}
//		//jsonResult = sysDeptService.doLoadSelect();
//		try{ 
//			getResponse().setContentType("text/plain;charset=UTF-8");
//	        getResponse().setCharacterEncoding("UTF-8");//汉字编码   
//	        PrintWriter out = getResponse().getWriter();  
//	        out.print(jsonResult);  
//	        out.flush();
//		}catch(Exception ex){ 
//			return NONE;
//		} finally{
//			
//		}
//		return NONE;
//	}
//	
//	public String deptList()
//	{
//		String customerId = getRequest().getParameter("customerId");
//		customerList = sysCustomerService.getCustomers();
//		deptList = sysDeptService.deptList(customerId);
//		List<Map<String,Object>> result = sysRoleService.getAuthedRoleByCustAndRoleTypeId(Long.valueOf(customerId));
//		roleList = new ArrayList();
//		for(Map<String,Object> item : result)
//		{
//			SysRole bean = new SysRole();
//			bean.setSysRoleId(Long.valueOf(ConvertUtil.nullToSpace(item.get("SYS_ROLE_ID"))));
//			bean.setSysRoleName(ConvertUtil.nullToSpace(item.get("SYS_ROLE_NAME")));
//			roleList.add(bean);
//		}
//		
//		ServletActionContext.getRequest().setAttribute("customerList", customerList);
//		ServletActionContext.getRequest().setAttribute("deptList",deptList);
//		ServletActionContext.getRequest().setAttribute("roleList",roleList);
//		ServletActionContext.getRequest().setAttribute("customerId",customerId);
//		return "success";
//	}
//	//根据客户加载部门
//	public String loadPreDept(){
//		String customerId = getRequest().getParameter("customerId");
//		jsonResult = sysDeptService.doloadPreDept(customerId);
//		try{ 
//			getResponse().setContentType("text/plain;charset=UTF-8");
//	        getResponse().setCharacterEncoding("UTF-8");//汉字编码   
//	        PrintWriter out = getResponse().getWriter();  
//	        out.print(jsonResult);  
//	        out.flush();
//		}catch(Exception ex){ 
//			return NONE;
//		} finally{
//			
//		}
//		return NONE;
//	}
//	//1-14 加载其他部门
//	public String loadOtherDept(){
//		String customerId = getRequest().getParameter("customerId");
//		String deptId = getRequest().getParameter("deptId");
//		String sysPersonId = getRequest().getParameter("sysPersonId");
//		jsonResult = sysDeptService.doloadOtherDept(customerId,deptId,sysPersonId);
//		try{ 
//			getResponse().setContentType("text/plain;charset=UTF-8");
//	        getResponse().setCharacterEncoding("UTF-8");//汉字编码   
//	        PrintWriter out = getResponse().getWriter();  
//	        out.print(jsonResult);  
//	        out.flush();
//		}catch(Exception ex){ 
//			return NONE;
//		} finally{
//			
//		}
//		return NONE;
//	}
//	
//	public String gotoCountFlow(){
//		return SUCCESS;
//	}
//
//	public Map<String, Object> getParamMap() {
//		return paramMap;
//	}
//
//	public void setParamMap(Map<String, Object> paramMap) {
//		this.paramMap = paramMap;
//	}
//
//	
//
//	public String getJsonResult() {
//		return jsonResult;
//	}
//
//	public void setJsonResult(String jsonResult) {
//		this.jsonResult = jsonResult;
//	}
//
//	public String getCurrPage() {
//		return currPage;
//	}
//
//	public void setCurrPage(String currPage) {
//		this.currPage = currPage;
//	}
//
//	public String getPageNum() {
//		return pageNum;
//	}
//
//	public void setPageNum(String pageNum) {
//		this.pageNum = pageNum;
//	}
//
//	public void setSysDeptService(SysDeptService sysDeptService) {
//		this.sysDeptService = sysDeptService;
//	}
//
//	public String getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
//
//	public String getDeptName() {
//		return deptName;
//	}
//
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
//	}
//
//	public String getCompId() {
//		return compId;
//	}
//
//	public void setCompId(String compId) {
//		this.compId = compId;
//	}
//
//	public String getPreDept() {
//		return preDept;
//	}
//
//	public void setPreDept(String preDept) {
//		this.preDept = preDept;
//	}
//
//	public String getDeptState() {
//		return deptState;
//	}
//
//	public void setDeptState(String deptState) {
//		this.deptState = deptState;
//	}
//	public String[] getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(String[] deptId) {
//		this.deptId = deptId;
//	}
//	public String getEditType() {
//		return editType;
//	}
//	public void setEditType(String editType) {
//		this.editType = editType;
//	}
//	public String getEditDeptId() {
//		return editDeptId;
//	}
//	public void setEditDeptId(String editDeptId) {
//		this.editDeptId = editDeptId;
//	}
//	public String getCheckedId() {
//		return checkedId;
//	}
//	public void setCheckedId(String checkedId) {
//		this.checkedId = checkedId;
//	}
//	public static long getSerialVersionUID() {
//		return serialVersionUID;
//	}
//	public List<SysDept> getDeptList() {
//		return deptList;
//	}
//	public void setDeptList(List<SysDept> deptList) {
//		this.deptList = deptList;
//	}
//	public SysRoleService getSysRoleService() {
//		return sysRoleService;
//	}
//	public void setSysRoleService(SysRoleService sysRoleService) {
//		this.sysRoleService = sysRoleService;
//	}
//	public List<SysRole> getRoleList() {
//		return roleList;
//	}
//	public void setRoleList(List<SysRole> roleList) {
//		this.roleList = roleList;
//	}
//	public SysDeptService getSysDeptService() {
//		return sysDeptService;
//	}
//	public SysCustomerService getSysCustomerService() {
//		return sysCustomerService;
//	}
//	public void setSysCustomerService(SysCustomerService sysCustomerService) {
//		this.sysCustomerService = sysCustomerService;
//	}
//	public List<SysCustomer> getCustomerList() {
//		return customerList;
//	}
//	public void setCustomerList(List<SysCustomer> customerList) {
//		this.customerList = customerList;
//	}
	
}
