package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysFunction;

public interface SysFunctionDao {

	public void insert(SysFunction function);
	
	public void update(SysFunction function);
	
	public void del(String [] function_ids);
	
	public List select();
}
