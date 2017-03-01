package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.RoleDataBean;

public interface SysRoleDao 
{
	public int getResultSize(String sql) ;
	
	public List<Map<String, Object>> getRoles();

	public List<KeyValueBean> selectAuthRole(String RoleId);
	
	public List<KeyValueBean> selectOperRole(String RoleId);
	
	public List getRoleByrolename(String sql);
	
	public void del(Long sysroleid);
	
	//public List<Map<String, Object>> getRolesByRoleTypeId();
	public List<Map<String, Object>> getAuthedRoleByCustId(String custid);
	
	public List<Map<String, Object>> getAuthedRolesByDeptId(String deptid);
	
	public List<Map<String, Object>> getAuthedRoleByCustAndRoleTypeId(Long custid);
	
	List<Map<String,Object>> getPageResult(String sql);
	
	List<KeyValueBean> selectRoleTypes();
	
	public List<Map<String, Object>> getUnAuthedRoles();
	
	//public List<Map<String, Object>> getRoleByRoleId();

	public List<Map<String, Object>> getRoles(String deptid,String custname);
	
	String deleteRoles(String[] id);
	
	String insertRole(RoleDataBean editBean);
	
	String updateRole(RoleDataBean editBean);
	
	//增加数据域判断获取客户角色 接口方法
	public List<Map<String, Object>> getRoles(Long custid);
	
	//角色同步状态修改
	void synStatusChange(String roleId);
}
