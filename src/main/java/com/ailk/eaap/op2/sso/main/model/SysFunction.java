package com.ailk.eaap.op2.sso.main.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能点表  SYS_FUNCTION
 * @author zhaobl
 *
 */

public class SysFunction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String functionId; //功能点编号
	private String functionName; //功能点名称
	private String functionEnName; //功能点英文名称
	private String businessSystemId; //所属业务系统ID
	private String businessSystemName; //所属业务系统名称
	private String parentFunctionId; //父功能点编号
	private String parentFunctionName; //父功能点名称
	private String url; //URL
	private String shownum; //排序号
	private String sysStatusId ; //状态编号
	private String sysStatusName; //状态名称
	private String displayMode; //三级菜单的窗口显示模式 0:框架内;1:弹出窗口
	
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionEnName() {
		return functionEnName;
	}
	public void setFunctionEnName(String functionEnName) {
		this.functionEnName = functionEnName;
	}
	public String getBusinessSystemId() {
		return businessSystemId;
	}
	public void setBusinessSystemId(String businessSystemId) {
		this.businessSystemId = businessSystemId;
	}
	public String getParentFunctionId() {
		return parentFunctionId;
	}
	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShownum() {
		return shownum;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}
	
	public String getSysStatusId() {
		return sysStatusId;
	}
	public void setSysStatusId(String sysStatusId) {
		this.sysStatusId = sysStatusId;
	}
	public String getParentFunctionName() {
		return parentFunctionName;
	}
	public void setParentFunctionName(String parentFunctionName) {
		this.parentFunctionName = parentFunctionName;
	}
	public String getSysStatusName() {
		return sysStatusName;
	}
	public void setSysStatusName(String sysStatusName) {
		this.sysStatusName = sysStatusName;
	}
	public String getBusinessSystemName() {
		return businessSystemName;
	}
	public void setBusinessSystemName(String businessSystemName) {
		this.businessSystemName = businessSystemName;
	}
	
	
	
	
	
//	// Fields
//
//	private String functionId;
//	private SysStatus sysStatus;
//	private Long businessSystemId;
//	private String businessSystemName;
//	private SysBusinessSystem sysBusinessSystem;
//	private String functionName;
//	private String parentFunctionId;
//	private String url;
//	private String shownum;
//	private String sysStatuId ;
//	private String sysStatuName ;
//	private Set sysRoleFunctions = new HashSet(0);
//	private Set sysRights = new HashSet(0);
//	private boolean flag ;
//	private PageBean pageBean;
//	private List<SysFunction> searchResult;
//
//	// Constructors
//
//	public PageBean getPageBean() {
//		return pageBean;
//	}
//
//	public void setPageBean(PageBean pageBean) {
//		this.pageBean = pageBean;
//	}
//
//	 
//
//	public List<SysFunction> getSearchResult() {
//		return searchResult;
//	}
//
//	public void setSearchResult(List<SysFunction> searchResult) {
//		this.searchResult = searchResult;
//	}
//
//	/** default constructor */
//	public SysFunction() {
//	}
//
//	/** minimal constructor */
//	public SysFunction(String url) {
//		this.url = url;
//	}
//
//	/** full constructor */
//	public SysFunction(SysStatus sysStatus,
//			SysBusinessSystem sysBusinessSystem, String functionName,
//			String parentFunctionId, String url, String shownum,
//			Set sysRoleFunctions, Set sysRights) {
//		this.sysStatus = sysStatus;
//		this.sysBusinessSystem = sysBusinessSystem;
//		this.functionName = functionName;
//		this.parentFunctionId = parentFunctionId;
//		this.url = url;
//		this.shownum = shownum;
//		this.sysRoleFunctions = sysRoleFunctions;
//		this.sysRights = sysRights;
//	}
//
//	// Property accessors
//
//	public String getFunctionId() {
//		return this.functionId;
//	}
//
//	public void setFunctionId(String functionId) {
//		this.functionId = functionId;
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
//	public SysBusinessSystem getSysBusinessSystem() {
//		return this.sysBusinessSystem;
//	}
//
//	public void setSysBusinessSystem(SysBusinessSystem sysBusinessSystem) {
//		this.sysBusinessSystem = sysBusinessSystem;
//	}
//
//	public String getFunctionName() {
//		return this.functionName;
//	}
//
//	public void setFunctionName(String functionName) {
//		this.functionName = functionName;
//	}
//
//	public String getParentFunctionId() {
//		return this.parentFunctionId;
//	}
//
//	public void setParentFunctionId(String parentFunctionId) {
//		this.parentFunctionId = parentFunctionId;
//	}
//
//	public String getUrl() {
//		return this.url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getShownum() {
//		return this.shownum;
//	}
//
//	public void setShownum(String shownum) {
//		this.shownum = shownum;
//	}
//
//	public Set getSysRoleFunctions() {
//		return this.sysRoleFunctions;
//	}
//
//	public void setSysRoleFunctions(Set sysRoleFunctions) {
//		this.sysRoleFunctions = sysRoleFunctions;
//	}
//
//	public Set getSysRights() {
//		return this.sysRights;
//	}
//
//	public void setSysRights(Set sysRights) {
//		this.sysRights = sysRights;
//	}
//
//	public Long getBusinessSystemId() {
//		return businessSystemId;
//	}
//
//	public void setBusinessSystemId(Long businessSystemId) {
//		this.businessSystemId = businessSystemId;
//	}
//
//	public String getBusinessSystemName() {
//		return businessSystemName;
//	}
//
//	public void setBusinessSystemName(String businessSystemName) {
//		this.businessSystemName = businessSystemName;
//	}
//
//	public boolean isFlag() {
//		return flag;
//	}
//
//	public void setFlag(boolean flag) {
//		this.flag = flag;
//	}
//
//	public String getSysStatuId() {
//		return sysStatuId;
//	}
//
//	public void setSysStatuId(String sysStatuId) {
//		this.sysStatuId = sysStatuId;
//	}
//
//	public String getSysStatuName() {
//		return sysStatuName;
//	}
//
//	public void setSysStatuName(String sysStatuName) {
//		this.sysStatuName = sysStatuName;
//	}

}