package com.ailk.eaap.op2.sso.main.model;

/*
 * author nanqing
 */
public class RequestContractBean {
	private String busCode;
	private String serviceCode;
	private String serviceContractVer;
	private String actionCode;
	private String serviceLevel;
	private String srcOrgId;
	private String srcSysId;
	private String dstOrgId;
	private String dstSysId;
	private String srcSysSign;
	public final static String TCPCONT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<ContractRoot>"
			+ "<TcpCont>"
			+ "<BusCode>busCode</BusCode>"
			+ "<ServiceCode>serviceCode</ServiceCode>"
			+ "<ServiceContractVer>serviceContractVer</ServiceContractVer>"
			+ " <ActionCode>actionCode</ActionCode>"
			+ "<TransactionID>transactionID</TransactionID>"
			+ "<ServiceLevel>serviceLevel</ServiceLevel>"
			+ "<SrcOrgID>srcOrgID</SrcOrgID>"
			+ "<SrcSysID>srcSysID</SrcSysID>"
			+ "<SrcSysSign>srcSysSign</SrcSysSign>"
			+ "<DstOrgID>dstOrgID</DstOrgID>"
			+ "<DstSysID>dstSysID</DstSysID>"
			+ "<ReqTime>reqTime</ReqTime>" + "</TcpCont>" + "</ContractRoot>";

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceContractVer() {
		return serviceContractVer;
	}

	public void setServiceContractVer(String serviceContractVer) {
		this.serviceContractVer = serviceContractVer;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getSrcOrgId() {
		return srcOrgId;
	}

	public void setSrcOrgId(String srcOrgId) {
		this.srcOrgId = srcOrgId;
	}

	public String getSrcSysId() {
		return srcSysId;
	}

	public void setSrcSysId(String srcSysId) {
		this.srcSysId = srcSysId;
	}

	public String getDstOrgId() {
		return dstOrgId;
	}

	public void setDstOrgId(String dstOrgId) {
		this.dstOrgId = dstOrgId;
	}

	public String getDstSysId() {
		return dstSysId;
	}

	public void setDstSysId(String dstSysId) {
		this.dstSysId = dstSysId;
	}

	public String getSrcSysSign() {
		return srcSysSign;
	}

	public void setSrcSysSign(String srcSysSign) {
		this.srcSysSign = srcSysSign;
	}

}
