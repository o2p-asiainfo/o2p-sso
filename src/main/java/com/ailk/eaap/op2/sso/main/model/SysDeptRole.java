package com.ailk.eaap.op2.sso.main.model;

/**
 * SysDeptRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysDeptRole implements java.io.Serializable {

	// Fields

	private Long deptRoleId;
	private SysDept sysDept;
	private SysRole sysRole;

	// Constructors

	/** default constructor */
	public SysDeptRole() {
	}

	/** full constructor */
	public SysDeptRole(SysDept sysDept, SysRole sysRole) {
		this.sysDept = sysDept;
		this.sysRole = sysRole;
	}

	// Property accessors

	public Long getDeptRoleId() {
		return this.deptRoleId;
	}

	public void setDeptRoleId(Long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public SysDept getSysDept() {
		return this.sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}