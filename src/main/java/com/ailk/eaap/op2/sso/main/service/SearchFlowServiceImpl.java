package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.dao.SearchFlowDAO;
import com.ailk.eaap.op2.sso.main.model.FLowDataBean;
import com.ailk.eaap.op2.sso.main.model.FlowBean;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.SearchFlowBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;

public class SearchFlowServiceImpl implements ISearchFlowService {
	
	private SearchFlowDAO dao;

	public SearchFlowDAO getDao() {
		return dao;
	}

	public void setDao(SearchFlowDAO dao) {
		this.dao = dao;
	}

	public void doSearchFlow(SearchFlowBean bean) {	
		List<FLowDataBean> list = new ArrayList<FLowDataBean>();
		String sql = SqlUtil.buildSearchFlowSql(bean);
		Integer size = dao.getResultSize(sql);
		if(size > 0){
			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
			List<Map<String,Object>> pageResult = dao.getPageResult(pageSql);
			this.convertResult(pageResult, list);
			bean.getPageBean().setTotalRecord(size.toString());
			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
			bean.getPageBean().setTotalPage(totalPage);
		}
		bean.setSearchResult(list);
	}
	private void convertResult(List<Map<String,Object>> result,List<FLowDataBean> list){
		for(Map<String,Object> item : result){
			FLowDataBean bean = new FLowDataBean();
			bean.setEndTime(ConvertUtil.nullToSpace(item.get("END_TIME")));
			bean.setEventSeqID(ConvertUtil.nullToSpace(item.get("XRB_EVENT_SEQ_ID")));
			bean.setFlowCd(ConvertUtil.nullToSpace(item.get("BUSI_FLOWID")));
			bean.setFlowNm(ConvertUtil.nullToSpace(item.get("flowNm")));
			bean.setIsEndCd(ConvertUtil.nullToSpace(item.get("STATE")));
			bean.setIsEndNm(ConvertUtil.convertIsEndCd(ConvertUtil.nullToSpace(item.get("STATE"))));
			String npCodeList = ConvertUtil.nullToSpace(item.get("NPCodeList"));
			if(npCodeList.length()>16){
				bean.setNpCode(npCodeList.substring(0, 16)+"...");
			}else{
				bean.setNpCode(npCodeList);
			}			
			bean.setNpCodeList(npCodeList);
			bean.setProvinceCd(ConvertUtil.nullToSpace(item.get("ORG_ID")));
			bean.setProvinceNm(ConvertUtil.nullToSpace(item.get("provinceNm")));
			bean.setRole(ConvertUtil.convertInOrOutCd(ConvertUtil.nullToSpace(item.get("INOROUT"))));		
			bean.setStartTime(ConvertUtil.nullToSpace(item.get("BEG_TIME")));
			bean.setXrbFlowID(ConvertUtil.nullToSpace(item.get("NP_FLOW_INST_ID")));
			list.add(bean);
		}
	}

	public List<FlowBean> doSearchFlowDef() {
		return dao.getFlowList();
	}

	public List<MessageBean> doSearchMessageByID(String id) {
		return dao.getMessageByID(id);
	}
}
