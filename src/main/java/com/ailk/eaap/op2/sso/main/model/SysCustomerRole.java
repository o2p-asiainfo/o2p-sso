package com.ailk.eaap.op2.sso.main.model;

/**
 * SysCustomerRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCustomerRole implements java.io.Serializable {

	// Fields

	private Long sysCustomerRoleId;
	private SysRole sysRole;
	private SysCustomer sysCustomer;

	// Constructors

	/** default constructor */
	public SysCustomerRole() {
	}

	/** full constructor */
	public SysCustomerRole(SysRole sysRole, SysCustomer sysCustomer) {
		this.sysRole = sysRole;
		this.sysCustomer = sysCustomer;
	}

	// Property accessors

	public Long getSysCustomerRoleId() {
		return this.sysCustomerRoleId;
	}

	public void setSysCustomerRoleId(Long sysCustomerRoleId) {
		this.sysCustomerRoleId = sysCustomerRoleId;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysCustomer getSysCustomer() {
		return this.sysCustomer;
	}

	public void setSysCustomer(SysCustomer sysCustomer) {
		this.sysCustomer = sysCustomer;
	}

}