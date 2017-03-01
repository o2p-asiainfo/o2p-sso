package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class CountFlowBean{
	private String searchType;
	private String province;
	private String inOrOut1;
	private String state;
	private String preFlow;
	private String sonFlow;
	private String startTime1;
	private String endTime1;
	private String npCode;
	private String inOrOut2;
	private String startTime2;
	private String endTime2;
	
	private PageBean pageBean;
	private List<CountDataBean> searchResult;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getInOrOut1() {
		return inOrOut1;
	}
	public void setInOrOut1(String inOrOut1) {
		this.inOrOut1 = inOrOut1;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getStartTime1() {
		return startTime1;
	}
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	public String getNpCode() {
		return npCode;
	}
	public void setNpCode(String npCode) {
		this.npCode = npCode;
	}
	public String getInOrOut2() {
		return inOrOut2;
	}
	public void setInOrOut2(String inOrOut2) {
		this.inOrOut2 = inOrOut2;
	}
	public String getStartTime2() {
		return startTime2;
	}
	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}
	public String getEndTime2() {
		return endTime2;
	}
	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<CountDataBean> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<CountDataBean> searchResult) {
		this.searchResult = searchResult;
	}
	
}
