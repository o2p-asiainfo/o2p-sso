package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SysDeptRoleBean {

	private String deptid;
	
	private String customerid ;
	
	private PageBean pageBean;
	
	private List<SysDeptRoleDataBean> searchResult;

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<SysDeptRoleDataBean> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SysDeptRoleDataBean> searchResult) {
		this.searchResult = searchResult;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
}
