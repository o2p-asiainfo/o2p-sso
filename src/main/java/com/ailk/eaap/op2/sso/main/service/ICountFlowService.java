package com.ailk.eaap.op2.sso.main.service;


import com.ailk.eaap.op2.sso.main.model.CountFlowBean;
import com.ailk.eaap.op2.sso.main.model.JsonTransformBean;

public interface ICountFlowService {
void doCountFlow(CountFlowBean bean);
void doSearchNpCodeList(JsonTransformBean bean);
}
