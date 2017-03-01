package com.ailk.eaap.op2.sso.main.util;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class WebServiceClient {

	private Service service;
	private Call call;

	public WebServiceClient() throws ServiceException {
		service = new Service();
		call = (Call) service.createCall();
	}

	public String sendMessage(String url, String message) throws Exception {
		call.setTargetEndpointAddress(new java.net.URL(url));
		call.setOperationStyle(org.apache.axis.constants.Style.RPC);// DOCUMENT
		call.setOperationUse(org.apache.axis.constants.Use.LITERAL);//  
		call.setTimeout(new Integer(30 * 1000));
		call.setOperationName(new QName("http://www.chinatelecom.hub.com","exchange"));
		call.addParameter("exchange",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); //
		String rep = (String) call.invoke(new Object[] { message });// 
		return rep;
	}
}
