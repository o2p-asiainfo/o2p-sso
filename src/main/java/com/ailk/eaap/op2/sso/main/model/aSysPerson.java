package com.ailk.eaap.op2.sso.main.model;

import java.io.Serializable;
/**
 * 员工表（SYS_PERSON）
 * @author zhaobl
 *
 */
public class aSysPerson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long sysPersonId;
	private String name;//姓名
	private String mobile;//手机
	private String password;//密码
	private String lastPassword;//上一次密码
	private String email;//邮箱
	private Long sysIdTypeId;//身份分类编号
	private String sysIdTypeName;
	private Long sysCardTypeId;//证件类型编号
	private String sysCardTypeName;
	private Long sysAreaId;//区域编号
	private SysArea sysArea;//地区表或省份表 bean
	private SysDept sysDept;//部门表 bean
	private String cardNumber;//证件号码/工号
	private String sysUserType;//用户类型
	private Long sysStatusId;//状态编号
	private String sysStatusName;
	private String stateDate;//状态时间
	private String pwdLastUpTime;//密码最新修改时间
	private String pwdEffTime;//密码失效失效时间
	private String tenantId;			//租户ID
	private String tenantCode;	//租户编码
	
	public Long getSysPersonId() {
		return sysPersonId;
	}
	public void setSysPersonId(Long sysPersonId) {
		this.sysPersonId = sysPersonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastPassword() {
		return lastPassword;
	}
	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getSysIdTypeId() {
		return sysIdTypeId;
	}
	public void setSysIdTypeId(Long sysIdTypeId) {
		this.sysIdTypeId = sysIdTypeId;
	}
	public String getSysIdTypeName() {
		return sysIdTypeName;
	}
	public void setSysIdTypeName(String sysIdTypeName) {
		this.sysIdTypeName = sysIdTypeName;
	}
	public Long getSysCardTypeId() {
		return sysCardTypeId;
	}
	public void setSysCardTypeId(Long sysCardTypeId) {
		this.sysCardTypeId = sysCardTypeId;
	}
	public String getSysCardTypeName() {
		return sysCardTypeName;
	}
	public void setSysCardTypeName(String sysCardTypeName) {
		this.sysCardTypeName = sysCardTypeName;
	}
	public Long getSysAreaId() {
		return sysAreaId;
	}
	public void setSysAreaId(Long sysAreaId) {
		this.sysAreaId = sysAreaId;
	}
	public SysArea getSysArea() {
		return sysArea;
	}
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}
	public SysDept getSysDept() {
		return sysDept;
	}
	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getSysUserType() {
		return sysUserType;
	}
	public void setSysUserType(String sysUserType) {
		this.sysUserType = sysUserType;
	}
	public Long getSysStatusId() {
		return sysStatusId;
	}
	public void setSysStatusId(Long sysStatusId) {
		this.sysStatusId = sysStatusId;
	}
	public String getSysStatusName() {
		return sysStatusName;
	}
	public void setSysStatusName(String sysStatusName) {
		this.sysStatusName = sysStatusName;
	}
	public String getStateDate() {
		return stateDate;
	}
	public void setStateDate(String stateDate) {
		this.stateDate = stateDate;
	}
	public String getPwdLastUpTime() {
		return pwdLastUpTime;
	}
	public void setPwdLastUpTime(String pwdLastUpTime) {
		this.pwdLastUpTime = pwdLastUpTime;
	}
	public String getPwdEffTime() {
		return pwdEffTime;
	}
	public void setPwdEffTime(String pwdEffTime) {
		this.pwdEffTime = pwdEffTime;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
}
