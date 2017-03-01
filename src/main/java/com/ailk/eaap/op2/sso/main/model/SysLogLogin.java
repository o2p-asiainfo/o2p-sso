package com.ailk.eaap.op2.sso.main.model;

import java.io.Serializable;
/**
 * 登录日志
 * @author zhaobl
 *
 */
public class SysLogLogin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sysLoginId;

	private String cardNumber;
	
	private String sysPersonId;

	private String loginTime;

	private String logoutTime;

	private String ip;
	
	private String authTranId;
	
	private String authBegDate;
	
	private String authEndDate;
	
	private String authRspType;

	private String authRspCode;

	private String authRspDesc;

	public String getSysLoginId() {
		return sysLoginId;
	}

	public void setSysLoginId(String sysLoginId) {
		this.sysLoginId = sysLoginId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSysPersonId() {
		return sysPersonId;
	}

	public void setSysPersonId(String sysPersonId) {
		this.sysPersonId = sysPersonId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAuthTranId() {
		return authTranId;
	}

	public void setAuthTranId(String authTranId) {
		this.authTranId = authTranId;
	}

	public String getAuthBegDate() {
		return authBegDate;
	}

	public void setAuthBegDate(String authBegDate) {
		this.authBegDate = authBegDate;
	}

	public String getAuthEndDate() {
		return authEndDate;
	}

	public void setAuthEndDate(String authEndDate) {
		this.authEndDate = authEndDate;
	}

	public String getAuthRspType() {
		return authRspType;
	}

	public void setAuthRspType(String authRspType) {
		this.authRspType = authRspType;
	}

	public String getAuthRspCode() {
		return authRspCode;
	}

	public void setAuthRspCode(String authRspCode) {
		this.authRspCode = authRspCode;
	}

	public String getAuthRspDesc() {
		return authRspDesc;
	}

	public void setAuthRspDesc(String authRspDesc) {
		this.authRspDesc = authRspDesc;
	}

	
}