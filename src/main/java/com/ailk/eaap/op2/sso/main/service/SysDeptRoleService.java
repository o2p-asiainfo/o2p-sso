package com.ailk.eaap.op2.sso.main.service;

import com.ailk.eaap.op2.sso.main.model.SysDeptRoleBean;

public interface SysDeptRoleService
{
	void doSearchFlow(SysDeptRoleBean bean);
	
	void save(Long deptid,String [] roles);
	
	void del(Long deptid);
	
	void del(String [] dept_role_ids);
	
	//3-16 增加数据域判断 
	void doSearchFlow(SysDeptRoleBean bean,String idType, String cusName);
}
