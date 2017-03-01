package com.ailk.eaap.op2.sso.main.model;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RoleDataBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysRoleId;
	private String sysRoleNm;
	private String sysRoleTypeId;
	private String sysRoleTypeNm;
	private String sysStatusId;
	private String sysStatusNm;
	private String shownum;
	
	private String synStatusName;
	private String synStateTime;
	private String synTimes;
	
	public String getSynStateTime() {
		return synStateTime;
	}
	public void setSynStateTime(String synStateTime) {
		this.synStateTime = synStateTime;
	}
	public String getSynTimes() {
		return synTimes;
	}
	public void setSynTimes(String synTimes) {
		this.synTimes = synTimes;
	}
	public String getSynStatusName() {
		return synStatusName;
	}
	public void setSynStatusName(String synStatusName) {
		if("S".equals(synStatusName.toUpperCase())){
			this.synStatusName = "同步成功";
		}else if("N".equals(synStatusName.toUpperCase())){
			this.synStatusName = "未同步";
		}else if("F".equals(synStatusName.toUpperCase())){
			this.synStatusName = "同步失败";
		}
		//this.synStatusName = synStatusName;
	}
	public String getSysRoleId() {
		return sysRoleId;
	}
	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	public String getSysRoleTypeId() {
		return sysRoleTypeId;
	}
	public void setSysRoleTypeId(String sysRoleTypeId) {
		this.sysRoleTypeId = sysRoleTypeId;
	}
	public String getSysRoleTypeNm() {
		return sysRoleTypeNm;
	}
	public void setSysRoleTypeNm(String sysRoleTypeNm) {
		this.sysRoleTypeNm = sysRoleTypeNm;
	}
	public String getSysStatusId() {
		return sysStatusId;
	}
	public void setSysStatusId(String sysStatusId) {
		this.sysStatusId = sysStatusId;
	}
	public String getSysStatusNm() {
		return sysStatusNm;
	}
	public void setSysStatusNm(String sysStatusNm) {
		this.sysStatusNm = sysStatusNm;
	}
	public String getShownum() {
		return shownum;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}
	public String getSysRoleNm() {
		return sysRoleNm;
	}
	public void setSysRoleNm(String sysRoleNm) {
		this.sysRoleNm = sysRoleNm;
	}
}