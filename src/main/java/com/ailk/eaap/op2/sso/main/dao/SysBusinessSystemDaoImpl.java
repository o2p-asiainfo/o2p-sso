package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
import com.linkage.rainbow.dao.SqlMapDAO;

public class SysBusinessSystemDaoImpl implements ISysBusinessSystemDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass()); 

	public List<SysBusinessSystem> querySysBusinessSystem() {
		List list = new ArrayList();
		try{
			list = sqlMapDao.queryForList("eaap-op2-sso-sysBusinessSystem.querySysBusinessSystem", null);
		}catch(Exception e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(list == null)
			return null;
		return (List<SysBusinessSystem>)list;
	}

	public SysBusinessSystem querySysBusinessSystemById(String businessSystemId) {
		Object sysBusinessSystem = new Object();
		try{
			sysBusinessSystem = sqlMapDao.queryForObject("eaap-op2-sso-sysBusinessSystem.querySysBusinessSystemById", businessSystemId);
		}catch(Exception e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysBusinessSystem == null)
			return null;
		return (SysBusinessSystem)sysBusinessSystem;
	}
	
	
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
