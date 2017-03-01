package com.ailk.eaap.op2.sso.framework.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.ailk.eaap.op2.sso.framework.model.BaseModel;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.linkage.rainbow.ui.components.page.Page;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public  class BaseAction extends ActionSupport implements ModelDriven , SessionAware, ServletRequestAware, ServletResponseAware,ServletContextAware  {
	private static final long serialVersionUID = 1L;
	//public static final String USER_SESSION_ID = "USER";
	public HttpServletRequest request;
	public HttpServletResponse response;
	public Map session;	
	public ServletContext application;
	public BaseModel model;
	
	private List<String> baseIndicatorList;

	private Map pageMap;

	private String msgKey = "boco_msg";
	
	public void saveErrorMsg(String msg){
		request.setAttribute(msgKey, msg);
	}
	public void setServletContext(ServletContext application) {
        this.application = application;
    }
	
	public Object getModel() {
		if(model==null){
			model= new BaseModel();
		}
		return model;
	}
	
	/**
	 * 封装分页的相关数据，以便页面显示时获得数据
	 * 
	 * @param request
	 * @param mapping
	 * @param form
	 * @param page
	 */
	protected void packPageMap(Object action,Page page) {		
		String path = request.getRequestURI();
		pageMap=new HashMap();
		pageMap.put("path", path);
		pageMap.put("page", page);
		pageMap.put("formObject", action);
	}
	
	public Map getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map pageMap) {
		this.pageMap = pageMap;
	}

	public void setSession(Map arg0) {
		this.session=arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		response=arg0;
		
	}

	/**
	 * 把LIST的值组合成AutoComplete的XML
	 * 
	 * @param list
	 *            结果列表
	 * @param name
	 *            显示的名称对应的字段
	 * @param value
	 *            获得的结果对应的字段
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected String getAjaxXmlString(List list, String name, String value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		xmlBuilder.setEncoding("UTF-8");
		xmlBuilder.addItemAsCData("-请选择-", "");
		return xmlBuilder.addItems(list, name, value).toString();
	}
	
	/**
	 * 把LIST的值组合成AutoComplete的XML
	 * 
	 * @param list
	 *            结果列表
	 * @param name
	 *            显示的名称对应的字段
	 * @param value
	 *            获得的结果对应的字段
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected String getAjaxXmlString(List list, LinkedList<String> nameList, LinkedList<String> valueList) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		xmlBuilder.setEncoding("UTF-8");
		xmlBuilder.addItemAsCData("", "");
		//return xmlBuilder.addItems(list, nameList, valueList).toString();
		return "";
	}
	
	/**
	 * 输出XML
	 * 
	 * @param response
	 * @param xmlString
	 * @throws IOException
	 */
	protected void writeXmlString(String xmlString) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(xmlString);
		out.close();
	}

	
	/**
	 * 使用AJAXTAGS时通过该方法取得参数值
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	protected String getValueByParamNameInAjaxTag(String paraName) {
		String value = null;
		value = request.getParameter(paraName);
		if (value != null) {
			value = StringUtil.unescape(value);
		}
		return value;
	}
	
	/**
	 * 使用DWR时可以通过此方法取得SESSION
	 * 
	 * @param bl
	 *            标志SESSION不存在时是否创建新的SESSION
	 * @return
	 */
	protected HttpSession getSessionByDWR(Boolean bl) {
		return WebContextFactory.get().getSession(bl);
	}

	/**
	 * @return
	 */
	protected HttpServletRequest getRequestByDWR() {
		return WebContextFactory.get().getHttpServletRequest();
	}
	
	/**
	 * @return
	 */
	protected HttpServletResponse getResponseByDWR() {
		return WebContextFactory.get().getHttpServletResponse();
	}

	/**
	 * 使用DWR时可以通过此方法取得SESSION
	 * 
	 * @return
	 */
	protected HttpSession getSessionByDWR() {
		return getSessionByDWR(true);
	}
	/**zhaobl
	 * 获得Local信息（用于国际化）
	 * @return
	 */
	public String getLocaleName(){
	    String localeName = "";
	    try {
		Locale locale= getLocale();
		    if(locale != null){
			localeName =  "_"+getLocale().getLanguage()+"_"+getLocale().getCountry();
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return localeName;
	}
}
