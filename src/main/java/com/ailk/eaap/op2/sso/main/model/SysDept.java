package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门表（SYS_DEPT）
 * @author zhaobl
 *
 */
public class SysDept implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long deptId;//部门编号
	private String deptName;//部门名称
	private Long parentDeptId;//父部门编号
	private String parentDeptName; //父部门名称
	private Long sysCustomerId;//客户编号
	private String sysCustomerName; //客户名称
	private Long sysStatusId;//状态编号
	private String sysStatusName; //状态名称

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(Long parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public Long getSysCustomerId() {
		return sysCustomerId;
	}

	public void setSysCustomerId(Long sysCustomerId) {
		this.sysCustomerId = sysCustomerId;
	}

	public Long getSysStatusId() {
		return sysStatusId;
	}

	public void setSysStatusId(Long sysStatusId) {
		this.sysStatusId = sysStatusId;
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public String getSysCustomerName() {
		return sysCustomerName;
	}

	public void setSysCustomerName(String sysCustomerName) {
		this.sysCustomerName = sysCustomerName;
	}

	public String getSysStatusName() {
		return sysStatusName;
	}

	public void setSysStatusName(String sysStatusName) {
		this.sysStatusName = sysStatusName;
	}

}