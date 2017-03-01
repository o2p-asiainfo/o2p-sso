package com.ailk.eaap.op2.sso.main.model;

/**
 * 权限明细表 SYS_RIGHT
 * @author zhaobl
 *
 */

public class SysRight implements java.io.Serializable {

	private String sysRightId; //权限明细编号
	private String sysRoleId; //角色编号
	private String functionId; //功能点编号
	private String sysPersonId; //员工编号
	private String authPersonId; //授权员工编号
	private String authTime; //授权时间
	private String sysStatusId; //状态编号

	
	
	
	
	

//	// Fields
//
//	private String sysRightId;
//	private SYSPERSON sysPersonByAuthPersonId;
//	private SysRole sysRole;
//	private SysFunction sysFunction;
//	private SYSPERSON sysPersonBySysPersonId;
//	private String authTime;
//	private String sysPersonId ;
//	private String functionId  ;
//
//	// Constructors
//
//	public String getFunctionId() {
//		return functionId;
//	}
//
//	public void setFunctionId(String functionId) {
//		this.functionId = functionId;
//	}
//
//	public String getSysPersonId() {
//		return sysPersonId;
//	}
//
//	public void setSysPersonId(String sysPersonId) {
//		this.sysPersonId = sysPersonId;
//	}
//
//	/** default constructor */
//	public SysRight() {
//	}
//
//	/** full constructor */
//	public SysRight(SYSPERSON sysPersonByAuthPersonId, SysRole sysRole,
//			SysFunction sysFunction, SYSPERSON sysPersonBySysPersonId,
//			String authTime) {
//		this.sysPersonByAuthPersonId = sysPersonByAuthPersonId;
//		this.sysRole = sysRole;
//		this.sysFunction = sysFunction;
//		this.sysPersonBySysPersonId = sysPersonBySysPersonId;
//		this.authTime = authTime;
//	}
//
//	// Property accessors
//
//	public String getSysRightId() {
//		return this.sysRightId;
//	}
//
//	public void setSysRightId(String sysRightId) {
//		this.sysRightId = sysRightId;
//	}
//
//	public SYSPERSON getSysPersonByAuthPersonId() {
//		return this.sysPersonByAuthPersonId;
//	}
//
//	public void setSysPersonByAuthPersonId(SYSPERSON sysPersonByAuthPersonId) {
//		this.sysPersonByAuthPersonId = sysPersonByAuthPersonId;
//	}
//
//	public SysRole getSysRole() {
//		return this.sysRole;
//	}
//
//	public void setSysRole(SysRole sysRole) {
//		this.sysRole = sysRole;
//	}
//
//	public SysFunction getSysFunction() {
//		return this.sysFunction;
//	}
//
//	public void setSysFunction(SysFunction sysFunction) {
//		this.sysFunction = sysFunction;
//	}
//
//	public SYSPERSON getSysPersonBySysPersonId() {
//		return this.sysPersonBySysPersonId;
//	}
//
//	public void setSysPersonBySysPersonId(SYSPERSON sysPersonBySysPersonId) {
//		this.sysPersonBySysPersonId = sysPersonBySysPersonId;
//	}
//
//	public String getAuthTime() {
//		return this.authTime;
//	}
//
//	public void setAuthTime(String authTime) {
//		this.authTime = authTime;
//	}

}