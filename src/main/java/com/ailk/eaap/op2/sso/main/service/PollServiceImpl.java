package com.ailk.eaap.op2.sso.main.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ailk.eaap.op2.sso.main.model.UnifiedResponseBean;

public class PollServiceImpl implements PollService {
	UnifiedResponseBean rspUnifiedBean;
	public Map parseXml(String returnXml) {
		Map responseMassage = new HashMap();
		try {
			
			Reader reader = new StringReader(returnXml);
			Document document = new SAXReader().read(reader);
			//Node rspTransactionID=document.selectSingleNode("//TcpCont/TransactionID");
			Node rspType=document.selectSingleNode("//TcpCont/Response/RspType");
			Node rspCode=document.selectSingleNode("//TcpCont/Response/RspCode");
			Node rspDesc = document
					.selectSingleNode("//TcpCont/Response/RspDesc");
			rspUnifiedBean.setRspCode(rspCode.getText());
			rspUnifiedBean.setRspDesc(rspDesc.getText());
			//rspUnifiedBean.setRspTransactionID(rspTransactionID.getText());
			rspUnifiedBean.setRspType(rspType.getText());
			if(rspCode.getText().equals("0000")){
				responseMassage.put("ture",rspUnifiedBean);
			}
			else{
				responseMassage.put("false", rspUnifiedBean);
			}
			return responseMassage;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMassage;
	}
	public UnifiedResponseBean getRspUnifiedBean() {
		return rspUnifiedBean;
	}
	public void setRspUnifiedBean(UnifiedResponseBean rspUnifiedBean) {
		this.rspUnifiedBean = rspUnifiedBean;
	}
}
