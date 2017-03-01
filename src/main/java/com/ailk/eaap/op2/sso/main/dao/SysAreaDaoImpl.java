package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.linkage.rainbow.dao.SqlMapDAO;

public class SysAreaDaoImpl implements ISysAreaDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
	public List<SysArea> queryAll() {
		List sysAreaList = new ArrayList();
		try{
			sysAreaList = sqlMapDao.queryForList("sysArea.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysAreaList != null)
			return (List<SysArea>)sysAreaList;
		return null;
    }
}
