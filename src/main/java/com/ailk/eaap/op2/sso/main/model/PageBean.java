package com.ailk.eaap.op2.sso.main.model;

public class PageBean {
private String totalRecord = "0";
private String totalPage = "0";
private String pageCount = "10";
private String currentPage = "1";
public String getTotalRecord() {
	return totalRecord;
}
public void setTotalRecord(String totalRecord) {
	this.totalRecord = totalRecord;
}
public String getTotalPage() {
	return totalPage;
}
public void setTotalPage(String totalPage) {
	this.totalPage = totalPage;
}
public String getPageCount() {
	return pageCount;
}
public void setPageCount(String pageCount) {
	this.pageCount = pageCount;
}
public String getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(String currentPage) {
	this.currentPage = currentPage;
}
}
