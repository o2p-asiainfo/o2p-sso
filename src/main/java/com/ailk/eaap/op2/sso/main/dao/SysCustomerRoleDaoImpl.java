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
public class SysCustomerRoleDaoImpl implements ISysCustomerRoleDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
	public void deleteSysCustomerRole(String sysCustomerRoleId) {
		try{
			sqlMapDao.delete("sysCustomerRole.deleteSysCustomerRoleById", sysCustomerRoleId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysCustomerRole(Map map) {
		List sysCustomerRoleList = new ArrayList();
		try{
			sysCustomerRoleList = sqlMapDao.queryForList("sysCustomerRole.queryAllSysCustomerRole", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysCustomerRoleList != null)
			return (List<Map>)sysCustomerRoleList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysCustomerRole.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List sysRoleList = new ArrayList();
		try{
			sysRoleList = sqlMapDao.queryForList("sysCustomerRole.queryNotSelectedRoleByCustomerId", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleList != null)
			return (List<Map>)sysRoleList;
		return null;
	}

	public void insertSysCustomerRole(List<Map> list) {
		try{
			for(int i=0; i<list.size(); i++){
				sqlMapDao.addBatch("sysCustomerRole.insertSysCustomerRole", list.get(i));
			}
			sqlMapDao.executeBatch();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public String querySeq() {
		Object seq = new Object();
		try{
			seq = sqlMapDao.queryForObject("sysCustomerRole.querySeq", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(seq!=null)
			return seq.toString();
		return null;
	}

	
}
