package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysStatus implements java.io.Serializable {

	// Fields

	private Long sysStatusId;
	private String sysStatusName;
	private Set sysRoles = new HashSet(0);
	private Set sysFunctions = new HashSet(0);
	private Set sysBusinessSystems = new HashSet(0);
	private Set sysPersons = new HashSet(0);
	private Set hisSysPersons = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysStatus() {
	}

	/** full constructor */
	public SysStatus(String sysStatusName, Set sysRoles, Set sysFunctions,
			Set sysBusinessSystems, Set sysPersons, Set hisSysPersons) {
		this.sysStatusName = sysStatusName;
		this.sysRoles = sysRoles;
		this.sysFunctions = sysFunctions;
		this.sysBusinessSystems = sysBusinessSystems;
		this.sysPersons = sysPersons;
		this.hisSysPersons = hisSysPersons;
	}

	// Property accessors

	public Long getSysStatusId() {
		return this.sysStatusId;
	}

	public void setSysStatusId(Long sysStatusId) {
		this.sysStatusId = sysStatusId;
	}

	public String getSysStatusName() {
		return this.sysStatusName;
	}

	public void setSysStatusName(String sysStatusName) {
		this.sysStatusName = sysStatusName;
	}

	public Set getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set sysRoles) {
		this.sysRoles = sysRoles;
	}

	public Set getSysFunctions() {
		return this.sysFunctions;
	}

	public void setSysFunctions(Set sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public Set getSysBusinessSystems() {
		return this.sysBusinessSystems;
	}

	public void setSysBusinessSystems(Set sysBusinessSystems) {
		this.sysBusinessSystems = sysBusinessSystems;
	}

	public Set getSysPersons() {
		return this.sysPersons;
	}

	public void setSysPersons(Set sysPersons) {
		this.sysPersons = sysPersons;
	}

	public Set getHisSysPersons() {
		return this.hisSysPersons;
	}

	public void setHisSysPersons(Set hisSysPersons) {
		this.hisSysPersons = hisSysPersons;
	}

}