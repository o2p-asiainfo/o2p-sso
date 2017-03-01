package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysFunction;

public interface SysFunctionService {
	public void insert(SysFunction function);
	
	public void update(SysFunction function);
	
	public void del(String [] function_ids);
	
	public List select();
}
