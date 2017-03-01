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
public class SysDeptRoleDaoImpl implements ISysDeptRoleDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
	
	public void deleteSysDeptRole(String deptRoleId) {
		try{
			sqlMapDao.delete("sysDeptRole.deleteSysDeptRoleById", deptRoleId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysDeptRole(Map map) {
		List sysDeptRoleList = new ArrayList();
		try{
			sysDeptRoleList = sqlMapDao.queryForList("sysDeptRole.queryAllSysDeptRole", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysDeptRoleList != null)
			return (List<Map>)sysDeptRoleList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysDeptRole.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List sysRoleList = new ArrayList();
		try{
			sysRoleList = sqlMapDao.queryForList("sysDeptRole.queryNotSelectedRoleByDeptId", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleList != null)
			return (List<Map>)sysRoleList;
		return null;
	}

	public void insertSysDeptRole(List<Map> list) {
		try{
			for(int i=0; i<list.size(); i++){
				sqlMapDao.addBatch("sysDeptRole.insertSysDeptRole", list.get(i));
			}
			sqlMapDao.executeBatch();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public String querySeq() {
		Object seq = new Object();
		try{
			seq = sqlMapDao.queryForObject("sysDeptRole.querySeq", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(seq!=null)
			return seq.toString();
		return null;
	}

	
}
