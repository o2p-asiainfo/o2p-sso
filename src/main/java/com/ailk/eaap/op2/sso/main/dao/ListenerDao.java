package com.ailk.eaap.op2.sso.main.dao;

import com.ailk.eaap.op2.sso.main.model.RequestContractBean;

public interface ListenerDao {
	public int queryPersonNum(String personId);

	public String obtainSerialNumber();

	public RequestContractBean obtainRequestheader();
	
	public String obtainUrl();
}
