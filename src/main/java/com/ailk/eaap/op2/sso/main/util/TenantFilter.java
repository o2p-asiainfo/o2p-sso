package com.ailk.eaap.op2.sso.main.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.ailk.eaap.op2.sso.main.service.PersonServ;
import com.ailk.eaap.op2.util.SpringContextUtil;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.sqllog.util.OperateActContext;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.O2pWebCommonUtil;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;

/**
 * @ClassName: TenantFilter
 * @Description: 
 * @author zhengpeng
 * @date 2016-5-23 上午10:04:46
 *
 */
public class TenantFilter implements Filter{

	private Logger log = Logger.getLog(this.getClass());
	private static final String SSO_LOGIN_URL = "/login/login.jsp";
	public static final String TENANT_ISINTERCEPTOR = "portal_tenant_isInterceptor";
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("SsoRewriteFilter just supports HTTP requests");
		}
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest)request);
		StringBuffer url = requestWrapper.getRequestURL();
		if(url.lastIndexOf(SSO_LOGIN_URL) > 0){
			try{
				UserRoleInfo userRoleInfo = new UserRoleInfo();
				Tenant tenant = new Tenant();
				PersonServ personServ = (PersonServ) SpringContextUtil.getBean("personServ");
				Tenant tenantMap = new Tenant(); 
				tenantMap.setStatus("A");										//租对状态：A:在用;D:失效;L:冻结;W:等待开通
				String tenantIsinterceptor = WebPropertyUtil.getPropertyValue(TENANT_ISINTERCEPTOR);
				System.out.println("------ portal_tenant_isInterceptor = "+tenantIsinterceptor);
				if("true".equals(tenantIsinterceptor)){
					//多租户工程（从域名中获取租户Code，多租户工程必需用域名形式访问）：
					String tenantCode = this.getTenantCodeByUrl(requestWrapper);
					System.out.println("------ getTenantCodeByUrl = "+tenantCode);
					//PersonServ personService = (PersonServ) SpringContextUtil.getBean("personServ");
					//String tenantId = personService.queryTenantIdByCode(tenantCode);
					tenantMap.setCode(tenantCode);
					tenant =personServ.getTenant(tenantMap);		//获取租对象
				}else{
					//非多租户工程，租户ID设为默认值：
					Integer defalutTenantId = O2pWebCommonUtil.getDefalutTenantId();  	//默认租户ID
					System.out.println("------ DefalutTenantId = "+defalutTenantId);
					tenantMap.setTenantId(defalutTenantId);		
					tenant =personServ.getTenant(tenantMap);		//获取租对象
				}
				if(tenant !=null && tenant.getTenantId() !=null){
					Integer tenantId = tenant.getTenantId();
					String tenantCode = tenant.getCode();
					userRoleInfo.setTenantCode(tenantCode);
					userRoleInfo.setTenantId(tenantId); 
					requestWrapper.getSession().setAttribute("tenantId", tenantId);
					OperateActContext.setUserInfo(userRoleInfo);
				}
			}catch (Exception e) {
				System.out.println("------ TenantFilter  personServ.getTenant()  Failure !");
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),e));
			}
		}
		chain.doFilter(request, response);
	}
	
	private String getTenantCodeByUrl(HttpServletRequest request){
	    StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
		int startIndex = 0;
		String indexStr = "://";
		if(contextUrl.indexOf(indexStr) > 0){
			startIndex = contextUrl.indexOf(indexStr)+indexStr.length();
		}
		contextUrl = contextUrl.substring(startIndex, contextUrl.indexOf("."));
		return contextUrl;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
