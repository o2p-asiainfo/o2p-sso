package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.FlowBean;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.SearchFlowBean;

public interface ISearchFlowService {
void doSearchFlow(SearchFlowBean bean);
List<FlowBean> doSearchFlowDef();
List<MessageBean> doSearchMessageByID(String id);
}
