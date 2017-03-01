package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ailk.eaap.op2.sso.main.model.FlowBean;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SearchFlowDAOImpl implements SearchFlowDAO {
	
	private JdbcTemplate npsoaJdbcTemplate;
	
	private JdbcTemplate xrbJdbcTemplate;
	
	private static final String SEARCH_FLOW_DEF = "select busi_flowid,name" +
			" from npsoa.NPSOA_FLOW_DEF";
	
	private static final String SEARCH_MESSAGE = "select MSG,DESCRIPTOR " +
			//" from xrb.ORIGINAL_MESSAGE_LOG " +
			" from ORIGINAL_MESSAGE_LOG " +
			" where XRB_EVENT_SEQ_ID = :eventId";


	public JdbcTemplate getNpsoaJdbcTemplate() {
		return npsoaJdbcTemplate;
	}

	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}

	public void setNpsoaJdbcTemplate(JdbcTemplate npsoaJdbcTemplate) {
		this.npsoaJdbcTemplate = npsoaJdbcTemplate;
	}

	public List<Map<String, Object>> getPageResult(String sql) {
		List<Map<String, Object>> list = npsoaJdbcTemplate.queryForList(sql);
		return list;
	}

	public int getResultSize(String sql) {
		int count = npsoaJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
		return count;
	}

	public List<FlowBean> getFlowList() {
		List<FlowBean> result = null;
		List<Map<String, Object>> list = npsoaJdbcTemplate.queryForList(SEARCH_FLOW_DEF);
		if(list != null && list.size() > 0){
			result = new ArrayList<FlowBean>();
			for(Map<String, Object> item : list){
				FlowBean bean = new FlowBean();
				bean.setFlowCd(ConvertUtil.nullToSpace(item.get("busi_flowid")));
				bean.setFlowNm(ConvertUtil.nullToSpace(item.get("name")));
				result.add(bean);
			}
		}
		return result;
	}

	public List<MessageBean> getMessageByID(String msgID) {
		List<MessageBean> list = null;
		try {
			NamedParameterJdbcTemplate njt = new NamedParameterJdbcTemplate(
					xrbJdbcTemplate);
			// �������
			Map<String, Object> inParam = new HashMap<String, Object>();			
			inParam.put("eventId", msgID);
			// ��ѯ���
			List<Map<String, Object>> result = njt.queryForList(SEARCH_MESSAGE, inParam);
			if(result != null && result.size() > 0){
				list = new ArrayList<MessageBean>();
				for(Map<String, Object> item : result){
					MessageBean bean = new MessageBean();
					String msg = ConvertUtil.nullToSpace(item.get("MSG"));
					msg = ConvertUtil.formatXml(msg);					
					msg = msg.replaceAll("<", "&lt;");
					msg = msg.replaceAll(">", "&gt;");
					msg=msg.replaceAll("\n","<br/>");
					bean.setMessageDscp(ConvertUtil.nullToSpace(item.get("DESCRIPTOR")));
					bean.setMessageContent(msg);
					list.add(bean);
				}
			}
		} catch (Exception e) {
		}
		return list;
	}
}
