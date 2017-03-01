package com.ailk.eaap.op2.sso.main.dao;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.ailk.eaap.op2.sso.main.model.SysFunction;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.linkage.rainbow.dao.SqlMapDAO;

/**
 * 功能点Dao
 * @author zhaobl
 *
 */
public class SysFunctionDaoImpl implements ISysFunctionDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
	public int deleteSysFunction(String functionId) {
		try{
			sqlMapDao.delete("eaap-op2-sso-sysFunction.deleteSysFunctionById", functionId);
			return 0;
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    		return 1;
		}
	}

	public void insertSysFunction(Map map) {
		try{
			Object shownum = sqlMapDao.queryForObject("eaap-op2-sso-sysFunction.queryShownum", map.get("parentFunctionId"));
			if(shownum == null)
				shownum = 1;
			map.put("shownum", shownum);
			sqlMapDao.insert("eaap-op2-sso-sysFunction.insertSysFunction", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<SysFunction> queryAll() {
		List sysFunctionList = new ArrayList();
		try{
			sysFunctionList = sqlMapDao.queryForList("eaap-op2-sso-sysFunction.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysFunctionList != null)
			return (List<SysFunction>)sysFunctionList;
		return null;
	}

	public List<SysFunction> queryAllSysFunction(Map map) {
		List sysFunctionList = new ArrayList();
		try{
			sysFunctionList = sqlMapDao.queryForList("eaap-op2-sso-sysFunction.queryAllSysFunction", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysFunctionList != null)
			return (List<SysFunction>)sysFunctionList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("eaap-op2-sso-sysFunction.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public SysFunction querySysFunctionById(String functionId) {
		Object sysFunction = new Object();
		try{
			sysFunction = sqlMapDao.queryForObject("eaap-op2-sso-sysFunction.querySysFunctionById", functionId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysFunction != null)
			return (SysFunction)sysFunction;
		return null;
	}

	public void updateSysFunction(Map map) {
		try{
			sqlMapDao.update("eaap-op2-sso-sysFunction.updateSysFunctionById", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public boolean isFunctionExist(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("eaap-op2-sso-sysFunction.isFunctionExist", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(count > 0)
			return true;
		return false;
	}
	
}