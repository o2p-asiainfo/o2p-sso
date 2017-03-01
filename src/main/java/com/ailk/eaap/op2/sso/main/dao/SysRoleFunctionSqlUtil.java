package com.ailk.eaap.op2.sso.main.dao;

import com.ailk.eaap.op2.sso.main.model.SysRoleFunctionBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SysRoleFunctionSqlUtil {
	
	private static final String SEARCH_SQL = "select rf.sys_role_func_id sys_role_func_id,"+
											     "  r.sys_role_id sys_role_id,"+
											     " r.sys_role_name sys_role_name,"+
											     "  f.function_id function_id,"+
											     "  f.function_name function_name,"+
											     "  f.business_system_id business_system_id,"+
											     "  f.url url"+
											 " from sys_role r, sys_role_function rf, sys_function f"+
											" where rf.role_id = r.sys_role_id"+
											 "  and rf.function_id = f.function_id and r.sys_status_id=1";
	
	public static final String buildSearchFlowSql(SysRoleFunctionBean bean){
		StringBuffer buffer = new StringBuffer(SEARCH_SQL);
		
		if(bean.getRoleid()!=null && !ConvertUtil.isEmpty(bean.getRoleid().toString())){
			buffer.append(" and r.SYS_ROLE_ID = ").append(bean.getRoleid());
		}
		return buffer.toString();
	}
}
