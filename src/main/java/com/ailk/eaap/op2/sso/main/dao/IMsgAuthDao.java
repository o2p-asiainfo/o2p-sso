package com.ailk.eaap.op2.sso.main.dao;
/**
 * 短信验证
 * @author zhaobl
 *
 */
public interface IMsgAuthDao {
	/**
	 * 更新相应用户的短信验证码状态
	 * @param sysPersonId
	 */
	public void updateMsgAuthState(String sysPersonId);
	/**
	 * 插入相应用户新的短信验证码
	 * @param authCode
	 * @param sysPersonId
	 */
	public void insertMsgAuth(String authCode, String sysPersonId);
	/**
	 * 查询相应用户的短信验证码
	 * @param sysPersonId
	 * @return
	 */
	public String queryAuthCodeBySysperid(String sysPersonId);
}
