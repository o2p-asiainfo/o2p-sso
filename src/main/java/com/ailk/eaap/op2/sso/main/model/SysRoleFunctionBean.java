package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SysRoleFunctionBean {

	private String roleid;
	
	private PageBean pageBean;
	
	private List<SysRoleFunctionDataBean> searchResult;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<SysRoleFunctionDataBean> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SysRoleFunctionDataBean> searchResult) {
		this.searchResult = searchResult;
	}
}
