package com.ailk.eaap.op2.sso.main.service;


import com.ailk.eaap.op2.sso.main.dao.IMsgAuthDao;

public class MsgAuthServiceImpl implements IMsgAuthService {
	private IMsgAuthDao msgAuthDao;

	public void insertMsgAuth(String authCode, String sysPersonId) {
		msgAuthDao.insertMsgAuth(authCode, sysPersonId);
	}

	public String queryAuthCodeBySysperid(String sysPersonId) {
		String authCode = msgAuthDao.queryAuthCodeBySysperid(sysPersonId);
		return authCode;
	}

	public void updateMsgAuthState(String sysPersonId) {
		msgAuthDao.updateMsgAuthState(sysPersonId);
	}

	public IMsgAuthDao getMsgAuthDao() {
		return msgAuthDao;
	}

	public void setMsgAuthDao(IMsgAuthDao msgAuthDao) {
		this.msgAuthDao = msgAuthDao;
	}

}
