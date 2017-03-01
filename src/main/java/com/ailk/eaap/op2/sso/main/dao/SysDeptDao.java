package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.DeptDataBean;
import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SysDept;

public interface SysDeptDao 
{
	int getResultSize(String sql);
	
	List<Map<String,Object>> getPageResult(String sql);
	
	List<KeyValueBean> selectDept(String deptId);
	
	public List<SysDept> deptList(String customerId) ;
	
	List<KeyValueBean> selectOtherDept(String customerId ,String deptId ,String sysPersonId);//1-14
	
	List<KeyValueBean> selectCustomer();
	
	List<KeyValueBean> selectStatus();
	
	String deleteDepts(String[] id);
	
	String insertDept(DeptDataBean editBean);
	
	String updateDept(DeptDataBean editBean);
	//3-16 增加数据域判断 DAO接口方法
	List<KeyValueBean> selectCustomer(Long cusId);
	
}
