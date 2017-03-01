package com.ailk.eaap.op2.sso.main.service;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysCustomerBean;

public interface SysCustomerService 
{
	List<Map<String,Object>> getPageResult(String sql);
	
	public List<SysArea> getAreas();
	
	public List<SysCustomer> getCustomers();
	
	public List<SysCustomer> getUnAuthedCustomer();
	
	public List<SysCustomer> getUnTotalAuthedCustomer();
	
	public void del(Long syscustomerid);
	
	public int checkDept(Long syscustomerid);
	
	public int checkRole(Long syscustomerid);
	
	//public void doSearch(SysCustomerBean bean);
	
	//3-9 增加数据域判断参数
	public void doSearch(SysCustomerBean bean,String sysUserType,String customerName);
	
	public List<SysArea> getAreas(String sysUserType,String customerName);
	
	public void insert(String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER);
	
	public void updateBycustomerid(Long sys_customer_id,String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER);
	
	public void insert(String SYS_CUSTOMER_NAME,Long SYS_AREA_ID);
	
	public void updateBycustomerid(Long sys_customer_id,String SYS_CUSTOMER_NAME,Long SYS_AREA_ID);
	
	public List<SysCustomer> getUnAuthedCustomer(String cusName);
	
	//3-17 修改增加数据域参数 方法接口
	public List<SysCustomer> getUnTotalAuthedCustomer(String cusName);

	public List<SysCustomer> getCustomers(Long cusId);
}
