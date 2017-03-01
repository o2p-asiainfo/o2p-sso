package com.ailk.eaap.op2.sso.main.service;

import com.ailk.eaap.op2.sso.main.model.SysCustomerRoleBean;

public interface SysCustomerRoleService
{
	void doSearch(SysCustomerRoleBean bean);
	
	void save(Long deptid,String [] roles);
	
	void del(Long deptid);
	
	void del(String [] dept_role_ids);
	//3-16 增加数据域判断方法
	void doSearch(SysCustomerRoleBean bean,String idType, String customerName);
}
