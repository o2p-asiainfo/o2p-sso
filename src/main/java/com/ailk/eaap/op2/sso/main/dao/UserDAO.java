package com.ailk.eaap.op2.sso.main.dao;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;

public interface UserDAO 
{
	int getResultSize(String sql);
	
	List<Map<String,Object>> getPageResult(String sql);
	
	List<Map<String,Object>> getPersonId(String sql);
	
	List<KeyValueBean> selectPassword(String sys_person_id);
	
	public void insertDeptUser(Long deptid,Long userid);
	
	int update(String sql) ;
	
	public void updatePassword(SYSPERSON bean);
	
	public int authCheck(Long sys_person_id);
	
	public List<Map<String, Object>> getPass(String sys_person_id);
}
