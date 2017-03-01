package com.ailk.eaap.op2.sso.main.dao;

import com.ailk.eaap.op2.sso.main.model.SysDeptRoleBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SysDeptRoleSqlUtil {
	
	private static final String SEARCH_SQL = "select r.sys_role_id as sys_role_id,r.sys_role_name as sys_role_name," 
												+	"dr.DEPT_ROLE_ID as dept_role_id," 
												+"d.dept_id as dept_id ,d.dept_name as dept_name, " +
														" c.sys_customer_name  sys_customer_name " 
											//	+" from xrb.sys_role r,xrb.sys_dept_role dr,xrb.sys_dept d,xrb.sys_customer c "
												+" from sys_role r,sys_dept_role dr,sys_dept d,sys_customer c "
												+" where dr.dept_id = d.dept_id"
												+" and dr.role_id = r.sys_role_id and d.sys_customer_id = c.sys_customer_id ";

	public static final String buildSearchFlowSql(SysDeptRoleBean bean){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		
		if(!ConvertUtil.isEmpty(bean.getDeptid())){
			buffer.append(" and d.dept_id = ").append(bean.getDeptid());
		}
		
		if(!ConvertUtil.isEmpty(bean.getCustomerid())){
			buffer.append(" and c.SYS_CUSTOMER_ID = ").append(bean.getCustomerid());
		}
		
		return buffer.toString();
	}
	//3-16 增加数据域 判断条件 方法
	public static final String buildSearchFlowSql(SysDeptRoleBean bean,String idType, String cusName){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		if((idType.equals("1"))||(idType.equals("2") && cusName.equals("电信集团"))){
			buffer.append(" and 1=1 ");
		}
		if(idType.equals("2") && !cusName.equals("电信集团")){
			buffer.append(" and c.sys_customer_name = '").append(cusName).append("'");
		}
		if(!ConvertUtil.isEmpty(bean.getDeptid())){
			buffer.append(" and d.dept_id = ").append(bean.getDeptid());
		}
		
		if(!ConvertUtil.isEmpty(bean.getCustomerid())){
			buffer.append(" and c.SYS_CUSTOMER_ID = ").append(bean.getCustomerid());
		}
		
		return buffer.toString();
	}
}
