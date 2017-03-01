package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.FlowBean;
import com.ailk.eaap.op2.sso.main.model.MessageBean;

public interface SearchFlowDAO {
int getResultSize(String sql);
List<Map<String,Object>> getPageResult(String sql);
List<FlowBean> getFlowList();
List<MessageBean> getMessageByID(String msgID);
}
