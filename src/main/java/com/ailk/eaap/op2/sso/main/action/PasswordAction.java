package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.Constants;
import com.ailk.eaap.op2.sso.framework.util.MD5;
import com.ailk.eaap.op2.sso.framework.util.SecurityCode;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IMsgAuthService;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 密码服务
 * @author zhaobl
 *
 */
public class PasswordAction extends BaseAction{
	private IPersonServ personServ = (IPersonServ)SpringBeanInvoker.getBean("personServ");
	private IMsgAuthService msgAuthServ = (IMsgAuthService)SpringBeanInvoker.getBean("eaap-op2-sso-msgAuthServ");
	private String oldPassword;
	private String newPassword;
	private String cardNumber; //用户工号
	private String authCode; //验证码
	private String tenantId;
	
	private ISysLogOperateService sysLogOperateServ;
	/**
	 * 修改密码
	 * @throws IOException 
	 */
	public void changePassword() throws IOException{
		/** 同一浏览器不同用户登录时 (为了不混淆，判断前后session中用户)**/
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute("cardNumber");		
		if(!userInfo.getUserId().equalsIgnoreCase(cardNumber)) {
			writeString("{\"result\" : 2}");
			return;
		}
		
		//操作日志
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("密码修改");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.PasswordAction");
		sysLogOperate.setMethodName("changePassword");
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);

		Map map =new HashMap();
		map.put("username", cardNumber);
		map.put("tenantId", tenantId);
		aSysPerson sysPerson = personServ.getSysPerson(map);
		String existOldPassMD5 = sysPerson.getPassword();
		String existLastPwdMD5 = sysPerson.getLastPassword();
		String oldPassMD5 = MD5.encode(oldPassword).toUpperCase();
		String newPassMD5 = MD5.encode(newPassword).toUpperCase();
		if(!oldPassMD5.equalsIgnoreCase(existOldPassMD5)){
			String json = "{\"result\": 1, \"message\": \""+ "旧密码输入错误，请重新输入..." +"\"}";
			writeString(json);
		}else if(newPassMD5.equalsIgnoreCase(existOldPassMD5) || newPassMD5.equalsIgnoreCase(existLastPwdMD5)){
			String json = "{\"result\": 1, \"message\": \""+ "新密码不能与上两次密码相同，请重新输入..." +"\"}";
			writeString(json);
		}else {
			String sysPersonId = sysPerson.getSysPersonId().toString();
			personServ.updatePassword(sysPersonId, newPassMD5);
			String json = "{\"result\": 0, \"message\": \""+ "密码修改成功，您将重新登录..." +"\"}";
			writeString(json);
		}
		
	}
	/**
	 * 判断验证码，并根据输入的用户名获得手机号等用户信息，用于发送短信验证
	 * @throws IOException
	 */
	public void getMobileByCardnumber() throws IOException{
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		String seesionAuthCode = (String)session.getAttribute("checkcode"); 
		if(!seesionAuthCode.equals(authCode)){
			String json = "{\"result\": 1, \"message\": \""+ "验证码输入错误" +"\"}";
			writeString(json);
		}else {
			Map mapPs =new HashMap();
			mapPs.put("username", cardNumber);
			mapPs.put("tenantId", tenantId);
			aSysPerson sysPerson = personServ.querySysPerson(mapPs);
			if(sysPerson == null){
				String json = "{\"result\": 2, \"message\": \""+ "用户名不存在" +"\"}";
				writeString(json);
			}else{
				String mobile = sysPerson.getMobile();
				if(mobile != null && mobile.length()==11){
					String a = mobile.substring(0,3);
					String b = mobile.substring(8);
					mobile = a + "*****" + b;
				}
				String json = "{\"result\": 0, \"mobile\": \""+ mobile +"\"}";
				writeString(json);
			}
		}
	}
	/**
	 * 发送短信验证码
	 */
	public void sendMsgAuthcode(){
		Map map =new HashMap();
		map.put("username", cardNumber);
		map.put("tenantId", tenantId);
		aSysPerson sysPerson = personServ.getSysPerson(map);
		if(sysPerson != null){
			String authCode= SecurityCode.getSecurityCode();
			String sysPersonId = sysPerson.getSysPersonId().toString();
			//新增短信验证码时，先将这个用户下所有短信验证码的状态置为失效
			msgAuthServ.updateMsgAuthState(sysPersonId);
			//新增短信验证码
			msgAuthServ.insertMsgAuth(authCode, sysPersonId);
		}
	}
	/**
	 * 验证短信验证码
	 * @throws IOException 
	 */
	public void msgAuthcodeValidate() throws IOException{
		Map map =new HashMap();
		map.put("username", cardNumber);
		map.put("tenantId", tenantId);
		aSysPerson sysPerson = personServ.getSysPerson(map);
		if(sysPerson != null){
			String sysPersonId = sysPerson.getSysPersonId().toString();
			String code = msgAuthServ.queryAuthCodeBySysperid(sysPersonId);
			if(code == null){
				String json = "{\"result\": 1, \"message\": \""+ "验证码过期，请重新获取" +"\"}";
				writeString(json);
			}else if(!code.equals(authCode)){
				String json = "{\"result\": 2, \"message\": \""+ "验证码错误" +"\"}";
				writeString(json);
			}else{
				//重置用户密码
				String passwordMD5 = MD5.encode(Constants.defaultPassword).toUpperCase();
				personServ.updatePassword(sysPersonId, passwordMD5);
				
				String json = "{\"result\": 0, \"defaultPassword\": \""+ Constants.defaultPassword +"\"}";
				writeString(json);
			}
		}
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
