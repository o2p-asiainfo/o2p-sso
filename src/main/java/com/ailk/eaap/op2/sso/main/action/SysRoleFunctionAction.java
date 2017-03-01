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
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.SysRole;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.ailk.eaap.op2.sso.main.service.ISysRoleFunctionService;
import com.ailk.eaap.op2.sso.main.service.ISysRoleService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;


/**
 * 角色权限管理
 * @author zhaobl
 *
 */
public class SysRoleFunctionAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ISysRoleFunctionService sysRoleFunctionServ ;
	private ISysRoleService sysRoleServ;
	
	private List sysRoleList = new ArrayList();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ;
	/**
	 * 跳转到角色权限管理页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		
		List<SysRole>  roleList = getSysRoleServ().queryAll();
		for(int i=0; i<roleList.size(); i++){
			Map map = new HashMap();
			SysRole sysRole = roleList.get(i);
			if(sysRole.getSysRoleName() == null || sysRole.getSysRoleName().equals(""))
				continue;
			map.put("sysRoleId", sysRole.getSysRoleId());
			map.put("sysRoleName", sysRole.getSysRoleName());
			sysRoleList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示角色权限表
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
		sysLogOperate.setModelName("角色授权管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleFunctionAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<Map> sysRoleFunctionList = new ArrayList<Map>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		String roleId = getRequest().getParameter("sysRoleName");//角色编号
		
		//操作日志参数
		String param = "roleId: "+roleId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(roleId)){
			hashMap.put("roleId", roleId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysRoleFunctionList = sysRoleFunctionServ.queryAllSysRoleFunction(hashMap);
			total = sysRoleFunctionServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysRoleFunctionList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysRoleFunctionList = sysRoleFunctionServ.queryAllSysRoleFunction(hashMap);
			total = sysRoleFunctionServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysRoleFunctionList));
		}
		return hashMap;
	}
	/**
	 * 根据角色ID查询出此角色未赋权的功能点
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedFunction(Map map){
		List<Map> sysFunctionList = sysRoleFunctionServ.queryNotSelectedFunction(map);
		return sysFunctionList;
	}
	/**
	 * 跳转到添加角色页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		List<SysRole>  roleList = getSysRoleServ().queryAll();
		for(int i=0; i<roleList.size(); i++){
			Map map = new HashMap();
			SysRole sysRole = roleList.get(i);
			if(sysRole.getSysRoleName() == null || sysRole.getSysRoleName().equals(""))
				continue;
			map.put("sysRoleId", sysRole.getSysRoleId());
			map.put("sysRoleName", sysRole.getSysRoleName());
			sysRoleList.add(map);
		}
		
		return SUCCESS;
	}
	/**
	 * 添加角色权限
	 * @return
	 */
	public String addSysRoleFunction(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("角色授权管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleFunctionAction");
		sysLogOperate.setMethodName("addSysRoleFunction");
		
		List<Map> list = new ArrayList<Map>();
		String roleId = getRequest().getParameter("sysRoleName"); //角色编号
		String[] functionIds = getRequest().getParameterValues("functionName");//新赋权功能点编号
		
		//操作日志参数
		StringBuffer sb = new StringBuffer();
		sb.append("roleId: " + roleId + ";");
		sb.append("sysRoleIds: ");
		if(functionIds!=null){
			for(int i=0; i<functionIds.length; i++){
				sb.append(functionIds[i]+",");
			}
		}
		sysLogOperate.setParamName(sb.toString());
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		for(int i=0; i<functionIds.length; i++){
			String sysRoleFuncId = getSysRoleFunctionServ().querySeq();
			Map hashMap = new HashMap();
			hashMap.put("sysRoleFuncId", sysRoleFuncId);
			hashMap.put("roleId", roleId);
			hashMap.put("functionId", functionIds[i]);
			list.add(hashMap);
		}
		
		getSysRoleFunctionServ().insertSysRoleFunction(list);
		return SUCCESS;
	}
	/**
	 * 删除选中角色权限
	 */
	public void deleteSysRoleFunction(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("角色授权管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysRoleFunctionAction");
		sysLogOperate.setMethodName("deleteSysRoleFunction");
		
		String sysRoleFuncId = getRequest().getParameter("sysRoleFuncId");
		
		//操作日志参数
		String param = "sysRoleFuncId: "+sysRoleFuncId  ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(sysRoleFuncId != null && !sysRoleFuncId.equals(""))
			getSysRoleFunctionServ().deleteSysRoleFunction(sysRoleFuncId);
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
	
	

	
	public ISysRoleFunctionService getSysRoleFunctionServ() {
		sysRoleFunctionServ = (ISysRoleFunctionService)SpringBeanInvoker.getBean("eaap-op2-sso-sysRoleFunctionServ");
		return sysRoleFunctionServ;
	}

	public void setSysRoleFunctionServ(ISysRoleFunctionService sysRoleFunctionServ) {
		this.sysRoleFunctionServ = sysRoleFunctionServ;
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

	public List getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List sysRoleList) {
		this.sysRoleList = sysRoleList;
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
