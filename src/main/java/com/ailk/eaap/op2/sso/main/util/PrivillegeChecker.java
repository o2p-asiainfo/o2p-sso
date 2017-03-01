package com.ailk.eaap.op2.sso.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.model.SYSPERSON;

public class PrivillegeChecker implements Filter {

	//isEffective
	private boolean isEffective = false;
	//isEntryControl
	private boolean isEntryControl = false;
	private FilterConfig filterConfig = null;
	private ServletContext servletContext = null;
	private Logger log = Logger.getLog(PrivillegeChecker.class);
	private Integer entryState = new Integer(1); //
	
	private static final String FILENAME = "D:\\JS1001";
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		/***
		 * 说明一下:为什么这个过滤器不是取Session。
		 * 因为枢纽工程是整合进系统管理的，而系统管理和枢纽工程是两个不同的Web应用。而Session是不允许被多个应用共享，至少我目前实现不了。
		 * 如果不获取Session，那么用户不经过登录就能直接访问枢纽工程，会存在很大的安全隐患。所以在无法获取Session的前提下，采用文件存储的
		 * 方式来做判断，当用户在登录后会产生一个用Session命名的文本文件。通过访问这个文件是否存在来达到控制的目的，
		 */
		if (this.isEffective) {
			String errorString = null; 
			String staffNo = null;
			String serialNo = null;
			
			String strRequestURL = request.getRequestURL().toString();
			/***
			 * 过滤掉这个文件，Flex加载文件时会同时加载一个hmtl文件，一个js文件，一个css文件，除了html文件有参数外，其它的两个是没有参数的
			 * 所以在web.xml中过滤掉后两个，可是在加载mainMonitor.mxml文件时，除了mainMonitor.html文件外，又多了一个historyFrame.html
			 * 而historyFrame.html是不带参数的，这样就会造成你又没有登录的事实，因为登录的url都会带上Session的值，所以这里直接将其它过滤掉
			 * 
			 */			
			/*if(strRequestURL.substring(strRequestURL.length()-17,strRequestURL.length()).equals("historyFrame.html")){
				chain.doFilter(request, response);
			}*/
			String[] staffInfo = request.getParameterValues("info");
			if (staffInfo == null || staffInfo.length == 0) {				
					errorString = "提示！您没有登录。" +request.getRequestURL().toString()+"]";
					log.debug(errorString);
					forwardErrorPage(response, errorString);					
					return; 
				}
			else {
				staffNo = staffInfo[0];
					if (staffInfo.length > 1) {
						serialNo = staffInfo[1]; //参数大于1个,例如?info=11111&orgId=222222&info=9349rj4kk2j8lekjiep3
					}
					//未登录用户
					if (staffNo == null || serialNo == null || serialNo.length() == 0) {
						errorString = "---Request 参数为空!---";
						log.debug(errorString);
						forwardErrorPage(response, errorString);
						return;
					}
					String infoName = serialNo + ".txt";
					//读取文件
					SYSPERSON userbean =FileRead.reateObjectFile(infoName);
					
					if(userbean == null){
						errorString = "提示！您是非法登录。" +request.getRequestURL().toString()+"]";
						log.debug(errorString);
						forwardErrorPage(response, errorString);					
						return; 
					}
				}
			}		 
	     chain.doFilter(request, response);
	}	
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = config;
		this.servletContext = filterConfig.getServletContext();
		String strEffective = filterConfig.getInitParameter("isEffective");
		if (strEffective == null) {
			this.isEffective = false;
		} else if (strEffective.equalsIgnoreCase("true")) {
			this.isEffective = true;
		} else if (strEffective.equalsIgnoreCase("yes")) {
			this.isEffective = true;
		} else {
			this.isEffective = false;
		}
		String strEntryControl = filterConfig.getInitParameter("isEntryControl");
		log.debug("strEntryControl:" + strEntryControl);
		if (strEntryControl == null) {
			this.isEntryControl = false;
		} else if (strEntryControl.equalsIgnoreCase("true")) {
			this.isEntryControl = true;
		} else if (strEntryControl.equalsIgnoreCase("yes")) {
			this.isEntryControl = true;
		} else {
			this.isEntryControl = false;
		}
	}
	
	/**
	 * 定位到出错页面
	 * @param response
	 * @throws Exception
	 */
	public void forwardErrorPage(HttpServletResponse response, String errorInfo) throws ServletException, IOException {
		InputStream is = ClassLoader.getSystemResourceAsStream("dbConfig.properties");
		response.setContentType("text/html;charset=UTF-8");
		ServletOutputStream sos = response.getOutputStream();
		if(is == null){
			log.debug("找不到出错定位文件! ");
			sos.println("<script>");
			sos.println("alert(\"" + errorInfo+":\")");
			sos.println("top.location=\'http://10.1.6.13:9080/systemmanager\';");
			sos.println("</script>");
			sos.close();
		}
		else{
			Properties prop = new Properties();
			prop.load(is);			
			sos.println("<script>");
			sos.println("alert(\"" + errorInfo+":" +prop.getProperty("SysLoginPage") + "\")");
			sos.println("top.location=\'"+prop.getProperty("SysLoginPage")+"';");
			sos.println("</script>");
			sos.close();
		}	
	}
}
