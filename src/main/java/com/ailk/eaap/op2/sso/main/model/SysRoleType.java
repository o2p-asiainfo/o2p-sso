package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRoleType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRoleType implements java.io.Serializable {

	// Fields

	private Long sysRoleTypeId;
	private String sysRoleTypeName;
	private Set sysRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysRoleType() {
	}

	/** full constructor */
	public SysRoleType(String sysRoleTypeName, Set sysRoles) {
		this.sysRoleTypeName = sysRoleTypeName;
		this.sysRoles = sysRoles;
	}

	// Property accessors

	public Long getSysRoleTypeId() {
		return this.sysRoleTypeId;
	}

	public void setSysRoleTypeId(Long sysRoleTypeId) {
		this.sysRoleTypeId = sysRoleTypeId;
	}

	public String getSysRoleTypeName() {
		return this.sysRoleTypeName;
	}

	public void setSysRoleTypeName(String sysRoleTypeName) {
		this.sysRoleTypeName = sysRoleTypeName;
	}

	public Set getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set sysRoles) {
		this.sysRoles = sysRoles;
	}

}