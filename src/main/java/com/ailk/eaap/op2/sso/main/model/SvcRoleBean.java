package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SvcRoleBean {
	private String roleName;
	private String roleType;
	private String roleState;	
	private String[] roleId;
	private String checkedId;
	
	private String editRoleId;
	private String editType;
	
	private PageBean pageBean;
	private List<RoleDataBean> searchResult;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleState() {
		return roleState;
	}
	public void setRoleState(String roleState) {
		this.roleState = roleState;
	}
	public String[] getRoleId() {
		return roleId;
	}
	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}
	public String getEditRoleId() {
		return editRoleId;
	}
	public void setEditRoleId(String editRoleId) {
		this.editRoleId = editRoleId;
	}
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	public List<RoleDataBean> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<RoleDataBean> searchResult) {
		this.searchResult = searchResult;
	}
	public String getCheckedId() {
		return checkedId;
	}
	public void setCheckedId(String checkedId) {
		this.checkedId = checkedId;
	}	
}
