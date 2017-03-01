package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

public interface SysRoleFunctionDao 
{
	public List<Map<String, Object>> getAuthedFunctionsByRoleId(Long roleid);
	
	public List<Map<String, Object>> getCanAuthFunctionsByRoleId(Long roleid,Long auth_person_id);
	
	int getResultSize(String sql);
	
	List<Map<String,Object>> getPageResult(String sql);
	
	public void save(Long ROLE_ID,String [] FUNCTION_IDs);
	
	public void del(Long roleid);
	
	public void del(String [] sys_role_func_ids);
	
}
