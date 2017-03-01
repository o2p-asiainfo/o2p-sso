package com.ailk.eaap.op2.sso.main.action;

import java.util.HashMap;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.CountFlowBean;
import com.ailk.eaap.op2.sso.main.model.JsonTransformBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.service.ICountFlowService;
import com.linkage.rainbow.ui.struts.BaseAction;

public class CountFlowAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private ICountFlowService countFlowService;
	
	private Map<String,Object> paramMap;

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
	
	private String currPage;
	private String pageNum;
	
	private String subProvince;
	private String subNpCodeList;
	private String subState;
	private String subFlowCd;
	private String subStartTime;
	private String subEndTime;
	private String subCurrPage;
	private String subPageNum;
	
	private String jsonResult;
	
	public String excuteSearch(){
		CountFlowBean bean = new CountFlowBean();
		bean.setEndTime1(endTime1);
		bean.setEndTime2(endTime2);
		bean.setInOrOut1(inOrOut1);
		bean.setInOrOut2(inOrOut2);
		bean.setNpCode(npCode);
		bean.setPreFlow(preFlow);
		bean.setProvince(province);
		bean.setSearchType(searchType);
		bean.setSonFlow(sonFlow);
		bean.setStartTime1(startTime1);
		bean.setStartTime2(startTime2);
		bean.setState(state);
		PageBean pageBean = new PageBean();
		if(currPage!=null){
			pageBean.setCurrentPage(currPage);
		}
		if(pageNum!=null){
			pageBean.setPageCount(pageNum);
		}
		bean.setPageBean(pageBean);
		countFlowService.doCountFlow(bean);
		paramMap = new HashMap<String, Object>();
		paramMap.put("pageBean", bean.getPageBean());
		paramMap.put("searchResult",bean.getSearchResult());
		return SUCCESS;
	}
	
	public String showNpList(){
		//jsonResult = "{user:[{name:'aaa',sex:'man'},{name:'bbb',sex:'woman'}]}";
		JsonTransformBean bean = new JsonTransformBean();
		bean.setSearchType(searchType);
		bean.setNpCodeList(subNpCodeList);
		bean.setSubEndTime(subEndTime);
		bean.setSubFlowCd(subFlowCd);
		bean.setSubProvince(subProvince);
		bean.setSubStartTime(subStartTime);
		bean.setSubState(subState);
		PageBean pageBean = new PageBean();
		if(subCurrPage!=null && !"".equals(subCurrPage)){
			pageBean.setCurrentPage(subCurrPage);
		}
		if(subPageNum!=null && !"".equals(subPageNum)){
			pageBean.setPageCount(subPageNum);
		}
		bean.setPageBean(pageBean);
		countFlowService.doSearchNpCodeList(bean);
		jsonResult = bean.getJsonResult();
		return SUCCESS;
	}
	
	public String gotoCountFlow(){
		return SUCCESS;
	}


	public void setCountFlowService(ICountFlowService countFlowService) {
		this.countFlowService = countFlowService;
	}


	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

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

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
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
	public String getSubCurrPage() {
		return subCurrPage;
	}

	public void setSubCurrPage(String subCurrPage) {
		this.subCurrPage = subCurrPage;
	}

	public String getSubPageNum() {
		return subPageNum;
	}

	public void setSubPageNum(String subPageNum) {
		this.subPageNum = subPageNum;
	}
}
