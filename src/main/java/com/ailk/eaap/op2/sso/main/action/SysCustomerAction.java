package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
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
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysCardType;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysCustomerBean;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.service.ISysAreaService;
import com.ailk.eaap.op2.sso.main.service.ISysCustomerService;
import com.ailk.eaap.op2.sso.main.service.ISysDeptService;
import com.ailk.eaap.op2.sso.main.service.ISysLogLoginService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.ailk.eaap.op2.sso.main.service.SysCardTypeService;
import com.ailk.eaap.op2.sso.main.service.SysCustomerRoleService;
import com.ailk.eaap.op2.sso.main.service.SysCustomerService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 客户管理
 * @author zhaobl
 *
 */
public class SysCustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ISysCustomerService sysCustomerServ ;
	private ISysAreaService sysAreaServ ;
	
	private List stateList = new ArrayList();
	private List areaList = new ArrayList();
	private SysCustomer sysCustomer = new SysCustomer();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ; 
	
	/**
	 * 跳转到客户页面
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
		
		List<SysArea>  sysAreaList = getSysAreaServ().queryAll();
		for(int i=0; i<sysAreaList.size(); i++){
			Map map = new HashMap();
			SysArea sysArea = sysAreaList.get(i);
			if(sysArea.getSysAreaName() == null || sysArea.getSysAreaName().equals(""))
				continue;
			map.put("sysAreaId", sysArea.getSysAreaId());
			map.put("sysAreaName", sysArea.getSysAreaName());
			areaList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示客户表
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
		sysLogOperate.setModelName("客户管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerAction");
		sysLogOperate.setMethodName("showGrid");
		
		
		List<SysCustomer> sysCustomerList = new ArrayList<SysCustomer>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		Map hashMap1 = new HashMap();
		String sysCustomerName = getRequest().getParameter("sysCustomerName");//客户名称
		if(sysCustomerName != null)
			sysCustomerName = sysCustomerName.trim();
		String sysAreaId = getRequest().getParameter("sysAreaName");//区域ID
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "sysCustomerName: "+sysCustomerName +",sysAreaId: "+sysAreaId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(sysCustomerName) || !StringUtil.isEmpty(sysAreaId) || !StringUtil.isEmpty(stateId)){
			hashMap.put("sysCustomerName", sysCustomerName);
			hashMap.put("sysAreaId", sysAreaId);
			hashMap.put("stateId", stateId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysCustomerList = sysCustomerServ.queryAllSysCustomer(hashMap);
			total = sysCustomerServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysCustomerList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysCustomerList = sysCustomerServ.queryAllSysCustomer(hashMap);
			total = sysCustomerServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysCustomerList));
		}
		return hashMap1;
	}
	/**
	 * 跳转到添加客户页面
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
		
		List<SysArea>  sysAreaList = getSysAreaServ().queryAll();
		for(int i=0; i<sysAreaList.size(); i++){
			Map map = new HashMap();
			SysArea sysArea = sysAreaList.get(i);
			if(sysArea.getSysAreaName() == null || sysArea.getSysAreaName().equals(""))
				continue;
			map.put("sysAreaId", sysArea.getSysAreaId());
			map.put("sysAreaName", sysArea.getSysAreaName());
			areaList.add(map);
		}
		
		return SUCCESS;
	}
	/**
	 * 判断新添加客户名称是否存在， 1存在
	 */
	public void isCustomerExist(){
		Map map = new HashMap();
		String sysCustomerName = getRequest().getParameter("sysCustomerName");
		if(sysCustomerName != null)
			sysCustomerName = sysCustomerName.trim();
		map.put("sysCustomerName", sysCustomerName);
		boolean flag = getSysCustomerServ().isCustomerExist(map);
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
	 * 添加客户
	 * @return
	 */
	public String addSysCustomer(){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("客户管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerAction");
		sysLogOperate.setMethodName("addSysCustomer");
		
		Map hashMap = new HashMap();
		String sysCustomerName = getRequest().getParameter("sysCustomerName");//客户名称
		if(sysCustomerName != null)
			sysCustomerName = sysCustomerName.trim();
		String sysAreaId = getRequest().getParameter("sysAreaName");//区域ID
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "sysCustomerName: "+sysCustomerName +",sysAreaId: "+sysAreaId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("sysCustomerName", sysCustomerName);
		hashMap.put("sysAreaId", sysAreaId);
		hashMap.put("stateId", stateId);
		getSysCustomerServ().insertSysCustomer(hashMap);
		return SUCCESS;
	}
	/**
	 * 删除选中客户
	 */
	public void deleteSysCustomer(){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("客户管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerAction");
		sysLogOperate.setMethodName("deleteSysCustomer");
		
		String sysCustomerId = getRequest().getParameter("sysCustomerId");
		
		//操作日志参数
		String param = "sysCustomerId: "+sysCustomerId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		String stringJson;
		if(sysCustomerId != null && !sysCustomerId.equals("")){
			int flag = getSysCustomerServ().deleteSysCustomer(sysCustomerId);
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
	 * 跳转到修改客户页面
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
		
		List<SysArea>  sysAreaList = getSysAreaServ().queryAll();
		for(int i=0; i<sysAreaList.size(); i++){
			Map map = new HashMap();
			SysArea sysArea = sysAreaList.get(i);
			if(sysArea.getSysAreaName() == null || sysArea.getSysAreaName().equals(""))
				continue;
			map.put("sysAreaId", sysArea.getSysAreaId());
			map.put("sysAreaName", sysArea.getSysAreaName());
			areaList.add(map);
		}
		
		String sysCustomerId = getRequest().getParameter("sysCustomerId");
		sysCustomer = getSysCustomerServ().querySysCustomerById(sysCustomerId);
		return SUCCESS;
	}
	/**
	 * 判断新修改客户名称是否存在， 1存在
	 */
	public void isUpdateCustomerExist(){
		Map map = new HashMap();
		String sysCustomerId = getRequest().getParameter("sysCustomerId");
		String sysCustomerName = getRequest().getParameter("sysCustomerName");
		if(sysCustomerName != null)
			sysCustomerName = sysCustomerName.trim();
		map.put("sysCustomerId", sysCustomerId);
		map.put("sysCustomerName", sysCustomerName);
		boolean flag = getSysCustomerServ().isUpdateCustomerExist(map);
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
	 * 修改选中客户信息
	 * @return
	 */
	public String updateSysCustomer(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("客户管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerAction");
		sysLogOperate.setMethodName("updateSysCustomer");
		
		Map hashMap = new HashMap();
		String sysCustomerId = getRequest().getParameter("sysCustomerId");
		String sysCustomerName = getRequest().getParameter("sysCustomerName");//客户名称
		if(sysCustomerName != null)
			sysCustomerName = sysCustomerName.trim();
		String sysAreaId = getRequest().getParameter("sysAreaName");//区域ID
		String stateId = getRequest().getParameter("state");//状态ID
		hashMap.put("sysCustomerName", sysCustomerName);
		hashMap.put("sysAreaId", sysAreaId);
		hashMap.put("stateId", stateId);
		hashMap.put("sysCustomerId", sysCustomerId);
		
		//操作日志参数
		String param = "sysCustomerName: "+sysCustomerName +",sysAreaId: "+sysAreaId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(sysCustomerId != null && !sysCustomerId.equals(""))
			getSysCustomerServ().updateSysCustomer(hashMap);
		return SUCCESS;
	}
	
	
	public ISysCustomerService getSysCustomerServ() {
		sysCustomerServ = (ISysCustomerService)SpringBeanInvoker.getBean("eaap-op2-sso-sysCustomerServ");
		return sysCustomerServ;
	}

	public void setSysCustomerServ(ISysCustomerService sysCustomerServ) {
		this.sysCustomerServ = sysCustomerServ;
	}

	public ISysAreaService getSysAreaServ() {
		sysAreaServ = (ISysAreaService)SpringBeanInvoker.getBean("eaap-op2-sso-sysAreaServ");
		return sysAreaServ;
	}

	public void setSysAreaServ(ISysAreaService sysAreaServ) {
		this.sysAreaServ = sysAreaServ;
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

	public List getAreaList() {
		return areaList;
	}

	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}

	public SysCustomer getSysCustomer() {
		return sysCustomer;
	}

	public void setSysCustomer(SysCustomer sysCustomer) {
		this.sysCustomer = sysCustomer;
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
	
	
	
	
	
	
	
	
	
	
	
//	private SysCustomerRoleService sysCustomerRoleService;
//	private SysCustomerService sysCustomerService;
//	private SysCardTypeService sysCardTypeService;
//	private Map<String,Object> paramMap;
//	private String currPage;
//	private String pageNum;
//	private String areaid;
//	private String syscustid ;
//	private String syscutomerid;
//	private String syscustomername;
//	private String sysareaid;
//	private String syscardtypeid;
//	private String syscardnumber;
//	/**
//	 * 查询公司客户信息方法
//	 * @author wgm
//	 * @return
//	 */
//	public String list()
//	{
//		if(ServletActionContext.getRequest().getParameter("num1")!=null)
//		{
//			String num1 = ServletActionContext.getRequest().getParameter("num1").toString();
//			ServletActionContext.getRequest().setAttribute("num1",num1);
//			
//			String num2 = ServletActionContext.getRequest().getParameter("num2");
//			ServletActionContext.getRequest().setAttribute("num2",num2);
//		}
//		
//		SysCustomerBean bean = new SysCustomerBean();
//		if(ServletActionContext.getRequest().getParameter("areaid")!= null && ServletActionContext.getRequest().getParameter("areaid")!="")
//		{
//			bean.setAreaid(ServletActionContext.getRequest().getParameter("areaid"));
//		}
//		
//		PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		bean.setPageBean(pageBean);
//		SYSPERSON person  = (SYSPERSON)session.get("userbean");
//		String userType = person.getSysIdTypeId();
//		String cusName = person.getCustomerName();
//
//		sysCustomerService.doSearch(bean,userType,cusName);
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("pageBean", bean.getPageBean());
//		paramMap.put("searchResult",bean.getSearchResult());
//		//List<SysArea> areaList = sysCustomerService.getAreas();
//		List<SysArea> areaList = sysCustomerService.getAreas(userType,cusName);
//		paramMap.put("areaList", areaList) ;
//		return "success";
//	}
//
//	/**
//	 * 添加公司客户方法
//	 * @author wgm 3-10 alter
//	 * @return list
//	 */
//	public String to_add()
//	{
//		SYSPERSON person  = (SYSPERSON)session.get("userbean");
//		String userType = person.getSysIdTypeId();
//		String cusName = person.getCustomerName();
//		
//		List<SysCardType> cardTypeList = sysCardTypeService.getCardTypes();
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("cardTypeList", cardTypeList) ;
//		//List<SysArea> areaList =  sysCustomerService.getAreas();
//		List<SysArea> areaList =  sysCustomerService.getAreas(userType,cusName);
//		paramMap.put("areaList", areaList) ;
//
//		return "success";
//	}
//
//	public String to_update()
//	{
//		SYSPERSON person  = (SYSPERSON)session.get("userbean");
//		String userType = person.getSysIdTypeId();
//		String cusName = person.getCustomerName();
//		
//		syscutomerid = ServletActionContext.getRequest().getParameter("custid").toString();
//		syscustomername = ServletActionContext.getRequest().getParameter("custname");
//		sysareaid = ServletActionContext.getRequest().getParameter("areaid");
//		syscardtypeid = ServletActionContext.getRequest().getParameter("cardtypeid");
//		syscardnumber = ServletActionContext.getRequest().getParameter("cardname");
//		
//		List<SysCardType> cardTypeList = sysCardTypeService.getCardTypes();
//		paramMap = new HashMap<String, Object>();
//		paramMap.put("cardTypeList", cardTypeList) ;
//		
//		//List<SysArea> areaList =  sysCustomerService.getAreas();
//		List<SysArea> areaList =  sysCustomerService.getAreas(userType,cusName);
//		paramMap.put("areaList", areaList) ;
//		
//		return "success";
//	}
//	
//	public String update()
//	{
//		syscutomerid = ServletActionContext.getRequest().getParameter("syscutomerid");
//		syscustomername = ServletActionContext.getRequest().getParameter("syscustomername");
//		//String sysCardTypeId = ServletActionContext.getRequest().getParameter("sysCardTypeId");
//		//syscardnumber = ServletActionContext.getRequest().getParameter("syscardnumber");
//		String sysAreaId = ServletActionContext.getRequest().getParameter("sysAreaId");
//		if(syscutomerid == null)
//		{
//			if(syscustomername!=null)
//			{
//				//sysCustomerService.insert(syscustomername, Long.valueOf(sysAreaId), Long.valueOf(sysCardTypeId), syscardnumber);
//				sysCustomerService.insert(syscustomername, Long.valueOf(sysAreaId));
//			}
//		}
//		else
//		{
//			//sysCustomerService.updateBycustomerid(Long.valueOf(syscutomerid),syscustomername,Long.valueOf(sysAreaId),Long.valueOf(sysCardTypeId),syscardnumber);
//			sysCustomerService.updateBycustomerid(Long.valueOf(syscutomerid),syscustomername,Long.valueOf(sysAreaId));
//		}
//		return "success";
//	}
//	
//	public String delete()
//	{
//		String [] ids = ServletActionContext.getRequest().getParameterValues("checkbox");
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
//				
//				/*ServletActionContext.getRequest().setAttribute("num1", num1);
//				ServletActionContext.getRequest().setAttribute("num2", num2);*/
//			}
//		}
//		return "success";
//	}
//
//	public SysCustomerRoleService getSysCustomerRoleService() {
//		return sysCustomerRoleService;
//	}
//
//	public void setSysCustomerRoleService(
//			SysCustomerRoleService sysCustomerRoleService) {
//		this.sysCustomerRoleService = sysCustomerRoleService;
//	}
//
//	public SysCustomerService getSysCustomerService() {
//		return sysCustomerService;
//	}
//
//	public void setSysCustomerService(SysCustomerService sysCustomerService) {
//		this.sysCustomerService = sysCustomerService;
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
//	public String getSyscustid() {
//		return syscustid;
//	}
//
//	public void setSyscustid(String syscustid) {
//		this.syscustid = syscustid;
//	}
//
//	public SysCardTypeService getSysCardTypeService() {
//		return sysCardTypeService;
//	}
//
//	public void setSysCardTypeService(SysCardTypeService sysCardTypeService) {
//		this.sysCardTypeService = sysCardTypeService;
//	}
//
//	public String getAreaid() {
//		return areaid;
//	}
//
//	public void setAreaid(String areaid) {
//		this.areaid = areaid;
//	}
//
//	public String getSyscutomerid() {
//		return syscutomerid;
//	}
//
//	public void setSyscutomerid(String syscutomerid) {
//		this.syscutomerid = syscutomerid;
//	}
//
//	public String getSyscustomername() {
//		return syscustomername;
//	}
//
//	public void setSyscustomername(String syscustomername) {
//		this.syscustomername = syscustomername;
//	}
//
//	public String getSysareaid() {
//		return sysareaid;
//	}
//
//	public void setSysareaid(String sysareaid) {
//		this.sysareaid = sysareaid;
//	}
//
//	public String getSyscardtypeid() {
//		return syscardtypeid;
//	}
//
//	public void setSyscardtypeid(String syscardtypeid) {
//		this.syscardtypeid = syscardtypeid;
//	}
//
//	public String getSyscardnumber() {
//		return syscardnumber;
//	}
//
//	public void setSyscardnumber(String syscardnumber) {
//		this.syscardnumber = syscardnumber;
//	}
	
	
}
