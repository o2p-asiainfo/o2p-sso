package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 业务系统表 SYS_BUSINESS_SYSTEM
 * @author zhaobl
 *
 */

public class SysBusinessSystem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String businessSystemId; //业务系统编号
	private String businessSystemName; //业务系统名称
	private String sysStatusId; //状态编号
	private String shownum; //排序号
	private String domain; //域名
	private String ip; //IP地址
	private String port; //端口号
	
	public String getBusinessSystemId() {
		return businessSystemId;
	}
	public void setBusinessSystemId(String businessSystemId) {
		this.businessSystemId = businessSystemId;
	}
	public String getBusinessSystemName() {
		return businessSystemName;
	}
	public void setBusinessSystemName(String businessSystemName) {
		this.businessSystemName = businessSystemName;
	}
	public String getSysStatusId() {
		return sysStatusId;
	}
	public void setSysStatusId(String sysStatusId) {
		this.sysStatusId = sysStatusId;
	}
	public String getShownum() {
		return shownum;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
	
	
	
	
//	// Fields
//
//	private Long businessSystemId;
//	private SysStatus sysStatus;
//	private String businessSystemName;
//	private Long shownum;
//	private String domain;
//	private String ip;
//	private Long port;
//	private Set sysFunctions = new HashSet(0);
//
//	// Constructors
//
//	/** default constructor */
//	public SysBusinessSystem() {
//	}
//
//	/** minimal constructor */
//	public SysBusinessSystem(String businessSystemName, String ip) {
//		this.businessSystemName = businessSystemName;
//		this.ip = ip;
//	}
//
//	/** full constructor */
//	public SysBusinessSystem(SysStatus sysStatus, String businessSystemName,
//			Long shownum, String domain, String ip, Long port, Set sysFunctions) {
//		this.sysStatus = sysStatus;
//		this.businessSystemName = businessSystemName;
//		this.shownum = shownum;
//		this.domain = domain;
//		this.ip = ip;
//		this.port = port;
//		this.sysFunctions = sysFunctions;
//	}
//
//	// Property accessors
//
//	public Long getBusinessSystemId() {
//		return this.businessSystemId;
//	}
//
//	public void setBusinessSystemId(Long businessSystemId) {
//		this.businessSystemId = businessSystemId;
//	}
//
//	public SysStatus getSysStatus() {
//		return this.sysStatus;
//	}
//
//	public void setSysStatus(SysStatus sysStatus) {
//		this.sysStatus = sysStatus;
//	}
//
//	public String getBusinessSystemName() {
//		return this.businessSystemName;
//	}
//
//	public void setBusinessSystemName(String businessSystemName) {
//		this.businessSystemName = businessSystemName;
//	}
//
//	public Long getShownum() {
//		return this.shownum;
//	}
//
//	public void setShownum(Long shownum) {
//		this.shownum = shownum;
//	}
//
//	public String getDomain() {
//		return this.domain;
//	}
//
//	public void setDomain(String domain) {
//		this.domain = domain;
//	}
//
//	public String getIp() {
//		return this.ip;
//	}
//
//	public void setIp(String ip) {
//		this.ip = ip;
//	}
//
//	public Long getPort() {
//		return this.port;
//	}
//
//	public void setPort(Long port) {
//		this.port = port;
//	}
//
//	public Set getSysFunctions() {
//		return this.sysFunctions;
//	}
//
//	public void setSysFunctions(Set sysFunctions) {
//		this.sysFunctions = sysFunctions;
//	}

}