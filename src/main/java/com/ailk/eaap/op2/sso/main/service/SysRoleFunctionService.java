package com.ailk.eaap.op2.sso.main.service;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.SysRoleFunctionBean;

public interface SysRoleFunctionService
{
	public List<Map<String, Object>> getAuthedFunctionsByRoleId(Long roleid);
	
	public List<Map<String, Object>> getCanAuthFunctionsByRoleId(Long roleid,Long auth_person_id);
	
	void doSearchFlow(SysRoleFunctionBean bean);
	
	public void save(Long deptid,String [] roles);
	
	public void del(Long deptid);
	
	void del(String [] dept_role_ids);
}
