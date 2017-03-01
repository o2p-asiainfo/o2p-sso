package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SvcDeptBean {
	private String deptName;
	private String compId;
	private String preDept;
	private String deptState;	
	private String[] deptId;
	private String checkedId;
	
	private String editDeptId;
	private String editType;
	
	private PageBean pageBean;
	private List<DeptDataBean> searchResult;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getPreDept() {
		return preDept;
	}
	public void setPreDept(String preDept) {
		this.preDept = preDept;
	}
	public String getDeptState() {
		return deptState;
	}
	public void setDeptState(String deptState) {
		this.deptState = deptState;
	}
	public List<DeptDataBean> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<DeptDataBean> searchResult) {
		this.searchResult = searchResult;
	}
	public String[] getDeptId() {
		return deptId;
	}
	public void setDeptId(String[] deptId) {
		this.deptId = deptId;
	}
	public String getEditDeptId() {
		return editDeptId;
	}
	public void setEditDeptId(String editDeptId) {
		this.editDeptId = editDeptId;
	}
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	public String getCheckedId() {
		return checkedId;
	}
	public void setCheckedId(String checkedId) {
		this.checkedId = checkedId;
	}
}
