package com.ailk.eaap.op2.sso.main.model;

public class JsonTransformBean {
	private String searchType;
	private String npCodeList;
	private String subProvince;
	private String subState;
	private String subFlowCd;
	private String subStartTime;
	private String subEndTime;
	private PageBean pageBean;
	private String jsonResult;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getNpCodeList() {
		return npCodeList;
	}

	public void setNpCodeList(String npCodeList) {
		this.npCodeList = npCodeList;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getSubProvince() {
		return subProvince;
	}

	public void setSubProvince(String subProvince) {
		this.subProvince = subProvince;
	}

	public String getSubState() {
		return subState;
	}

	public void setSubState(String subState) {
		this.subState = subState;
	}

	public String getSubFlowCd() {
		return subFlowCd;
	}

	public void setSubFlowCd(String subFlowCd) {
		this.subFlowCd = subFlowCd;
	}

	public String getSubStartTime() {
		return subStartTime;
	}

	public void setSubStartTime(String subStartTime) {
		this.subStartTime = subStartTime;
	}

	public String getSubEndTime() {
		return subEndTime;
	}

	public void setSubEndTime(String subEndTime) {
		this.subEndTime = subEndTime;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
}
