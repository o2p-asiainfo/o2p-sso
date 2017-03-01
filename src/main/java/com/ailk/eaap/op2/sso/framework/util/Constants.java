package com.ailk.eaap.op2.sso.framework.util;

public class Constants {
	/**
	 * 密码默认值
	 */
	public static String defaultPassword;
	/**
	 * 密码失效时间 单位：天
	 */
	public static int passwordTimeout;
	/**
	 * 短信验证码失效时间 单位：分钟
	 */
	public static int authCodeTimeout;
	
	public  String getDefaultPassword() {
		return defaultPassword;
	}
	public  void setDefaultPassword(String defaultPassword) {
		Constants.defaultPassword = defaultPassword;
	}
	public  int getPasswordTimeout() {
		return passwordTimeout;
	}
	public  void setPasswordTimeout(int passwordTimeout) {
		Constants.passwordTimeout = passwordTimeout;
	}
	public  int getAuthCodeTimeout() {
		return authCodeTimeout;
	}
	public  void setAuthCodeTimeout(int authCodeTimeout) {
		Constants.authCodeTimeout = authCodeTimeout;
	}
	
}
