package com.ailk.eaap.op2.sso.main.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.action.BaseAction;
import com.ailk.eaap.op2.sso.main.model.FlowBean;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SearchFlowBean;
import com.ailk.eaap.op2.sso.main.service.ISearchFlowService;

public class SearchFlowAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISearchFlowService searchFlowService;
	
	private Map<String,Object> paramMap;

	private String startTime;
	private String endTime;
	private String preFlow;
	private String sonFlow;
	private String npCode;
	
	private String currPage;
	private String pageNum;
	
	private List<MessageBean> result;
	private String msgSeqId;
	


	public List<MessageBean> getResult() {
		return result;
	}

	public void setResult(List<MessageBean> result) {
		this.result = result;
	}

	public String getMsgSeqId() {
		return msgSeqId;
	}

	public void setMsgSeqId(String msgSeqId) {
		this.msgSeqId = msgSeqId;
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

	public void setSearchFlowService(ISearchFlowService searchFlowService) {
		this.searchFlowService = searchFlowService;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public String excuteSearch(){
		SearchFlowBean bean = new SearchFlowBean();
		bean.setStartTime(startTime);
		bean.setEndTime(endTime);
		bean.setPreFlow(preFlow);
		bean.setSonFlow(sonFlow);
		bean.setNpCode(npCode);
		PageBean pageBean = new PageBean();
		if(currPage!=null){
			pageBean.setCurrentPage(currPage);
		}
		if(pageNum!=null){
			pageBean.setPageCount(pageNum);
		}
		bean.setPageBean(pageBean);
		searchFlowService.doSearchFlow(bean);
		paramMap = new HashMap<String, Object>();
		paramMap.put("pageBean", bean.getPageBean());
		paramMap.put("searchResult",bean.getSearchResult());
		return SUCCESS;
	}
	public String gotoSearch(){
		List<FlowBean> list = searchFlowService.doSearchFlowDef();
		if(list!=null && list.size()>0){
			session.put("flowDefList", list);
		}
		return SUCCESS;
	}
	
	public String showMessage(){
		result = searchFlowService.doSearchMessageByID(msgSeqId);
		return SUCCESS;
	}
	
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

	public String getNpCode() {
		return npCode;
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

	public void setNpCode(String npCode) {
		this.npCode = npCode;
	}
}
