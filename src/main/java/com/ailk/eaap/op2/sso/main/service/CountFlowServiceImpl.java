package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.dao.SearchFlowDAO;
import com.ailk.eaap.op2.sso.main.model.CountDataBean;
import com.ailk.eaap.op2.sso.main.model.CountFlowBean;
import com.ailk.eaap.op2.sso.main.model.JsonTransformBean;
import com.ailk.eaap.op2.sso.main.model.NpDataBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;

public class CountFlowServiceImpl implements ICountFlowService{
	private SearchFlowDAO dao;

	public SearchFlowDAO getDao() {
		return dao;
	}

	public void setDao(SearchFlowDAO dao) {
		this.dao = dao;
	}

	public void doCountFlow(CountFlowBean bean) {
		List<CountDataBean> list = new ArrayList<CountDataBean>();
		String sql = SqlUtil.buildCountFlowSql(bean);
		Integer size = dao.getResultSize(sql);
		if(size > 0){
			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
			List<Map<String,Object>> pageResult = dao.getPageResult(pageSql);
			this.convertFlowResult(pageResult, list);
			bean.getPageBean().setTotalRecord(size.toString());
			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
			bean.getPageBean().setTotalPage(totalPage);
		}
		bean.setSearchResult(list);
	}
	private void convertFlowResult(List<Map<String,Object>> result,List<CountDataBean> list){
		for(Map<String,Object> item : result){
			CountDataBean bean = new CountDataBean();
			bean.setFlowCd(ConvertUtil.nullToSpace(item.get("flowCd")));
			bean.setFlowNm(ConvertUtil.convertFlowCd(ConvertUtil.nullToSpace(item.get("flowCd"))));
			bean.setInOrOutCd(ConvertUtil.nullToSpace(item.get("inorout")));
			bean.setInOrOutNm(ConvertUtil.convertInOrOutCd(ConvertUtil.nullToSpace(item.get("inorout"))));
			String npCodeList = ConvertUtil.nullToSpace(item.get("code"));
			if(npCodeList.length()>16){
				bean.setNpCode(npCodeList.substring(0, 16)+"...");
			}else{
				bean.setNpCode(npCodeList);
			}			
			bean.setNpCodeList(npCodeList);
			bean.setProvinceCd(ConvertUtil.nullToSpace(item.get("code")));
			bean.setProvinceNm(ConvertUtil.convertProvinceCd(ConvertUtil.nullToSpace(item.get("code"))));
			bean.setQuantity(ConvertUtil.nullToSpace(item.get("num")));
			bean.setStateCd(ConvertUtil.nullToSpace(item.get("state")));
			bean.setStateNm(ConvertUtil.convertStateCd(ConvertUtil.nullToSpace(item.get("state"))));	
			list.add(bean);
		}
	}

	public void doSearchNpCodeList(JsonTransformBean bean) {
		String jsonObj = "";
		StringBuffer temp = new StringBuffer("");
		List<NpDataBean> list = new ArrayList<NpDataBean>();
		String sql = SqlUtil.buildSearchNpListSql(bean);
		Integer size = dao.getResultSize(sql);
		if(size > 0){
			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
			List<Map<String,Object>> pageResult = dao.getPageResult(pageSql);
			this.convertNpCodeResult(pageResult, list);
			bean.getPageBean().setTotalRecord(size.toString());
			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
			bean.getPageBean().setTotalPage(totalPage);
		}
		//jsonResult = "{user:[{name:'aaa',sex:'man'},{name:'bbb',sex:'woman'}]}";
		temp=temp.append("{");
		temp=temp.append(ConvertUtil.convertPageBeanToJsonObj(bean.getPageBean()));
		temp=temp.append(",");
		temp=temp.append(ConvertUtil.convertNplistToJsonObj(list));
		temp=temp.append("}");
		jsonObj=temp.toString();
		bean.setJsonResult(jsonObj);
	}
	private void convertNpCodeResult(List<Map<String,Object>> result,List<NpDataBean> list){
		for(Map<String,Object> item : result){
			NpDataBean bean = new NpDataBean();
			bean.setStartTime(ConvertUtil.nullToSpace(item.get("beg_time")));
			bean.setEndTime(ConvertUtil.nullToSpace(item.get("end_time")));
			String npCodeList = ConvertUtil.nullToSpace(item.get("npcodelist"));
			if(npCodeList.length()>16){
				bean.setNpCode(npCodeList.substring(0, 16)+"...");
			}else{
				bean.setNpCode(npCodeList);
			}			
			bean.setNpCodeList(npCodeList);
			list.add(bean);
		}
	}
}
