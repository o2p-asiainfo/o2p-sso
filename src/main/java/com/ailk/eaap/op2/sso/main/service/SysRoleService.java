package com.ailk.eaap.op2.sso.main.service;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.RoleDataBean;
import com.ailk.eaap.op2.sso.main.model.SvcRoleBean;
import com.ailk.eaap.op2.sso.main.model.SysRole;

public interface SysRoleService {

	public List<SysRole> getRoles();
	
	public List<SysRole> getUnAuthedRoles();
	
	//public List<Map<String, Object>> getRoleByRoleId();
	
	public void del(Long sysroleid);
	
	public SysRole getRoleByrolename(String rolename);
	
	//public List<Map<String, Object>> getRolesByRoleTypeId();
	//alert by wanggm 02-04
	public List<Map<String, Object>> getRoles(String deptid,String custname);
	
	public List<Map<String, Object>> getAuthedRoleByCustId(String custid);
	
	public List<Map<String, Object>> getAuthedRolesByDeptId(String deptid);
	
	public List<Map<String, Object>> getAuthedRoleByCustAndRoleTypeId(Long custid);
	
	public String doLoadSelect();
	public void doSearchRoleList(SvcRoleBean bean);
	public String doDeleteRole(SvcRoleBean bean);
	public RoleDataBean doSearchEditRole(String roleId);
	public String doEditRole(SvcRoleBean bean);
	public String doloadPreAuthRole(String customerId);
	public String doloadPreOperRole(String customerId);
	
	//3-16 增加数据域判断 获取公司所属角色
	public List<SysRole> getRoles(Long custid);
	
	//角色同步状态修改
	void synStatusChange(String roleId);
}
