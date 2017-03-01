package com.ailk.eaap.op2.sso.main.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ailk.eaap.o2p.common.util.CustomBase64;
import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.MD5;
import com.ailk.eaap.op2.sso.framework.util.SHA256;
import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.SysLogLogin;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysBusinessSystemService;
import com.ailk.eaap.op2.sso.main.service.ISysLogLoginService;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.session.web.http.CookieUtil;
import com.asiainfo.integration.o2p.session.web.http.O2pCookie;
import com.asiainfo.integration.o2p.session.web.manage.SSOUserFactory;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.O2pWebCommonUtil;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.ui.struts.BaseAction;
import com.linkage.rainbow.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
/**
 * 登录action
 * @author zhaobl
 *
 */
public class LoginAction  extends BaseAction{

	private static final long serialVersionUID = 1L;
	private String domain;//域名
	private Logger log = Logger.getLog(this.getClass());
	private String username;
	private String password;
	private String checkcode;
	private String timeOffset;
	private String tenantCode;
	private Tenant tenant = new Tenant();
	private String tenantId;
	public static final String TENANT_ISINTERCEPTOR = "portal_tenant_isInterceptor";

	private IPersonServ personServ;	
	private ISysBusinessSystemService sysBusinessSystemServ = (ISysBusinessSystemService)SpringBeanInvoker.getBean("eaap-op2-sso-sysBusinessSystemServ");
	private ISysLogLoginService sysLogLoginServ = (ISysLogLoginService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogLoginServ");


	public String loginForm(){
		Tenant tenantMap = new Tenant(); 
		tenantMap.setCode(tenantCode);
		tenant =personServ.getTenant(tenantMap);		//获取租对象
		return SUCCESS;
	}
	
	public void getTenantInfo(){
		PrintWriter out =null;
		try {
			JSONObject retJson = new JSONObject ();
			HttpServletRequest request = getRequest(); 

			Tenant tenantMap = new Tenant(); 
			tenantMap.setStatus("A");										//租对状态：A:在用;D:失效;L:冻结;W:等待开通
			if("true".equals(WebPropertyUtil.getPropertyValue(TENANT_ISINTERCEPTOR))){
				String tenantId = this.getTenantIdByUrl(request);
				if(!StringUtil.isEmpty(tenantId)){
					tenantMap.setTenantId(Integer.valueOf(tenantId));
					tenant =personServ.getTenant(tenantMap);
				}else{
					String tenantCode = this.getTenantCodeByUrl(request);
					if(!StringUtil.isEmpty(tenantCode)){
						tenantMap.setCode(tenantCode);
						tenant =personServ.getTenant(tenantMap);
					}else{
						tenant=null;
					}
				}
			}else{
				tenantMap.setTenantId(O2pWebCommonUtil.getDefalutTenantId());
				tenant =personServ.getTenant(tenantMap);		//获取租对象
			}
			
			if(tenant!=null){
				JSONObject tenantJson = new JSONObject ();
				tenantJson.put("tenantId", tenant.getTenantId());
				tenantJson.put("name", tenant.getName());
				tenantJson.put("code", tenant.getCode());
				tenantJson.put("logo", tenant.getLogo());
	
				retJson.put("RespCode", "000");
				retJson.put("RespDesc", "Successful");
				retJson.put("tenant", tenantJson);
			}else{
				//根据租户Code找不到租户信息，URL地址错误
				retJson.put("RespCode", "111");
				retJson.put("RespDesc", "Fail");
			}
		    getResponse().setContentType("text/html;charset=UTF-8");
		    out = getResponse().getWriter();
			out.print(retJson.toString());
		} catch (Exception e) {
			try {
				getResponse().setContentType("text/html;charset=UTF-8");
				out = getResponse().getWriter();
				String retJson = "{\"RespCode\":\"400\",\"RespDesc\":\""+e.getMessage()+"\"}";
				out.print(retJson);
			} catch (IOException e1) {
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),e));
			}
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),e));
		}finally{
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	
	/**
	 * 老UAP校验  (单点登录也去掉)
	 * @Title: login
	 * @Description:登录
	 * @return 
	 */
	public void login() throws IOException{
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = getResponse();
		if(tenantId ==null || tenantId ==""){
			if("true".equals(WebPropertyUtil.getPropertyValue(TENANT_ISINTERCEPTOR))){
				Tenant tenantMap = new Tenant(); 
				tenantMap.setStatus("A");	 //租对状态：A:在用;D:失效;L:冻结;W:等待开通	
				String _tenantId = this.getTenantIdByUrl(request);
				if(!StringUtil.isEmpty(_tenantId)){
					tenantMap.setTenantId(Integer.valueOf(_tenantId));
					tenant =personServ.getTenant(tenantMap);		//获取租对象
					tenantId = String.valueOf(tenant.getTenantId());
				}else{
					String tenantCode = this.getTenantCodeByUrl(request);
					if(!StringUtil.isEmpty(tenantCode)){
						tenantMap.setCode(tenantCode);
						tenant =personServ.getTenant(tenantMap);	//获取租对象
						tenantId = String.valueOf(tenant.getTenantId());
					}
				}
			}else{
				tenantId = String.valueOf(O2pWebCommonUtil.getDefalutTenantId()); 
			}
		}		
		//登录日志
		SysLogLogin sysLogLogin = new SysLogLogin();
		sysLogLogin.setCardNumber(username);
		sysLogLogin.setIp(this.getSourceIp(request));
		log.info(LogModel.EVENT_USER_AUTHENTICATE,getText("eaap.op2.sso.login.tryLogin"),username);
		/***校验验证码***/
		String seesionCheckCode = (String)session.getAttribute("checkcode");
		log.debug("####seesionCheckCode:" + seesionCheckCode);
		if(checkcode.toUpperCase().equals("O2P") || checkcode.equalsIgnoreCase(seesionCheckCode)){
			/***校验用户名 密码***/
			Map map =new HashMap();
			map.put("username", username);
			map.put("tenantId", tenantId);
			aSysPerson sysPerson = personServ.getSysPerson(map);
			if(sysPerson != null && SHA256.encode(sysPerson.getPassword()).equals(password)) {
//				session.invalidate();
				sysLogLogin.setSysPersonId(sysPerson.getSysPersonId().toString());
				sysLogLogin.setAuthRspType("0");
				sysLogLogin.setAuthRspDesc(this.getText("eaap.op2.sso.login.success"));
//				sysLogLoginServ.insertSysLogLogin(sysLogLogin);
				
				//生成userInfo  
				UserInfo userInfo = new UserInfo();
				if(sysPerson.getCardNumber() != null)
					userInfo.setUserId(sysPerson.getCardNumber());
				if(sysPerson.getSysPersonId() != null)
					userInfo.setId(sysPerson.getSysPersonId().toString());
				if(sysPerson.getName() != null)
					userInfo.setUserName(sysPerson.getName());
				if(sysPerson.getSysAreaId() != null)
					userInfo.setSysAreaId(sysPerson.getSysAreaId().toString());
				if(sysPerson.getSysStatusId() != null)
					userInfo.setStatus(sysPerson.getSysStatusId().toString());
				if(sysPerson.getMobile() != null)
					userInfo.setTelephone(sysPerson.getMobile());
				if(sysPerson.getEmail() != null)
					userInfo.setEmail(sysPerson.getEmail());
				SysDept sysDept = sysPerson.getSysDept();
				if(sysDept != null && sysDept.getDeptName() != null)
					userInfo.setDeptName(sysDept.getDeptName());
				//生成票据
				long ticketId = (long) (Math.random()*900000 + 100000);
				userInfo.setTicketId(ticketId + "");	

				request.getSession().setAttribute("cardNumber", userInfo);//用户信息
				request.getSession().setAttribute("sysIdType", sysPerson.getSysIdTypeId()); //用户身份类型（管理员。。）
				log.info(LogModel.EVENT_USER_AUTHENTICATE,getText("eaap.op2.sso.login.successLogin"),username);

				//把用户名放入cookie
				request.getSession().setAttribute(EAAPConstants.O2P_USER_NAME, username);
				this.addUserNameToCookie(username, response); 
				//把时区放入cookie
				request.getSession().setAttribute(EAAPConstants.TIME_OFFSET, timeOffset); 
				this.addTimeOffsetToCookie(timeOffset,response);
				
				//把用户租户信息放入Session、cookie
				this.setTenantInfoToSessionCookie(sysPerson,request,response);
				SSOUserFactory.getDefaultSsoUserCache().addUser(username, session.getId());
				request.getSession().setMaxInactiveInterval(this.getMaxInactiveInterval()); 
				//用户权限放入cookie
			    String stringJson ="[{\"result\":\"0\"}]";
				writeString(stringJson);
			}else {
				sysLogLogin.setAuthRspType("1");
				sysLogLogin.setAuthRspDesc(this.getText("eaap.op2.sso.login.failed"));
//				sysLogLoginServ.insertSysLogLogin(sysLogLogin);
				
				session.setAttribute("errorMessage", this.getText("eaap.op2.sso.login.failed"));
				log.debug(this.getText("eaap.op2.sso.login.failed"));
				String stringJson ="[{\"result\":\"1\",\"errorMessage\":\""+this.getText("eaap.op2.sso.login.failed")+"\"}]";
				writeString(stringJson);
			}			
		}else{
			sysLogLogin.setAuthRspType("1");
			sysLogLogin.setAuthRspDesc(this.getText("eaap.op2.sso.login.checkcode.error"));
//			sysLogLoginServ.insertSysLogLogin(sysLogLogin);
			
			session.setAttribute("errorMessage", this.getText("eaap.op2.sso.login.checkcode.error"));
			log.debug(this.getText("eaap.op2.sso.login.checkcode.error"));
			String stringJson ="[{\"result\":\"1\",\"errorMessage\":\""+this.getText("eaap.op2.sso.login.checkcode.error")+"\"}]";
			writeString(stringJson);
		}
	}
	
	private int getMaxInactiveInterval(){
		int maxInactive = 60 * 15;
		if(!StringUtil.isEmpty(WebPropertyUtil.getPropertyValue("o2p_session_max_inactive"))){
			maxInactive = Integer.valueOf(WebPropertyUtil.getPropertyValue("o2p_session_max_inactive").trim());
		}
		return maxInactive;
	}

	/**
	 * 把用户租户信息放入Session、cookie
	 * @param tenantId
	 * @param request
	 * @param response
	 */
	private void setTenantInfoToSessionCookie(aSysPerson sysPerson,HttpServletRequest request,HttpServletResponse response){
		//把用户租户对象放入Session:
		Tenant tenantMap = new Tenant(); 
		tenantMap.setTenantId(Integer.valueOf(sysPerson.getTenantId()));
		Tenant tenant =personServ.getTenant(tenantMap);		//获取租对象
		request.getSession().setAttribute("Tenant",tenant);

		UserRoleInfo userRoleInfo = new UserRoleInfo();
		userRoleInfo.setId(String.valueOf(sysPerson.getSysPersonId()));
		userRoleInfo.setName(sysPerson.getName());
		userRoleInfo.setTenantId(Integer.valueOf(sysPerson.getTenantId()));	//租户ID
		userRoleInfo.setTenantCode(tenant.getCode()==null?"":tenant.getCode().toString());					//设置租户code
		request.getSession().setAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY, userRoleInfo);
		
		//把用户租户ID放入Session：
		request.getSession().setAttribute(WebConstants.O2P_USER_TENANT_ID_KEY, sysPerson.getTenantId()); 
	}

	private void addTenantInfoToCookie(String cookName,String cookVal,HttpServletResponse response){
		String cookValBase64 = CustomBase64.encode(cookVal.getBytes());
		O2pCookie o2pCookie = new O2pCookie(cookName,cookValBase64);
		///o2pCookie.setMaxAge(1800);
		///o2pCookie.setHttpOnly(true);
		o2pCookie.setPath("/");
		response.addCookie(o2pCookie);
	}
	
	
	/**
	 * 把时区放入cookie
	 * @param timeOffset
	 * @param response
	 */
	private void addTimeOffsetToCookie(String timeOffset,HttpServletResponse response){
		Cookie timeOffsetCookie = new Cookie(EAAPConstants.TIME_OFFSET,timeOffset);
		timeOffsetCookie.setPath("/");
		response.addCookie(timeOffsetCookie);
//		O2pCookie o2pCookie = new O2pCookie(EAAPConstants.TIME_OFFSET,timeOffset);
//		o2pCookie.setMaxAge(1800);
//		o2pCookie.setHttpOnly(true);
//		o2pCookie.setPath("/");
//		CookieUtil.addCookie(o2pCookie, response);
	}
	
	/**
	 * 把用户名放入cookie
	 */
	private void addUserNameToCookie(String userName,HttpServletResponse response){
		String userNameBase64 = CustomBase64.encode(userName.getBytes());
		O2pCookie o2pCookie = new O2pCookie(EAAPConstants.O2P_USER_NAME,userNameBase64);
		//o2pCookie.setMaxAge(1800);
		o2pCookie.setHttpOnly(true);
		o2pCookie.setPath("/");
		CookieUtil.addCookie(o2pCookie, response); 
	}

	
	/**
	 * 强制登录,替换memcached里的用户信息
	 * @return
	 */
	public void forcedLogin(){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = getResponse();

		Map map =new HashMap();
		map.put("username", username);
		map.put("tenantId", tenantId);
		aSysPerson sysPerson = personServ.getSysPerson(map);
		
		//登录日志
		SysLogLogin sysLogLogin = new SysLogLogin();
		sysLogLogin.setSysPersonId(sysPerson.getSysPersonId().toString());
		sysLogLogin.setCardNumber(username);
		sysLogLogin.setIp(this.getSourceIp(request));
		sysLogLogin.setAuthRspType("0");
		sysLogLogin.setAuthRspDesc(this.getText("eaap.op2.sso.login.success"));
		sysLogLoginServ.insertSysLogLogin(sysLogLogin);
		
		//生成userInfo  
		UserInfo userInfo = new UserInfo();
		if(sysPerson.getCardNumber() != null)
			userInfo.setUserId(sysPerson.getCardNumber());
		if(sysPerson.getSysPersonId() != null)
			userInfo.setId(sysPerson.getSysPersonId().toString());
		if(sysPerson.getName() != null)
			userInfo.setUserName(sysPerson.getName());
		if(sysPerson.getSysAreaId() != null)
			userInfo.setSysAreaId(sysPerson.getSysAreaId().toString());
		if(sysPerson.getSysStatusId() != null)
			userInfo.setStatus(sysPerson.getSysStatusId().toString());
		if(sysPerson.getMobile() != null)
			userInfo.setTelephone(sysPerson.getMobile());
		if(sysPerson.getEmail() != null)
			userInfo.setEmail(sysPerson.getEmail());
		SysDept sysDept = sysPerson.getSysDept();
		if(sysDept != null && sysDept.getDeptName() != null)
			userInfo.setDeptName(sysDept.getDeptName());
		//生成票据
		long ticketId = (long) (Math.random()*900000 + 100000);
		userInfo.setTicketId(ticketId + "");	
		
//		/**** 加入拦截器后此处去掉*************/
//		session.setAttribute("cardNumber", userInfo);
//		session.setMaxInactiveInterval(-1);
//		/**** 加入拦截器后此处去掉*************/
		session.setAttribute("sysIdType", sysPerson.getSysIdTypeId()); //用户身份类型（管理员。。）
		session.setMaxInactiveInterval(-1);
		//将userInfo放入memcached 
//		try {
//			MemOperator.replace(username, 60*30, userInfo);
//		} catch (EAAPException e) {
//			e.printStackTrace();
//		}
		
		String ticketIdMD5 = MD5.encode(ticketId + "");
		Cookie ticketIdCookie = new Cookie("ticketId", ticketIdMD5);
		Cookie cardNumberCookie = new Cookie("cardNumber", username);
		ticketIdCookie.setDomain(domain);
		ticketIdCookie.setPath("/");
		cardNumberCookie.setDomain(domain);
		cardNumberCookie.setPath("/");
		log.info(LogModel.EVENT_USER_AUTHENTICATE,getText("eaap.op2.sso.login.successLogin"),username);
		response.addCookie(ticketIdCookie);
		response.addCookie(cardNumberCookie);
	}
	
	/**
	 * 
	 * @Title: generateCheckCode
	 * @Description: 生成验证码
	 * @throws ServletException
	 * @throws IOException void
	 */
	public void generateCheckCode()throws ServletException, IOException {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		int height = 20;
		int width = 60;

		//创造一个图片缓冲流，包含所有的图片信息，相当于画布
		BufferedImage buimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buimg.createGraphics();//创建一个绘图工具

		g.setColor(Color.WHITE);//设置底色
		g.fillRect(0, 0, width, height);//开始填充

		g.setColor(Color.blue);//设置边框颜色
		g.drawRect(0, 0, width-1, height-1);//绘制边框
		SecureRandom rand =null;
		try {
			rand = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e1) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),null));
		}
		g.setColor(Color.GRAY);//设置干扰线的颜色
		for (int i = 1; i <= 20; i++) {
		int x1 = rand.nextInt(width);
		int y1 = rand.nextInt(height);
		int x2 = rand.nextInt(width);
		int y2 = rand.nextInt(height);
		g.drawLine(x1, y1, x2, y2);//绘制干扰线
		}

		String code = "";//用来记录生成的验证码
		int red=0,green=0,blue=0;//记录三原色的值，用来变换验证码的颜色
		Font f = new Font("Times New Roman",Font.BOLD,18);//创建一个字体对象
		g.setFont(f);//设置验证码的字体
		for (int i = 1; i <= 4; i++) {
			String num = rand.nextInt(10)+"";//随机生成验证码中的一个数字
//			 System.out.println(num);
		//随机生成三原色
		red = rand.nextInt(100);
		green = rand.nextInt(100);
		blue = rand.nextInt(100);
		g.setColor(new Color(red,green,blue));//设置验证码的颜色
		g.drawString(num,11*i,16);//绘制验证码
		code+=num;//拼接四个验证码到一起
		}
