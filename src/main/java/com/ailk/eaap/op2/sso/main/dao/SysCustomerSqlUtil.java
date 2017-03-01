package com.ailk.eaap.op2.sso.main.dao;

import com.ailk.eaap.op2.sso.main.model.SysCustomerBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SysCustomerSqlUtil {
	
	private static final String SEARCH_SQL = "select c.sys_customer_id sys_customer_id," +
			"       c.sys_customer_name sys_customer_name," +
			"       c.sys_area_id sys_area_id," +
			"       a.sys_area_name sys_area_name," +
			"       c.sys_card_type_id sys_card_type_id," +
			"       t.sys_card_type_name sys_card_type_name," +
			"       c.sys_card_number sys_card_number" +
			"  from sys_customer c, sys_area a, sys_card_type t " +
			" where c.sys_area_id = a.sys_area_id " +
			"   and c.sys_card_type_id = t.sys_card_type_id";
	//alert by wanggm 3-9 增加数据域判断
	public static final String buildSearchFlowSql(SysCustomerBean bean,String sysUserType,String customerName){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		if((sysUserType.equals("1"))||(sysUserType.equals("2") && customerName.equals("电信集团"))){
			buffer.append(" and 1=1 ");
		}
		//if(sysUserType.equals("2")&& !customerName.equals("电信集团"))
		else
		{
			buffer.append(" and c.sys_customer_name = '").append(customerName).append("'");
		}
		if(bean.getAreaid()!= null && !ConvertUtil.isEmpty(bean.getAreaid().toString())){
			buffer.append(" and c.sys_area_id =  ").append(bean.getAreaid());
		}
		return buffer.toString();
	}
}
