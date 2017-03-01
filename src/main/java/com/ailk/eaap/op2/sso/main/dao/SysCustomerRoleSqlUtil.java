package com.ailk.eaap.op2.sso.main.dao;

import com.ailk.eaap.op2.sso.main.model.SysCustomerRoleBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SysCustomerRoleSqlUtil {
	
	private static final String SEARCH_SQL = "select distinct c.sys_customer_id sys_customer_id," +
			"                c.sys_customer_name sys_customer_name," +
			"                c.sys_card_number sys_card_number," +
			"                r.sys_role_id sys_role_id," +
			"                r.sys_role_name sys_role_name," +
			"                cr.SYS_CUSTOMER_ROLE_ID sys_customer_role_id" +
			"  from sys_customer c, sys_role r, sys_customer_role cr " +
			" where cr.sys_customer_id = c.sys_customer_id " +
			"   and cr.sys_role_id = r.sys_role_id" ;
	
	public static final String buildSearchFlowSql(SysCustomerRoleBean bean){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		
		if(!ConvertUtil.isEmpty(bean.getSyscustomerid())){
			buffer.append(" and c.sys_customer_id = ").append(bean.getSyscustomerid()).append("");
		}
		return buffer.toString();
	}
	//3-16 增加数据域判断
	public static final String buildSearchFlowSql(SysCustomerRoleBean bean,String idType, String customerName){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		if((idType.equals("1"))||(idType.equals("2") && customerName.equals("电信集团"))){
			buffer.append(" and 1=1 ");
		}
		//if(idType.equals("2") && !customerName.equals("电信集团"))
		else{
			buffer.append(" and c.sys_customer_name = '").append(customerName).append("'");
		}
		if(!ConvertUtil.isEmpty(bean.getSyscustomerid())){
			buffer.append(" and c.sys_customer_id = ").append(bean.getSyscustomerid()).append("");
		}
		return buffer.toString();
	}
}
