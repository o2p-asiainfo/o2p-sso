package com.ailk.eaap.op2.sso.main.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户表（SYS_CUSTOMER）
 * @author zhaobl
 *
 */

public class SysCustomer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String sysCustomerId; //客户ID
	private String sysCustomerName; //客户名称
	private String sysStatusId; //状态ID
	private String sysStatusName; //状态名称
	private String sysAreaId; //区域编号
	private String sysAreaName; //区域名称
	private String sysCardTypeId;//证件类型编号
	private String sysCardNumber; //证件号码
	
	
	public String getSysCustomerId() {
		return sysCustomerId;
	}
	public void setSysCustomerId(String sysCustomerId) {
		this.sysCustomerId = sysCustomerId;
	}
	public String getSysCustomerName() {
		return sysCustomerName;
	}
	public void setSysCustomerName(String sysCustomerName) {
		this.sysCustomerName = sysCustomerName;
	}
	public String getSysStatusId() {
		return sysStatusId;
	}
	public void setSysStatusId(String sysStatusId) {
		this.sysStatusId = sysStatusId;
	}
	public String getSysStatusName() {
		return sysStatusName;
	}
	public void setSysStatusName(String sysStatusName) {
		this.sysStatusName = sysStatusName;
	}
	public String getSysAreaId() {
		return sysAreaId;
	}
	public void setSysAreaId(String sysAreaId) {
		this.sysAreaId = sysAreaId;
	}
	public String getSysAreaName() {
		return sysAreaName;
	}
	public void setSysAreaName(String sysAreaName) {
		this.sysAreaName = sysAreaName;
	}
	public String getSysCardTypeId() {
		return sysCardTypeId;
	}
	public void setSysCardTypeId(String sysCardTypeId) {
		this.sysCardTypeId = sysCardTypeId;
	}
	public String getSysCardNumber() {
		return sysCardNumber;
	}
	public void setSysCardNumber(String sysCardNumber) {
		this.sysCardNumber = sysCardNumber;
	}
	
	
	
	
//	
//	// Fields
//
//	/**
//	 * 
//	 */
//	private Long sysCustomerId;
//	private SysArea sysArea;
//	private SysCardType sysCardType;
//	private String sysCustomerName;
//	private String sysCardNumber;
//	private Set sysDepts = new HashSet(0);
//	private Set sysCustomerRoles = new HashSet(0);
//
//	// Constructors
//
//	/** default constructor */
//	public SysCustomer() {
//	}
//
//	/** full constructor */
//	public SysCustomer(SysArea sysArea, SysCardType sysCardType,
//			String sysCustomerName, String sysCardNumber, Set sysDepts,
//			Set sysCustomerRoles) {
//		this.sysArea = sysArea;
//		this.sysCardType = sysCardType;
//		this.sysCustomerName = sysCustomerName;
//		this.sysCardNumber = sysCardNumber;
//		this.sysDepts = sysDepts;
//		this.sysCustomerRoles = sysCustomerRoles;
//	}
//
//	// Property accessors
//
//	public Long getSysCustomerId() {
//		return this.sysCustomerId;
//	}
//
//	public void setSysCustomerId(Long sysCustomerId) {
//		this.sysCustomerId = sysCustomerId;
//	}
//
//	public SysArea getSysArea() {
//		return this.sysArea;
//	}
//
//	public void setSysArea(SysArea sysArea) {
//		this.sysArea = sysArea;
//	}
//
//	public SysCardType getSysCardType() {
//		return this.sysCardType;
//	}
//
//	public void setSysCardType(SysCardType sysCardType) {
//		this.sysCardType = sysCardType;
//	}
//
//	public String getSysCustomerName() {
//		return this.sysCustomerName;
//	}
//
//	public void setSysCustomerName(String sysCustomerName) {
//		this.sysCustomerName = sysCustomerName;
//	}
//
//	public String getSysCardNumber() {
//		return this.sysCardNumber;
//	}
//
//	public void setSysCardNumber(String sysCardNumber) {
//		this.sysCardNumber = sysCardNumber;
//	}
//
//	public Set getSysDepts() {
//		return this.sysDepts;
//	}
//
//	public void setSysDepts(Set sysDepts) {
//		this.sysDepts = sysDepts;
//	}
//
//	public Set getSysCustomerRoles() {
//		return this.sysCustomerRoles;
//	}
//
//	public void setSysCustomerRoles(Set sysCustomerRoles) {
//		this.sysCustomerRoles = sysCustomerRoles;
//	}

}