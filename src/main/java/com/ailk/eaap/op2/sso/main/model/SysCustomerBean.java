package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SysCustomerBean {

	private String areaid;
	
	private PageBean pageBean;
	
	private List<SysCustomerDataBean> searchResult;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	public List<SysCustomerDataBean> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SysCustomerDataBean> searchResult) {
		this.searchResult = searchResult;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
}
