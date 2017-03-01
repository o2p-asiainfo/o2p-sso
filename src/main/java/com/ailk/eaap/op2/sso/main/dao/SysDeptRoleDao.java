package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

public interface SysDeptRoleDao 
{
	int getResultSize(String sql);
	
	List<Map<String,Object>> getPageResult(String sql);
	
	public void save(Long deptid,String [] roles);
	
	public void del(Long deptid);
	
	void del(String [] dept_role_ids);
	
}
