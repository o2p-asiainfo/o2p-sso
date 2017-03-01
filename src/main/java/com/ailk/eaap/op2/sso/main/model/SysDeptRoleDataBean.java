package com.ailk.eaap.op2.sso.main.model;

public class SysDeptRoleDataBean {
	private Long deptId;
	private String deptName;
	private Long sysRoleId;
	private String sysRoleName;
	private Long deptRoleId;
	private String customerName;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getSysRoleId() {
		return sysRoleId;
	}
	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	public String getSysRoleName() {
		return sysRoleName;
	}
	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}
	public Long getDeptRoleId() {
		return deptRoleId;
	}
	public void setDeptRoleId(Long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}
}
