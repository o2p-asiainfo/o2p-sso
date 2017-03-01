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

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.RoleDataBean;
import com.ailk.eaap.op2.sso.main.model.SvcRoleBean;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.SysRole;
import com.ailk.eaap.op2.sso.main.service.ISysAreaService;
import com.ailk.eaap.op2.sso.main.service.ISysCustomerService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.ailk.eaap.op2.sso.main.service.ISysRoleService;
import com.ailk.eaap.op2.sso.main.service.SysRoleService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 角色管理
 * @author zhaobl
 *
 */
public class SysRoleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private ISysRoleService sysRoleServ ;
	
	private List stateList = new ArrayList();
	private SysRole sysRole = new SysRole();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ;
	/**
	 * 跳转到角色管理页面
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
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示角色表
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
		sysLogOperate.setModelName("角色管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<SysRole> sysRoleList = new ArrayList<SysRole>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		Tenant tenant = (Tenant) session.getAttribute("Tenant");
		hashMap.put("tenantId", tenant.getTenantId());
		Map hashMap1 = new HashMap();
		String sysRoleName = getRequest().getParameter("sysRoleName");//角色名称
		if(sysRoleName != null)
			sysRoleName = sysRoleName.trim();
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "sysRoleName: "+sysRoleName +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(sysRoleName) || !StringUtil.isEmpty(stateId)){
			hashMap.put("sysRoleName", sysRoleName);
			hashMap.put("stateId", stateId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysRoleList = sysRoleServ.queryAllSysRole(hashMap);
			total = sysRoleServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysRoleList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysRoleList = sysRoleServ.queryAllSysRole(hashMap);
			total = sysRoleServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysRoleList));
		}
		return hashMap1;
	}
	/**
	 * 跳转到添加角色页面
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
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		return SUCCESS;
	}
	/**
	 * 判断新添加角色名称是否存在， 1存在
	 */
	public void isRoleExist(){
		Map map = new HashMap();
		String sysRoleName = getRequest().getParameter("sysRoleName");
		if(sysRoleName != null)
			sysRoleName = sysRoleName.trim();
		map.put("sysRoleName", sysRoleName);
		boolean flag = getSysRoleServ().isRoleExist(map);
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
	 * 添加角色
	 * @return
	 */
	public String addSysRole(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		Tenant tenant = (Tenant) session.getAttribute("Tenant");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("角色管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleAction");
		sysLogOperate.setMethodName("addSysRole");
		
		Map hashMap = new HashMap();
		String sysRoleName = getRequest().getParameter("sysRoleName");//角色名称
		if(sysRoleName != null)
			sysRoleName = sysRoleName.trim();
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "sysRoleName: "+sysRoleName +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		sysLogOperate.setTenantId(tenant.getTenantId());
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("sysRoleName", sysRoleName);
		hashMap.put("stateId", stateId);
		hashMap.put("tenantId", tenant.getTenantId());
		getSysRoleServ().insertSysRole(hashMap);
		return SUCCESS;
	}
	/**
	 * 删除选中角色
	 */
	public void deleteSysRole(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("角色管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleAction");
		sysLogOperate.setMethodName("deleteSysRole");
		
		String sysRoleId = getRequest().getParameter("sysRoleId");
		
		//操作日志参数
		String param = "sysRoleId: "+sysRoleId  ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		String stringJson;
		if(sysRoleId != null && !sysRoleId.equals("")){
			int flag = getSysRoleServ().deleteSysRole(sysRoleId);
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
	 * 跳转到修改角色页面
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
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		String sysRoleId = getRequest().getParameter("sysRoleId");
		sysRole = getSysRoleServ().querySysRoleById(sysRoleId);
		return SUCCESS;
	}
	/**
	 * 判断新修改角色名称是否存在， 1存在
	 */
	public void isUpdateRoleExist(){
		Map map = new HashMap();
		String sysRoleId = getRequest().getParameter("sysRoleId");
		String sysRoleName = getRequest().getParameter("sysRoleName");
		if(sysRoleName != null)
			sysRoleName = sysRoleName.trim();
		map.put("sysRoleId", sysRoleId);
		map.put("sysRoleName", sysRoleName);
		boolean flag = getSysRoleServ().isUpdateRoleExist(map);
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
	 * 修改选中角色信息
	 * @return
	 */
	public String updateSysRole(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("角色管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleAction");
		sysLogOperate.setMethodName("updateSysRole");
		
		Map hashMap = new HashMap();
		String sysRoleId = getRequest().getParameter("sysRoleId");
		String sysRoleName = getRequest().getParameter("sysRoleName");//角色名称
		if(sysRoleName != null)
			sysRoleName = sysRoleName.trim();
		String shownum = getRequest().getParameter("shownum");//排序号
		if(shownum != null)
			shownum = shownum.trim();
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "sysRoleId: "+sysRoleId +"sysRoleName: "+sysRoleName +",shownum: "+shownum+",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("sysRoleName", sysRoleName);
		hashMap.put("shownum", shownum);
		hashMap.put("stateId", stateId);
		hashMap.put("sysRoleId", sysRoleId);
		if(sysRoleId != null && !sysRoleId.equals(""))
			getSysRoleServ().updateSysRole(hashMap);
		return SUCCESS;
	}
	
	

	public ISysRoleService getSysRoleServ() {
		sysRoleServ = (ISysRoleService)SpringBeanInvoker.getBean("eaap-op2-sso-sysRoleServ");
		return sysRoleServ;
	}

	public void setSysRoleServ(ISysRoleService sysRoleServ) {
		this.sysRoleServ = sysRoleServ;
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

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
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
//	private SysRoleService sysRoleService;
//	
//	private Map<String,Object> paramMap;
//	
//	private String roleName;
//	private String roleType;
//	private String roleState;
//	private String customerId;//客户编码
//	
//	private String editRoleId;
//	private String editType;
//	
//	private String currPage;
//	private String pageNum;
//	
//	private String[] roleId;
//	private String checkedId;
//	
//	private String jsonResult;
//	
//	public void searchRoleList(){
//		StringBuffer stringJson = new StringBuffer();
//		stringJson.append("[");
//		if(pageNum.equals("10")){
//			if(currPage.equals("1")){
//				stringJson.append("{\"roleName\":\"SFM文件交换管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}," +
//						"{\"roleName\":\"省份查询\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"容灾管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"携号转网管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"容灾管理员内部\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"一点收费稽核管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"稽核管理\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"枢纽平台管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"枢纽稽核管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//						"{\"roleName\":\"一点收费\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}");
//			}else{
//				stringJson.append("{\"roleName\":\"系统管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}," +
//					"{\"roleName\":\"电信稽核管理\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"测试角色\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"EOP测试\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"API操作员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}");
//			}
//		}else{
//			stringJson.append("{\"roleName\":\"SFM文件交换管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}," +
//					"{\"roleName\":\"省份查询\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"容灾管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"携号转网管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"容灾管理员内部\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"一点收费稽核管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"稽核管理\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"枢纽平台管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"枢纽稽核管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"一点收费\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"系统管理员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}," +
//					"{\"roleName\":\"电信稽核管理\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"测试角色\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"EOP测试\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"},"+
//					"{\"roleName\":\"API操作员\",\"status\":\"正常\",\"synStatus\":\"\",\"synTime\":\"\",\"synNum\":\"\",\"personSyn\":\"手工同步\"}");
//		}
//		stringJson.append("]");
//		try {
//			writeString(stringJson.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		SvcRoleBean bean = new SvcRoleBean();
////		bean.setRoleName(roleName);
////		bean.setRoleState(roleState);
////		//bean.setRoleType(roleType);
////		PageBean pageBean = new PageBean();
////		if(currPage!=null){
////			pageBean.setCurrentPage(currPage);
////		}
////		if(pageNum!=null){
////			pageBean.setPageCount(pageNum);
////		}
////		bean.setPageBean(pageBean);
////		sysRoleService.doSearchRoleList(bean);
////		paramMap = new HashMap<String, Object>();
////		paramMap.put("pageBean", bean.getPageBean());
////		paramMap.put("searchResult",bean.getSearchResult());
////		return SUCCESS;
//	}
//	public String gotoEditRole(){
//		if("edit".equals(editType)){
//			RoleDataBean role = sysRoleService.doSearchEditRole(editRoleId);
//			roleName = role.getSysRoleNm();
//			//roleState = role.getSysStatusId();
//			//roleType = role.getSysRoleTypeId();
//		}		
//		return SUCCESS;
//	}
//	public String editRole(){
//		SvcRoleBean bean = new SvcRoleBean();
//		bean.setEditRoleId(editRoleId);
//		bean.setEditType(editType);
//		bean.setRoleName(roleName);
//		bean.setRoleState(roleState);
//		//bean.setRoleType(roleType);
//		PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		bean.setPageBean(pageBean);
//		String errorMsg = sysRoleService.doEditRole(bean);
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("pageBean", bean.getPageBean());
//		paramMap.put("searchResult",bean.getSearchResult());
////		saveErrorMsg(errorMsg);
//		return SUCCESS;
//	}
//	public String deleteRole(){
////		AuthUtils au = new AuthUtils();
//		String [] ids = ServletActionContext.getRequest().getParameterValues("roleId");
//		
//		if(ids.length>0)
//		{
//			for(int i=0;i<ids.length;i++)
//			{
//				sysRoleService.del(Long.valueOf(ids[i]));
//				//au.delrole(Integer.parseInt(ids[i]));
//			}
//		}
//		
//		
//		
//		
//		return SUCCESS;
//		
//		/*String [] ids = ServletActionContext.getRequest().getParameterValues("checkbox");
//		if(ids.length>0)
//		{
//			for(int i=0;i<ids.length;i++)
//			{
//				int num1 = sysCustomerService.checkDept(Long.valueOf(ids[i]));
//				int num2 = sysCustomerService.checkRole(Long.valueOf(ids[i]));
//				if(num1 == 0 && num2 == 0){
//					sysCustomerService.del(Long.valueOf(ids[i]));
//				}
//				paramMap = new HashMap<String, Object>();
//				paramMap.put("num1", num1);
//				paramMap.put("num2", num2);
//			}
//		}
//		return "success";*/
//	}
//	
//	//手工同步方法
//	public String synStatusChange(){
//		String roleId = getRequest().getParameter("roleId");
//		sysRoleService.synStatusChange(roleId);
//		return SUCCESS;
//	}
//	
//	public String loadSelect(){
//		//jsonResult = "{user:[{name:'aaa',sex:'man'},{name:'bbb',sex:'woman'}]}";		
//		jsonResult = sysRoleService.doLoadSelect();
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
//	//12.28 加载客户下 所属的 所有角色//
//	public String loadPreAuthRole(){
//		String customerId = getRequest().getParameter("customerId");
//		jsonResult = sysRoleService.doloadPreAuthRole(customerId);
//		try{ 
//			getResponse().setContentType("text/plain;charset=UTF-8");
//	        getResponse().setCharacterEncoding("UTF-8");   
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
//	public String loadPreOperRole(){
//		String customerId = getRequest().getParameter("customerId");
//		jsonResult = sysRoleService.doloadPreOperRole(customerId);
//		try{ 
//			getResponse().setContentType("text/plain;charset=UTF-8");
//	        getResponse().setCharacterEncoding("UTF-8");   
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
//	public String getRoleName() {
//		return roleName;
//	}
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//	public String getRoleType() {
//		return roleType;
//	}
//	public void setRoleType(String roleType) {
//		this.roleType = roleType;
//	}
//	public String getRoleState() {
//		return roleState;
//	}
//	public void setRoleState(String roleState) {
//		this.roleState = roleState;
//	}
//	public String getEditRoleId() {
//		return editRoleId;
//	}
//	public void setEditRoleId(String editRoleId) {
//		this.editRoleId = editRoleId;
//	}
//	public String getEditType() {
//		return editType;
//	}
//	public void setEditType(String editType) {
//		this.editType = editType;
//	}
//	public String[] getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(String[] roleId) {
//		this.roleId = roleId;
//	}
//	public void setSysRoleService(SysRoleService sysRoleService) {
//		this.sysRoleService = sysRoleService;
//	}
//	public String getCheckedId() {
//		return checkedId;
//	}
//	public void setCheckedId(String checkedId) {
//		this.checkedId = checkedId;
//	}
//	public String getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}

	
}
