package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SysCustomerRoleBean {

	private String syscustomerid;
	
	private PageBean pageBean;
	
	private List<SysCustomerRoleDataBean> searchResult;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<SysCustomerRoleDataBean> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SysCustomerRoleDataBean> searchResult) {
		this.searchResult = searchResult;
	}

	public String getSyscustomerid() {
		return syscustomerid;
	}

	public void setSyscustomerid(String syscustomerid) {
		this.syscustomerid = syscustomerid;
	}

}
