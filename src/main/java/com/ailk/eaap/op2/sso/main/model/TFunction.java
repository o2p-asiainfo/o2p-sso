package com.ailk.eaap.op2.sso.main.model;

/**
 * TFunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TFunction implements java.io.Serializable {

	// Fields
	public String sysPkName = "functionId";
	public String sysTableName = "T_FUNCTION"; 
	
	private String functionId;
	private String businessSystemId;
	private String sysStatusId;
	private String functionName;
	private String parentFunctionId;
	private String url;
	private String shownum;

	// Constructors

	/** default constructor */
	public TFunction() {
	}

	/** minimal constructor */
	public TFunction(String functionId, String url) {
		this.functionId = functionId;
		this.url = url;
	}

	// Property accessors

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentFunctionId() {
		return this.parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShownum() {
		return this.shownum;
	}

	public void setShownum(String shownum) {
		this.shownum = shownum;
	}

	public String getSysPkName() {
		return sysPkName;
	}

	public void setSysPkName(String sysPkName) {
		this.sysPkName = sysPkName;
	}

	public String getSysTableName() {
		return sysTableName;
	}

	public void setSysTableName(String sysTableName) {
		this.sysTableName = sysTableName;
	}

	public String getBusinessSystemId() {
		return businessSystemId;
	}

	public void setBusinessSystemId(String businessSystemId) {
		this.businessSystemId = businessSystemId;
	}

	public String getSysStatusId() {
		return sysStatusId;
	}

	public void setSysStatusId(String sysStatusId) {
		this.sysStatusId = sysStatusId;
	}

}