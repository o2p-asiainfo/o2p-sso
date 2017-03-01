package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.DeptDataBean;
import com.ailk.eaap.op2.sso.main.model.SvcDeptBean;
import com.ailk.eaap.op2.sso.main.model.SysDept;


public interface SysDeptService
{
	String doLoadSelect();
	String doloadPreDept(String customerId);
	String doloadOtherDept(String customerId ,String deptId ,String sysPersonId);//1-14
	void doSearchDeptList(SvcDeptBean bean);
	String doDeleteDept(SvcDeptBean bean);
	DeptDataBean doSearchEditDept(String deptId);
	String doEditDept(SvcDeptBean bean);
	public List<SysDept> deptList(String customerId) ;
	//增加数据域判断 接口方法 3-16
	void doSearchDeptList(SvcDeptBean bean,String idType,String cusName);
	String doLoadSelect(Long cusId);
}
