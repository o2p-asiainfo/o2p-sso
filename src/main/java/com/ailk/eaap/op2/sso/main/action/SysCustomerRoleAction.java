package com.ailk.eaap.op2.sso.main.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.service.ISysCustomerRoleService;
import com.ailk.eaap.op2.sso.main.service.ISysCustomerService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;


/**
 * 客户角色管理
 * @author zhaobl
 *
 */
public class SysCustomerRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISysCustomerRoleService sysCustomerRoleServ ;
	private ISysCustomerService sysCustomerServ;
	
	private List sysCustomerList = new ArrayList();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ; 
	/**
	 * 跳转到客户角色管理页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		
		List<SysCustomer>  customerList = getSysCustomerServ().queryAll();
		for(int i=0; i<customerList.size(); i++){
			Map map = new HashMap();
			SysCustomer sysCustomer = customerList.get(i);
			map.put("sysCustomerId", sysCustomer.getSysCustomerId());
			map.put("sysCustomerName", sysCustomer.getSysCustomerName());
			sysCustomerList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示客户角色表
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
		sysLogOperate.setModelName("客户角色授权管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerRoleAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<Map> sysCustomerRoleList = new ArrayList<Map>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		String sysCustomerId = getRequest().getParameter("sysCustomerName");//客户编号
		
		//操作日志参数
		String param = "sysCustomerId: "+sysCustomerId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(sysCustomerId)){
			hashMap.put("sysCustomerId", sysCustomerId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysCustomerRoleList = sysCustomerRoleServ.queryAllSysCustomerRole(hashMap);
			total = sysCustomerRoleServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysCustomerRoleList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysCustomerRoleList = sysCustomerRoleServ.queryAllSysCustomerRole(hashMap);
			total = sysCustomerRoleServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysCustomerRoleList));
		}
		return hashMap;
	}
	/**
	 * 根据客户ID查询出此客户未赋权的角色
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedRole(Map map){
		List<Map> sysRoleList = sysCustomerRoleServ.queryNotSelectedRole(map);
		return sysRoleList;
	}
	/**
	 * 跳转到添加客户角色页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		List<SysCustomer>  customerList = getSysCustomerServ().queryAll();
		for(int i=0; i<customerList.size(); i++){
			Map map = new HashMap();
			SysCustomer sysCustomer = customerList.get(i);
			map.put("sysCustomerId", sysCustomer.getSysCustomerId());
			map.put("sysCustomerName", sysCustomer.getSysCustomerName());
			sysCustomerList.add(map);
		}
		
		return SUCCESS;
	}
	/**
	 * 添加客户角色
	 * @return
	 */
	public String addSysCustomerRole(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("客户角色授权管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerRoleAction");
		sysLogOperate.setMethodName("addSysCustomerRole");
		
		List<Map> list = new ArrayList<Map>();
		String sysCustomerId = getRequest().getParameter("sysCustomerName"); //客户编号
		String[] sysRoleIds = getRequest().getParameterValues("roleName");//新赋权角色编号
		
		//操作日志参数
		StringBuffer sb = new StringBuffer();
		sb.append("sysCustomerId: " + sysCustomerId + ";");
		sb.append("sysRoleIds: ");
		for(int i=0; i<sysRoleIds.length; i++){
			sb.append(sysRoleIds[i]+",");
		}
		sysLogOperate.setParamName(sb.toString());
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		for(int i=0; i<sysRoleIds.length; i++){
			String sysCustomerRoleId = getSysCustomerRoleServ().querySeq();
			Map hashMap = new HashMap();
			hashMap.put("sysCustomerRoleId", sysCustomerRoleId);
			hashMap.put("sysCustomerId", sysCustomerId);
			hashMap.put("sysRoleId", sysRoleIds[i]);
			list.add(hashMap);
		}
		
		getSysCustomerRoleServ().insertSysCustomerRole(list);
		return SUCCESS;
	}
	/**
	 * 删除选中客户角色
	 */
	public void deleteSysCustomerRole(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("客户角色授权管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysCustomerRoleAction");
		sysLogOperate.setMethodName("deleteSysCustomerRole");
		
		String sysCustomerRoleId = getRequest().getParameter("sysCustomerRoleId");
		
		//操作日志参数
		String param = "sysCustomerRoleId: "+sysCustomerRoleId  ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(sysCustomerRoleId != null && !sysCustomerRoleId.equals(""))
			getSysCustomerRoleServ().deleteSysCustomerRole(sysCustomerRoleId);
	}
//	/**
//	 * 跳转到修改角色页面
//	 * 页面加载前下拉框等的初始化
//	 * @return
//	 */
//	public String preUpdate(){
//		Map hashMap1 = new HashMap();
//		hashMap1.put("stateId", "1");
//		hashMap1.put("stateName", "正常");
//		Map hashMap2 = new HashMap();
//		hashMap2.put("stateId", "2");
//		hashMap2.put("stateName", "暂停");
//		Map hashMap3 = new HashMap();
//		hashMap3.put("stateId", "3");
//		hashMap3.put("stateName", "作废");
//		stateList.add(hashMap1);
//		stateList.add(hashMap2);
//		stateList.add(hashMap3);
//		
//		String sysRoleId = getRequest().getParameter("sysRoleId");
//		sysRole = getSysRoleServ().querySysRoleById(sysRoleId);
//		return SUCCESS;
//	}
//	/**
//	 * 修改选中角色信息
//	 * @return
//	 */
//	public String updateSysRole(){
//		Map hashMap = new HashMap();
//		String sysRoleId = getRequest().getParameter("sysRoleId");
//		String sysRoleName = getRequest().getParameter("sysRoleName");//角色名称
//		String shownum = getRequest().getParameter("shownum");//排序号
//		String stateId = getRequest().getParameter("state");//状态ID
//		hashMap.put("sysRoleName", sysRoleName);
//		hashMap.put("shownum", shownum);
//		hashMap.put("stateId", stateId);
//		hashMap.put("sysRoleId", sysRoleId);
//		if(sysRoleId != null && !sysRoleId.equals(""))
//			getSysRoleServ().updateSysRole(hashMap);
//		return SUCCESS;
//	}
	
	

	
	
	public ISysCustomerRoleService getSysCustomerRoleServ() {
		sysCustomerRoleServ = (ISysCustomerRoleService)SpringBeanInvoker.getBean("eaap-op2-sso-sysCustomerRoleServ");
		return sysCustomerRoleServ;
	}

	public void setSysCustomerRoleServ(ISysCustomerRoleService sysCustomerRoleServ) {
		this.sysCustomerRoleServ = sysCustomerRoleServ;
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
	
	public List getSysCustomerList() {
		return sysCustomerList;
	}

	public void setSysCustomerList(List sysCustomerList) {
		this.sysCustomerList = sysCustomerList;
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
	
}
