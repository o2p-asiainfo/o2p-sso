package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class SearchFlowBean{
	private String startTime;
	private String endTime;
	private String preFlow;
	private String sonFlow;
	private String npCode;
	private PageBean pageBean;
	private List<FLowDataBean> searchResult;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPreFlow() {
		return preFlow;
	}
	public void setPreFlow(String preFlow) {
		this.preFlow = preFlow;
	}
	public String getSonFlow() {
		return sonFlow;
	}
	public void setSonFlow(String sonFlow) {
		this.sonFlow = sonFlow;
	}
	public String getNpCode() {
		return npCode;
	}
	public void setNpCode(String npCode) {
		this.npCode = npCode;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<FLowDataBean> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<FLowDataBean> searchResult) {
		this.searchResult = searchResult;
	}
}
