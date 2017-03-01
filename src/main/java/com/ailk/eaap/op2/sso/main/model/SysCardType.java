package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysCardType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCardType implements java.io.Serializable {

	// Fields

	private Long sysCardTypeId;
	private String sysCardTypeName;
	private Set hisSysPersons = new HashSet(0);
	private Set sysPersons = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysCardType() {
	}

	/** full constructor */
	public SysCardType(String sysCardTypeName, Set hisSysPersons, Set sysPersons) {
		this.sysCardTypeName = sysCardTypeName;
		this.hisSysPersons = hisSysPersons;
		this.sysPersons = sysPersons;
	}

	// Property accessors

	public Long getSysCardTypeId() {
		return this.sysCardTypeId;
	}

	public void setSysCardTypeId(Long sysCardTypeId) {
		this.sysCardTypeId = sysCardTypeId;
	}

	public String getSysCardTypeName() {
		return this.sysCardTypeName;
	}

	public void setSysCardTypeName(String sysCardTypeName) {
		this.sysCardTypeName = sysCardTypeName;
	}

	public Set getHisSysPersons() {
		return this.hisSysPersons;
	}

	public void setHisSysPersons(Set hisSysPersons) {
		this.hisSysPersons = hisSysPersons;
	}

	public Set getSysPersons() {
		return this.sysPersons;
	}

	public void setSysPersons(Set sysPersons) {
		this.sysPersons = sysPersons;
	}

}