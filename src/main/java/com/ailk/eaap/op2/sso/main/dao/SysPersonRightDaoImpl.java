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
public class SysPersonRightDaoImpl implements ISysPersonRightDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
	
	
	public void deleteSysPersonRight(String sysRightId) {
		try{
			sqlMapDao.delete("sysPersonRight.deleteSysPersonRightById", sysRightId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysPersonRight(Map map) {
		List sysPersonRightList = new ArrayList();
		try{
			sysPersonRightList = sqlMapDao.queryForList("sysPersonRight.queryAllSysPersonRight", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPersonRightList != null)
			return (List<Map>)sysPersonRightList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysPersonRight.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public List<Map> queryNotSelectedFunction(Map map) {
		List sysFunctionList = new ArrayList();
		try{
			if(map.get("authSysPersonId").equals("1") && map.get("sysPersonId")!=null && map.get("sysPersonId").equals("1")){
				//超级管理员给自己授权时
				sysFunctionList = sqlMapDao.queryForList("sysPersonRight.queryNotSelectedFunctionSuper", map);
			}else{
				sysFunctionList = sqlMapDao.queryForList("sysPersonRight.queryNotSelectedFunction", map);
			}
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysFunctionList != null)
			return (List<Map>)sysFunctionList;
		return null;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List sysRoleList = new ArrayList();
		try{
			if(map.get("authSysPersonId").equals("1") && map.get("sysPersonId")!=null && map.get("sysPersonId").equals("1")){
				//超级管理员给自己授权时
				sysRoleList = sqlMapDao.queryForList("sysPersonRight.queryNotSelectedRoleSuper", map);
			}else{
				sysRoleList = sqlMapDao.queryForList("sysPersonRight.queryNotSelectedRole", map);
			}
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleList != null)
			return (List<Map>)sysRoleList;
		return null;
	}

	public void insertSysPersonRight(List<Map> list) {
		try{
			for(int i=0; i<list.size(); i++){
				sqlMapDao.addBatch("sysPersonRight.insertSysPersonRight", list.get(i));
			}
			sqlMapDao.executeBatch();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public String querySeq() {
		Object seq = new Object();
		try{
			seq = sqlMapDao.queryForObject("sysPersonRight.querySeq", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(seq!=null)
			return seq.toString();
		return null;
	}

	public Map querySysPersonRightById(String sysRightId) {
		Object sysPersonRight = new Object();
		try{
			sysPersonRight = sqlMapDao.queryForObject("sysPersonRight.querySysPersonRightById", sysRightId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPersonRight != null)
			return (Map)sysPersonRight;
		return null;
	}

	public void updateSysPersonRight(Map map) {
		try{
			sqlMapDao.update("sysPersonRight.updateSysPersonRight", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

}
