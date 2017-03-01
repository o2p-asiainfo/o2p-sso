package com.ailk.eaap.op2.sso.framework.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.opensymphony.xwork2.ActionContext;

/**
 * 此过滤器为了适应老UAP
 * 未用新的拦截器，代码遵循老UAP拦截器逻辑
 * @author zhaobl
 *
 */
public class LoginFilter implements Filter{
	private String loginUrl;
	
	private IPersonServ personServ = (IPersonServ)SpringBeanInvoker.getBean("personServ");
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		//不拦截登录时的页面和action  公共页面/代理页面   密码重置页面   重定向拦截页面
		if(url.indexOf("/login/") != -1 || url.indexOf("/main/index!logOut.shtml") != -1 || url.indexOf("/password") != -1 
				|| url.indexOf("/ssoIntercept.jsp") != -1 || url.indexOf("/common.jsp") != -1 || url.indexOf("/proxy.html") != -1){
			chain.doFilter(request, response);
			return;
		}
		
		if(session.getAttribute("cardNumber") != null){
			//读取cookie
			Cookie[] cookies = request.getCookies();
			Cookie sCookie = null;
			String cardNumber = null;
			String sysPersonId = null;
			String tenantId=null;
			if(cookies!=null){
				for (int i = 0; i < cookies.length; i++) {
					sCookie = cookies[i];
					if("cardNumber".equals(sCookie.getName())){
						cardNumber = sCookie.getValue();
					}else if("sysPersonId".equals(sCookie.getName())){
						sysPersonId = sCookie.getValue();
					}else if("Tenant.TenantId".equals(sCookie.getName())){
						tenantId = sCookie.getValue();
					}
				}
			}
			aSysPerson sysPerson = null;
			if(cardNumber!=null&&!"".equals(cardNumber)&&sysPersonId!=null&&!"".equals(sysPersonId) && tenantId!=null && !"".equals(tenantId)) {
				Map map =new HashMap();
				map.put("username", cardNumber);
				map.put("tenantId", tenantId);
				sysPerson = personServ.querySysPerson(map);
			}
			if(sysPerson != null){
				chain.doFilter(request, response);
			}else{
				response.sendRedirect(loginUrl);
			}
		}else {
			response.sendRedirect(loginUrl);
		}
		
		
	}

	public void init(FilterConfig fc) throws ServletException {
		this.loginUrl = fc.getInitParameter("loginUrl");
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
