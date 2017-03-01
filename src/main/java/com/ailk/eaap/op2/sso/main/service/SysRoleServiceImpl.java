package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map;


import com.ailk.eaap.op2.sso.main.dao.ISysRoleDao;
import com.ailk.eaap.op2.sso.main.dao.SysDeptDao;
import com.ailk.eaap.op2.sso.main.dao.SysRoleDao;
import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.RoleDataBean;
import com.ailk.eaap.op2.sso.main.model.SvcRoleBean;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysFunction;
import com.ailk.eaap.op2.sso.main.model.SysRole;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;
/**
 * 角色Serv
 * @author zhaobl
 *
 */
public  class SysRoleServiceImpl implements ISysRoleService {
	private ISysRoleDao sysRoleDao;
	
	

	public int deleteSysRole(String sysRoleId) {
		int flag = sysRoleDao.deleteSysRole(sysRoleId);
		return flag;
	}

	public void insertSysRole(Map map) {
		sysRoleDao.insertSysRole(map);
	}

	public List<SysRole> queryAllSysRole(Map map) {
		List<SysRole> sysRoleList = sysRoleDao.queryAllSysRole(map);
		return sysRoleList;
	}

	public int queryCount(Map map) {
		int count = sysRoleDao.queryCount(map);
		return count;
	}

	public void updateSysRole(Map map) {
		sysRoleDao.updateSysRole(map);
	}

	public SysRole querySysRoleById(String sysRoleId) {
		SysRole sysRole = sysRoleDao.querySysRoleById(sysRoleId);
		return sysRole;
	}
	
	public List<SysRole> queryAll() {
		List<SysRole> sysRoleList = sysRoleDao.queryAll();
		return sysRoleList;
	}

	public boolean isRoleExist(Map map) {
		boolean flag = sysRoleDao.isRoleExist(map);
		return flag;
	}

	public boolean isUpdateRoleExist(Map map) {
		String sysRoleId =  map.get("sysRoleId").toString();
		String sysRoleName = map.get("sysRoleName").toString();
		SysRole sysRole = sysRoleDao.querySysRoleById(sysRoleId);
		if(sysRoleName.equals(sysRole.getSysRoleName())){
			return false;
		}else{
			return this.isRoleExist(map);
		}
	}
	
	
	public ISysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}



	
	
	
	
	
	
	
	
	
	
	
