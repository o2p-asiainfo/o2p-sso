package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.linkage.rainbow.dao.SqlMapDAO;

/**
 * 
 * @author zhaobl
 *
 */

public class SysRoleFunctionDaoImpl implements ISysRoleFunctionDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
	
	public void deleteSysRoleFunction(String sysRoleFuncId) {
		try{
			sqlMapDao.delete("sysRoleFunction.deleteSysRoleFunctionById", sysRoleFuncId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysRoleFunction(Map map) {
		List sysRoleFunctionList = new ArrayList();
		try{
			sysRoleFunctionList = sqlMapDao.queryForList("sysRoleFunction.queryAllSysRoleFunction", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleFunctionList != null)
			return (List<Map>)sysRoleFunctionList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysRoleFunction.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public List<Map> queryNotSelectedFunction(Map map) {
		List sysFunctionList = new ArrayList();
		try{
			sysFunctionList = sqlMapDao.queryForList("sysRoleFunction.queryNotSelectedFunctionByRoleId", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysFunctionList != null)
			return (List<Map>)sysFunctionList;
		return null;
	}

	public void insertSysRoleFunction(List<Map> list) {
		try{
			for(int i=0; i<list.size(); i++){
				sqlMapDao.addBatch("sysRoleFunction.insertSysRoleFunction", list.get(i));
			}
			sqlMapDao.executeBatch();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public String querySeq() {
		Object seq = new Object();
		try{
			seq = sqlMapDao.queryForObject("sysRoleFunction.querySeq", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(seq!=null)
			return seq.toString();
		return null;
	}

	


}
