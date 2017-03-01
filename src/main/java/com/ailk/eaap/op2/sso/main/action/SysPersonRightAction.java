package com.ailk.eaap.op2.sso.main.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysDeptRoleService;
import com.ailk.eaap.op2.sso.main.service.ISysDeptService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.ailk.eaap.op2.sso.main.service.ISysPersonRightService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 员工权限管理
 * @author zhaobl
 *
 */
public class SysPersonRightAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ISysPersonRightService sysPersonRightServ ;
	private IPersonServ sysPersonServ;
	
	private List personList = new ArrayList();
	private List stateList = new ArrayList();
	private List roleRightTypeList = new ArrayList();
	private List functionRightTypeList = new ArrayList();
	private List sysRoleTypeList = new ArrayList();
	private Map sysPersonRightMap = new HashMap();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	private String tenantId;
	
	private ISysLogOperateService sysLogOperateServ; 
	/**
	 * 跳转到员工权限管理页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String sysIdTypeId = sysPerson.getSysIdTypeId().toString();
		//1:超级管理员  2:管理员  3：操作员
		if(sysIdTypeId.equals("1")){
			List<Map> sysPersonList = getSysPersonServ().queryAll();
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else if(sysIdTypeId.equals("2")){
			SysDept sysDept = sysPerson.getSysDept();
			String deptId ="";
			if(sysDept != null)
				deptId = sysDept.getDeptId().toString();
			Map parmMap = new HashMap();
			parmMap.put("deptId", deptId);
			
			List<Map> sysPersonList = getSysPersonServ().queryAllByDeptId(parmMap);
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else{
			Map map = new HashMap();
			map.put("sysPersonId", sysPerson.getSysPersonId());
			map.put("cardNumber", sysPerson.getCardNumber());
			personList.add(map);
		}
		
		return SUCCESS;
	}
	/**
	 * 查询展示员工权限表
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
		sysLogOperate.setModelName("员工授权管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonRightAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<Map> sysPersonRightList = new ArrayList<Map>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		//MYSQL 
		hashMap.put("pro_mysql", startRow - 1);
		hashMap.put("page_record", rows);
		
		hashMap.put("startRow", startRow);
		hashMap.put("rows", rows);
		String sysPersonId = getRequest().getParameter("cardNumber");//员工Id
		
		//操作日志参数
		String param = "sysPersonId: "+sysPersonId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		/** 判断员工身份 **/
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String sysIdTypeId = sysPerson.getSysIdTypeId().toString();
		
		if(sysIdTypeId.equals("1")){
			hashMap.put("sysPersonId", sysPersonId);
			sysPersonRightList = sysPersonRightServ.queryAllSysPersonRight(hashMap);
			total = sysPersonRightServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysPersonRightList));
		}else if(sysIdTypeId.equals("2")){
			hashMap.put("sysPersonId", sysPersonId);
			hashMap.put("deptId", sysPerson.getSysDept().getDeptId().toString());
			sysPersonRightList = sysPersonRightServ.queryAllSysPersonRight(hashMap);
			total = sysPersonRightServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysPersonRightList));
		}else{
			hashMap.put("sysPersonId", sysPerson.getSysPersonId().toString());
			sysPersonRightList = sysPersonRightServ.queryAllSysPersonRight(hashMap);
			total = sysPersonRightServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysPersonRightList));
		}
		
		return hashMap;
	}
	/**
	 * 根据授权员工Id和被授权员工Id查询出此员工未赋权的角色
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedRole(Map map){
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String authSysPersonId = sysPerson.getSysPersonId().toString();
		map.put("authSysPersonId", authSysPersonId);
		
		List<Map> sysRoleList = getSysPersonRightServ().queryNotSelectedRole(map);
		return sysRoleList;
	}
	/**
	 * 根据授权员工Id和被授权员工Id查询出此员工未赋权的功能点
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedFunction(Map map){
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String authSysPersonId = sysPerson.getSysPersonId().toString();
		map.put("authSysPersonId", authSysPersonId);
		
		List<Map> sysFunctionList = getSysPersonRightServ().queryNotSelectedFunction(map);
		return sysFunctionList;
	}
	/**
	 * 跳转到添加员工权限页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		/** 工号加载 **/
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String sysIdTypeId = sysPerson.getSysIdTypeId().toString();
		//1:超级管理员  2:管理员  3：操作员
		if(sysIdTypeId.equals("1")){
			List<Map> sysPersonList = getSysPersonServ().queryAll();
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else if(sysIdTypeId.equals("2")){
			SysDept sysDept = sysPerson.getSysDept();
			String deptId ="";
			if(sysDept != null)
				deptId = sysDept.getDeptId().toString();
			Map parmMap = new HashMap();
			parmMap.put("deptId", deptId);
			
			List<Map> sysPersonList = getSysPersonServ().queryAllByDeptId(parmMap);
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else{
			Map map = new HashMap();
			map.put("sysPersonId", sysPerson.getSysPersonId());
			map.put("cardNumber", sysPerson.getCardNumber());
			personList.add(map);
		}
		/** 状态 **/
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
		/** 角色权限类型 **/
		Map hashMap4 = new HashMap();
		hashMap4.put("roleRightTypeId", "1");
		hashMap4.put("roleRightTypeName", "授权类");
		Map hashMap5 = new HashMap();
		hashMap5.put("roleRightTypeId", "2");
		hashMap5.put("roleRightTypeName", "操作类");
		roleRightTypeList.add(hashMap4);
		roleRightTypeList.add(hashMap5);
		/** 功能点权限类型 **/
		Map hashMap6 = new HashMap();
		hashMap6.put("functionRightTypeId", "1");
		hashMap6.put("functionRightTypeName", "授权类");
		Map hashMap7 = new HashMap();
		hashMap7.put("functionRightTypeId", "2");
		hashMap7.put("functionRightTypeName", "操作类");
		functionRightTypeList.add(hashMap6);
		functionRightTypeList.add(hashMap7);
		
		return SUCCESS;
	}
	/**
	 * 添加员工权限（角色/功能点）
	 * @return
	 */
	public String addSysPersonRight(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工授权管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonRightAction");
		sysLogOperate.setMethodName("addSysPersonRight");
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String authSysPersonId = sysPerson.getSysPersonId().toString();//授权员工编号
		
		List<Map> list = new ArrayList<Map>();
		String sysPersonId = getRequest().getParameter("cardNumber"); //员工编号
		String sysStatusId = getRequest().getParameter("state"); //状态Id
		String roleRightTypeId = getRequest().getParameter("roleRightType"); //角色权限类型
		String functionRightTypeId = getRequest().getParameter("functionRightType"); //功能点权限类型
		String[] sysRoleIds = getRequest().getParameterValues("roleName");//新赋权角色编号
		String[] functionIds = getRequest().getParameterValues("functionName");//新赋权功能点编号
		
		//操作日志参数
		StringBuffer sb = new StringBuffer();
		sb.append("sysPersonId: " + sysPersonId + ";");
		sb.append("authSysPersonId: " + authSysPersonId + ";");
		sb.append("sysStatusId: " + sysStatusId + ";");
		sb.append("roleRightTypeId: " + roleRightTypeId + ";");
		sb.append("functionRightTypeId: " + functionRightTypeId + ";");
		sb.append("sysRoleIds: ");
		if(sysRoleIds!=null){
			for(int i=0; i<sysRoleIds.length; i++){
				sb.append(sysRoleIds[i]+",");
			}
		}
		sb.append("functionIds: ");
		if(functionIds!=null){
			for(int i=0; i<functionIds.length; i++){
				sb.append(functionIds[i]+",");
			}
		}
		sysLogOperate.setParamName(sb.toString());
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(sysRoleIds!=null && sysRoleIds.length>0){
			for(int i=0; i<sysRoleIds.length; i++){
				String sysRightId = getSysPersonRightServ().querySeq();
				Map hashMap = new HashMap();
				hashMap.put("sysRightId", sysRightId);
				hashMap.put("sysPersonId", sysPersonId);
				hashMap.put("sysRoleId", sysRoleIds[i]);
				hashMap.put("sysRoleTypeId", roleRightTypeId);
				hashMap.put("authPersonId", authSysPersonId);
				hashMap.put("sysStatusId", sysStatusId);
				
				list.add(hashMap);
			}
		}
		if(functionIds!=null && functionIds.length>0){
			for(int i=0; i<functionIds.length; i++){
				String sysRightId = getSysPersonRightServ().querySeq();
				Map hashMap = new HashMap();
				hashMap.put("sysRightId", sysRightId);
				hashMap.put("sysPersonId", sysPersonId);
				hashMap.put("functionId", functionIds[i]);
				hashMap.put("sysRoleTypeId", functionRightTypeId);
				hashMap.put("authPersonId", authSysPersonId);
				hashMap.put("sysStatusId", sysStatusId);
				
				list.add(hashMap);
			}
		}
		getSysPersonRightServ().insertSysPersonRight(list);
		return SUCCESS;
	}
	/**
	 * 删除选中员工权限
	 */
	public void deleteSysPersonRight(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工授权管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonRightAction");
		sysLogOperate.setMethodName("deleteSysPersonRight");
		
		String sysRightId = getRequest().getParameter("sysRightId");
		
		//操作日志参数
		String param = "sysRightId: "+sysRightId  ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(sysRightId != null && !sysRightId.equals(""))
			getSysPersonRightServ().deleteSysPersonRight(sysRightId);
	}
	/**
	 * 跳转到修改员工权限页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preUpdate(){
		/** 状态 **/
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
		/** 角色权限类型 **/
		Map hashMap4 = new HashMap();
		hashMap4.put("sysRoleTypeId", "1");
		hashMap4.put("sysRoleTypeName", "授权类");
		Map hashMap5 = new HashMap();
		hashMap5.put("sysRoleTypeId", "2");
		hashMap5.put("sysRoleTypeName", "操作类");
		sysRoleTypeList.add(hashMap4);
		sysRoleTypeList.add(hashMap5);
		
		String sysRightId = getRequest().getParameter("sysRightId");
		sysPersonRightMap = getSysPersonRightServ().querySysPersonRightById(sysRightId);
		return SUCCESS;
	}
	/**
	 * 修改选中员工权限（只能修改角色类型/状态）
	 * @return
	 */
	public String updateSysPersonRight(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工授权管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonRightAction");
		sysLogOperate.setMethodName("updateSysPersonRight");
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		Map hashMap = new HashMap();
		String sysRightId = getRequest().getParameter("sysRightId");
		String sysRoleTypeId = getRequest().getParameter("sysRoleTypeName");//角色类型
		String sysStatusId = getRequest().getParameter("state");//状态ID
		hashMap.put("sysRightId", sysRightId);
		hashMap.put("sysRoleTypeId", sysRoleTypeId);
		hashMap.put("sysStatusId", sysStatusId);
		if(sysRightId != null && !sysRightId.equals(""))
			getSysPersonRightServ().updateSysPersonRight(hashMap);
		return SUCCESS;
	}
	
	
	
	
	public ISysPersonRightService getSysPersonRightServ() {
		sysPersonRightServ = (ISysPersonRightService)SpringBeanInvoker.getBean("eaap-op2-sso-sysPersonRightServ");
		return sysPersonRightServ;
	}

	public void setSysPersonRightServ(ISysPersonRightService sysPersonRightServ) {
		this.sysPersonRightServ = sysPersonRightServ;
	}

	public IPersonServ getSysPersonServ() {
		sysPersonServ = (IPersonServ)SpringBeanInvoker.getBean("personServ");
		return sysPersonServ;
	}

	public void setSysPersonServ(IPersonServ sysPersonServ) {
		this.sysPersonServ = sysPersonServ;
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}

	public List getPersonList() {
		return personList;
	}

	public void setPersonList(List personList) {
		this.personList = personList;
	}

	public List getStateList() {
		return stateList;
	}
	
	public void setStateList(List stateList) {
		this.stateList = stateList;
	}
	
	public List getRoleRightTypeList() {
		return roleRightTypeList;
	}
	
	public void setRoleRightTypeList(List roleRightTypeList) {
		this.roleRightTypeList = roleRightTypeList;
	}
	
	public List getFunctionRightTypeList() {
		return functionRightTypeList;
	}
	
	public void setFunctionRightTypeList(List functionRightTypeList) {
		this.functionRightTypeList = functionRightTypeList;
	}
	
	public List getSysRoleTypeList() {
		return sysRoleTypeList;
	}
	
	public void setSysRoleTypeList(List sysRoleTypeList) {
		this.sysRoleTypeList = sysRoleTypeList;
	}
	
	public Map getSysPersonRightMap() {
		return sysPersonRightMap;
	}
	public void setSysPersonRightMap(Map sysPersonRightMap) {
		this.sysPersonRightMap = sysPersonRightMap;
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
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	
}
