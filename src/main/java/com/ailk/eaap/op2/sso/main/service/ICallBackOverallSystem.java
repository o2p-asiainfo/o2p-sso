package com.ailk.eaap.op2.sso.main.service;

import com.ailk.eaap.op2.common.EAAPException;

/**
 * 
 * @Package com.ailk.eaap.op2.sso.main.service
 * @ClassName: ICallBackOverallSystem
 * @Description: TODO(回调能力管理平台的接口)
 * @author sun
 * @date 2013-6-5 上午10:02:04
 */
public interface ICallBackOverallSystem {

	/**
	 * 
	 * @Title: getUserInfoByLSeeionIdAndTicketId
	 * @Description: TODO(回访获取用户信息接口)
	 * @param lSessionId 各系统本地lsessionId
	 * @param ticketId  票据Id
	 * @param qSessionId  全局qsessionId
	 * @param localSystemId  各系统标识
	 * @return String 传出参数是xml,包含用户信息
	 */
	public String getUserInfoByLSeeionIdAndTicketId(String qSessionId,String lSessionId, String ticketId, String localSystemId);
	/**
	 * 
	 * @Title: stayLoggedIn
	 * @Description: TODO(保持登录状态)
	 * @param isStay  0代表一直在操作
	 * @param lSessionId 各系统本地sessionId
	 */
	public void stayLoggedIn(String isStay, String lSessionId);



}
