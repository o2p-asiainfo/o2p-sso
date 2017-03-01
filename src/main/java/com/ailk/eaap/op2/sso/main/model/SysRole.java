package com.ailk.eaap.op2.sso.main.model;

/**
 * 角色表（SYS_ROLE）
 * @author zhaobl
 *
 */

public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String sysRoleId;
	private String sysRoleName;//角色名称
	private String shownum;
	private String createTime;
	private String sysStatusId;
	private String sysStatusName;//状态名称
	
	public String getSysRoleId() {
		return sysRoleId;
	}
	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	public String getSysRoleName() {
		return sysRoleName;
	}
	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}
	public String getShownum() {
		return shownum;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

}