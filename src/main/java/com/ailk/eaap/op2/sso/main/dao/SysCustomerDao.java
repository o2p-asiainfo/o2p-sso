package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

public interface SysCustomerDao 
{
	public List<Map<String, Object>> getAreas();
	
	public List<Map<String, Object>> getCustomers();
	
	public List<Map<String, Object>> getUnAuthedCustomer();
	
	public List<Map<String, Object>> getUnTotalAuthedCustomer();
	
	public int getResultSize(String sql) ;
	
	public void del(Long syscustomerid);
	
	public int checkDept(Long syscustomerid);
	
	public int checkRole(Long syscustomerid);
	
	public List<Map<String, Object>> getPageResult(String sql) ;
	
	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER);
	
	public void updateBycustomerid(Long sys_customer_id,String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER);
	
	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID);
	
	public void updateBycustomerid(Long sys_customer_id,String SYS_CUSTOMER_NAME,Long SYS_AREA_ID);
	//增加数据域判断参数 3-9
	public List<Map<String, Object>> getAreas(String sysUserType,String customerName);
	
	public List<Map<String, Object>> getUnAuthedCustomer(String cusName);
	
	public List<Map<String, Object>> getUnTotalAuthedCustomer(String cusName);
	
	public List<Map<String, Object>> getCustomers(Long cusId);
}
