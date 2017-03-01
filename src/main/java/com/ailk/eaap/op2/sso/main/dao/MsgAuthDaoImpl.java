package com.ailk.eaap.op2.sso.main.dao;

import java.util.HashMap;
import java.util.Map;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.framework.util.Constants;
import com.linkage.rainbow.dao.SqlMapDAO;

public class MsgAuthDaoImpl implements IMsgAuthDao{
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass()); 
	
	public void insertMsgAuth(String authCode, String sysPersonId) {
		Map map = new HashMap();
		map.put("authCode", authCode);
		map.put("sysPersonId", sysPersonId);
		map.put("authCodeTimeout",Constants.authCodeTimeout);
		try{
			sqlMapDao.insert("eaap-op2-sso-msgAuth.insertMsgAuth", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public String queryAuthCodeBySysperid(String sysPersonId) {
		Object authCode = new Object();
		try{
			 authCode = sqlMapDao.queryForObject("eaap-op2-sso-msgAuth.queryAuthCodeBySysperId", sysPersonId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(authCode == null)
			return null;
		return (String)authCode;
	}

	public void updateMsgAuthState(String sysPersonId) {
		try{
			sqlMapDao.update("eaap-op2-sso-msgAuth.updateMsgAuth", sysPersonId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
}