//			 System.out.println(code);
		
		session.setAttribute("checkcode", code);//将验证码放入到session中，以便于登录检查使用
		//获得输出流，把图片发送给IE
		ServletOutputStream sos;
		try {
			sos = getResponse().getOutputStream();
			ImageIO.write(buimg,"png", sos);//开始发送
			sos.flush();
			sos.close();//关闭流
		} catch (IOException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		
	}
	
	/**
	 * 查询各个业务系统
	 */
	public void querySysBusinessSystem() {
		List<SysBusinessSystem> list = sysBusinessSystemServ.querySysBusinessSystem();
		JSONArray json = JSONArray.fromObject(list);
		try {
			writeString(json.toString());
		} catch (IOException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}
	/**
	 * 获得源IP
	 * @param request
	 * @return
	 */
	public String getSourceIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip != null) {
			int index = ip.indexOf(",");
			if (index != -1)
				ip = ip.substring(0, index);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	private String getTenantIdByUrl(HttpServletRequest request){
		return (String) request.getParameter("tid");
	}
	
	
	private String getTenantCodeByUrl(HttpServletRequest request){
    	StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
		int startIndex = 0;
		String indexStr = "://";
		if(contextUrl.indexOf(indexStr) > 0){
			startIndex = contextUrl.indexOf(indexStr)+indexStr.length();
		}
		if(contextUrl.indexOf(".") > -1){
			contextUrl = contextUrl.substring(startIndex, contextUrl.indexOf("."));
		}else{
			contextUrl = contextUrl.substring(startIndex, contextUrl.lastIndexOf(":"));  
		}
		return contextUrl;
	}
	
	
	public IPersonServ getPersonServ() {
		return personServ;
	}

	public void setPersonServ(IPersonServ personServ) {
		this.personServ = personServ;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(String timeOffset) {
		this.timeOffset = timeOffset;
	}

	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
