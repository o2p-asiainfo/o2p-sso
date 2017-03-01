package com.ailk.eaap.op2.sso.main.model;

public class FlowBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String flowCd;
	private String flowNm;
	public String getFlowCd() {
		return flowCd;
	}
	public void setFlowCd(String flowCd) {
		this.flowCd = flowCd;
	}
	public String getFlowNm() {
		return flowNm;
	}
	public void setFlowNm(String flowNm) {
		this.flowNm = flowNm;
	}
}