//	/*private final static String SELECT_ROLE_LIST_SQL = "select a.sys_role_id,a.sys_role_name,a.sys_role_type,a.sys_status_id,a.shownum," +
//	" b.sys_status_name,d.sys_role_type_name roleTypeName " +
//	" from xrb.SYS_ROLE a " +
//	" left join xrb.SYS_ROLE_TYPE d on a.sys_role_type = d.sys_role_type_id" +
//	" left join xrb.sys_status b on a.sys_status_id = b.sys_status_id" +
//	" where 1=1 ";*/
//	
//	private final static String SELECT_ROLE_LIST_SQL = "select * from sys_role";
//	/*edit by Guangshu.Mi*/
////	private final static String SELECT_ROLE_LIST_SQL1 = "select t.sys_role_id,t.sys_role_name,s.sys_status_name," +
////			"t.role_state,to_char(t.state_time,'yyyy-mm-dd hh24:mi:ss') state_time,t.syn_times " +
////			"from sys_role t,sys_status s " +
////			"where t.sys_status_id = s.sys_status_id " +
////			"and t.sys_status_id=1";
//	/*end by Guangshu.Mi*/
//	private final static String SELECT_ROLE_LIST_SQL1 = "select t.sys_role_id,t.sys_role_name,s.sys_status_name" +
//			" from sys_role t,sys_status s " +
//			"where t.sys_status_id = s.sys_status_id " +
//			"and t.sys_status_id=1";
//	
//	private SysRoleDao sysRoleDao;
//	
//	private SysDeptDao sysDeptDao;
//	
//	public List<SysRole> getRoles()
//	{
//		 List<SysRole> list = new ArrayList<SysRole>();
//		 List<Map<String,Object>> Result = sysRoleDao.getRoles();
//		 this.convertResult(Result, list);
//		 return list ;
//	}
//	
//	public SysRole getRoleByrolename(String rolename)
//	{
//		List<Map<String,Object>> Result = new ArrayList<Map<String,Object>>()  ;
//	     List<SysFunction> list = new ArrayList<SysFunction>();
//		 String sql = "select * from sys_role where SYS_ROLE_NAME = '"+rolename+"'" ;
//		 Result = sysRoleDao.getPageResult(sql);
//		 SysRole sr = new SysRole();
//		 sr.setSysRoleId(Long.valueOf(Result.get(0).get("SYS_ROLE_ID").toString()));
//		 return sr ;
//	}
//	
//	//3-16 加入数据域判断获取 公司所属角色
//	public List<SysRole> getRoles(Long custid)
//	{
//		 List<SysRole> list = new ArrayList<SysRole>();
//		 List<Map<String,Object>> Result = sysRoleDao.getRoles(custid);
//		 this.convertResult(Result, list);
//		 return list ;
//	}
//	public List<SysRole> getUnAuthedRoles()
//	{
//		 List<SysRole> list = new ArrayList<SysRole>();
//		 List<Map<String,Object>> Result = sysRoleDao.getUnAuthedRoles();
//		 this.convertResult(Result, list);
//		 return list ;
//	}
//	
//	public void del(Long sysroleid)
//	{
//		sysRoleDao.del(sysroleid);
//	}
//	
//	private void convertResult(List<Map<String,Object>> result,List<SysRole> list)
//	{
//		for(Map<String,Object> item : result)
//		{
//			SysRole bean = new SysRole();
//			bean.setSysRoleId(Long.valueOf(ConvertUtil.nullToSpace(item.get("SYS_ROLE_ID"))));
//			bean.setSysRoleName(ConvertUtil.nullToSpace(item.get("SYS_ROLE_NAME")));
//			//bean.setSysRoleTypeId(Long.valueOf(ConvertUtil.nullToSpace(item.get("SYS_ROLE_TYPE"))));
//			list.add(bean);
//		}
//	}
//	
//	/*public List<Map<String, Object>> getRolesByRoleTypeId()
//	{
//		return sysRoleDao.getRolesByRoleTypeId();
//	}*/
//	//alert by wanggm 02-04
//	public List<Map<String, Object>> getAuthedRoleByCustId(String custid)
//	{
//		return sysRoleDao.getAuthedRoleByCustId(custid);
//	}
//	
//	public List<Map<String, Object>> getAuthedRolesByDeptId(String deptid)
//	{
//		return sysRoleDao.getAuthedRolesByDeptId(deptid);
//	}
//	
//	public List<Map<String, Object>> getAuthedRoleByCustAndRoleTypeId(Long custid)
//	{
//		return sysRoleDao.getAuthedRoleByCustAndRoleTypeId(custid);
//	}
//	//alert 02-04
//	public List<Map<String, Object>> getRoles(String deptid,String custname)
//	{
//		return sysRoleDao.getRoles(deptid,custname);
//	}
//	public String doLoadSelect() {
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> roleTypes = new ArrayList<KeyValueBean>();
//		List<KeyValueBean> status = new ArrayList<KeyValueBean>();		
//		roleTypes=sysRoleDao.selectRoleTypes();
//		status=sysDeptDao.selectStatus();
//		temp=temp.append("{roleTypes:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(roleTypes));
//		temp=temp.append("],status:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(status));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	//客户所属角色 doloadPreRole 12.28
//	public String doloadPreAuthRole(String customerId) {
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> roles = new ArrayList<KeyValueBean>();
//		roles=sysRoleDao.selectAuthRole(customerId);
//		temp=temp.append("{roles:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(roles));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//
//	public String doloadPreOperRole(String customerId) {
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> roles = new ArrayList<KeyValueBean>();
//		roles=sysRoleDao.selectOperRole(customerId);
//		temp=temp.append("{roles:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(roles));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	public String doEditRole(SvcRoleBean bean) {
//		String result = "";
//		RoleDataBean editBean = new RoleDataBean();
//		editBean.setSysRoleId(bean.getEditRoleId());
//		editBean.setSysRoleNm(bean.getRoleName());
//		//editBean.setSysRoleTypeId(bean.getRoleType());		
//		//editBean.setSysStatusId(bean.getRoleState());		
//		if("add".equals(bean.getEditType())){
//			
//			result = sysRoleDao.insertRole(editBean);
//			
//			List<Map<String,Object>> Result = new ArrayList<Map<String,Object>>()  ;
//			 String sql = "select * from sys_role where SYS_ROLE_NAME = '"+bean.getRoleName()+"'" ;
//			 Result = sysRoleDao.getPageResult(sql);
//
////			AuthUtils au = new AuthUtils();
////			au.addrole(Integer.parseInt(Result.get(0).get("SYS_ROLE_ID").toString()));
//			
//		}else if("edit".equals(bean.getEditType())){
//			result = sysRoleDao.updateRole(editBean);
//		}else{
//			
//		}
//		doSearchRoleList(bean);
//		return result;
//	}
//	
//	
//	public void doSearchRoleList(SvcRoleBean bean) {
//		List<RoleDataBean> list = new ArrayList<RoleDataBean>();
//		String sql = buildSearchRoleListSql(bean);
//		Integer size = sysRoleDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
//			List<Map<String,Object>> pageResult = sysRoleDao.getPageResult(pageSql);
//			convertRoleListResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}
//	
//	public String doDeleteRole(SvcRoleBean bean) {		
//		String result = sysRoleDao.deleteRoles(bean.getRoleId());
//		if(result.length() == 5){
//			bean.setCheckedId("");
//		}
//		doSearchRoleList(bean);
//		return result;
//	}
//	
//	public RoleDataBean doSearchEditRole(String roleId) {
//		List<RoleDataBean> list = new ArrayList<RoleDataBean>();
//		RoleDataBean result = new RoleDataBean();
//		String sql = SELECT_ROLE_LIST_SQL + " where t.SYS_ROLE_ID = " + roleId;
//		List<Map<String,Object>> pageResult = sysRoleDao.getPageResult(sql);
//		if(pageResult != null && pageResult.size() > 0){
//			convertRoleListResult(pageResult, list);
//			result = list.get(0);
//		}		
//		return result;
//	}
//	
//	private String buildSearchRoleListSql(SvcRoleBean bean){
//		StringBuffer buffer = new StringBuffer(SELECT_ROLE_LIST_SQL1);
//		if(!ConvertUtil.isEmpty(bean.getRoleName())){
//			buffer.append(" and t.sys_role_name like '%").append(bean.getRoleName()).append("%'");
//		}
//		/*if(!ConvertUtil.isEmpty(bean.getRoleState())){
//			buffer.append(" and a.sys_status_id = ").append(bean.getRoleState());
//		}
//		if(!ConvertUtil.isEmpty(bean.getRoleType())){
//			buffer.append(" and a.sys_role_type = ").append(bean.getRoleType());
//		}*/
//		//buffer.append(" order by a.sys_role_id");	
//		return buffer.toString();
//	}
//	
//	private void convertRoleListResult(List<Map<String,Object>> result,List<RoleDataBean> list){
//		for(Map<String,Object> item : result){
//			RoleDataBean bean = new RoleDataBean();
//			/*
//			bean.setShownum(ConvertUtil.nullToSpace(item.get("shownum")));
//			bean.setSysRoleId(ConvertUtil.nullToSpace(item.get("sys_role_id")));
//			bean.setSysRoleNm(ConvertUtil.nullToSpace(item.get("sys_role_name")));
//			bean.setSysRoleTypeId(ConvertUtil.nullToSpace(item.get("sys_role_type")));	
//			bean.setSysRoleTypeNm(ConvertUtil.nullToSpace(item.get("roleTypeName")));
//			bean.setSysStatusId(ConvertUtil.nullToSpace(item.get("sys_status_id")));
//			bean.setSysStatusNm(ConvertUtil.nullToSpace(item.get("sys_status_name")));
//			*/
//			bean.setSysRoleId(ConvertUtil.nullToSpace(item.get("sys_role_id")));
//			bean.setSysRoleNm(ConvertUtil.nullToSpace(item.get("sys_role_name")));
//			bean.setSysStatusNm(ConvertUtil.nullToSpace(item.get("sys_status_name")));
//			bean.setSynStatusName(ConvertUtil.nullToSpace(item.get("role_state")));
//			bean.setSynStateTime(ConvertUtil.nullToSpace(item.get("state_time")));
//			bean.setSynTimes(ConvertUtil.nullToSpace(item.get("syn_times")));
//			list.add(bean);
//		}
//	}
//	
//	public SysRoleDao getSysRoleDao() {
//		return sysRoleDao;
//	}
//
//	public void setSysRoleDao(SysRoleDao sysRoleDao) {
//		this.sysRoleDao = sysRoleDao;
//	}
//
//	/*public List<Map<String, Object>> getRoleByRoleId(Long SYS_ROLE_ID) {
//		return sysRoleDao.getRoleByRoleId(SYS_ROLE_ID);
//	}*/
//
//	public SysDeptDao getSysDeptDao() {
//		return sysDeptDao;
//	}
//
//	public void setSysDeptDao(SysDeptDao sysDeptDao) {
//		this.sysDeptDao = sysDeptDao;
//	}
//
//	public void synStatusChange(String roleId) {
//		sysRoleDao.synStatusChange(roleId);
//	}

}
