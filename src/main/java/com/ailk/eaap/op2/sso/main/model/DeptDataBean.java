package com.ailk.eaap.op2.sso.main.model;


/**
 * SysDept entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DeptDataBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String deptId;
	private String deptName;
	private String parentDeptId;
	private String customerId;
	private String statusId;
	
	private String statusNm;
	private String customerNm;
	private String parentDeptNm;
	public String getStatusNm() {
		return statusNm;
	}
	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getParentDeptNm() {
		return parentDeptNm;
	}
	public void setParentDeptNm(String parentDeptNm) {
		this.parentDeptNm = parentDeptNm;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParentDeptId() {
		return parentDeptId;
	}
	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

}