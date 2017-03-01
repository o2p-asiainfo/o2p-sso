package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.model.SysLogLogin;
import com.linkage.rainbow.dao.SqlMapDAO;



public class SysLogLoginDAOImpl implements ISysLogLoginDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
	
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
    
	public void insertSysLogLogin(SysLogLogin sysLogLogin) {
		try{
			sqlMapDao.insert("sysLogLogin.insertSysLogLogin", sysLogLogin);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysLogLogin(Map map) {
		List sysLogLoginList = new ArrayList();
		try{
			sysLogLoginList = sqlMapDao.queryForList("sysLogLogin.queryAllSysLogLogin", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysLogLoginList != null)
			return (List<Map>)sysLogLoginList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysLogLogin.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	
	
	
	
	
	
//	public SysLogLoginDAOImpl() {
//		super();
//	}
//
//	public Integer insert(SysLogLogin record) {
//		Object newKey = getSqlMapClientTemplate().insert("SysLogLogin.insert",
//				record);
//		return (Integer) newKey;
//	}
//
//	public int updateByPrimaryKey(SysLogLogin record) {
//		int rows = getSqlMapClientTemplate().update(
//				"SysLogLogin.updateByPrimaryKey", record);
//		return rows;
//	}
//
//	public int updateByPrimaryKeySelective(SysLogLogin record) {
//		int rows = getSqlMapClientTemplate().update(
//				"SysLogLogin.updateByPrimaryKeySelective", record);
//		return rows;
//	}
//
//	public List selectByExample(SysLogLogin example) {
//		List list = getSqlMapClientTemplate().queryForList(
//				"SysLogLogin.selectByExample", example);
//		return list;
//	}
//
//	public Page selectPageByExample(SysLogLoginExample example) {
//		List list = getSqlMapClientTemplate().queryForList(
//				"SysLogLogin.selectPageByExample", example);
//		Integer total = (Integer) getSqlMapClientTemplate().queryForObject(
//				"SysLogLogin.selectCountByExample", example);
//		return null;
////		return new PageImp(list, total, example.getPageNumber(), example
////				.getPageSize());
//	}
//
//	public Integer selectCountByExample(SysLogLoginExample example) {
//		Integer total = (Integer) getSqlMapClientTemplate().queryForObject(
//				"SysLogLogin.selectCountByExample", example);
//		return total;
//	}
//
//	public SysLogLogin selectByPrimaryKey(Integer sysLoginId) {
//		SysLogLogin key = new SysLogLogin();
//		key.setSysLoginId(sysLoginId);
//		SysLogLogin record = (SysLogLogin) getSqlMapClientTemplate()
//				.queryForObject("SysLogLogin.selectByPrimaryKey", key);
//		return record;
//	}
//
//	public int deleteByExample(SysLogLoginExample example) {
//		int rows = getSqlMapClientTemplate().delete(
//				"SysLogLogin.deleteByExample", example);
//		return rows;
//	}
//
//	public int deleteByPrimaryKey(SysLogLogin key) {
//		if (key.getSysLoginId() == null || key.getSysLoginId().equals(""))
//			throw new RuntimeException("�����ɾ��ʱ�����ֵ����Ϊ�գ�");
//		int rows = getSqlMapClientTemplate().delete(
//				"SysLogLogin.deleteByPrimaryKey", key);
//		return rows;
//	}
//
//	public int updateLog(SysLogLogin record) {
//		int rows = getSqlMapClientTemplate().update(
//				"SysLogLogin.updateByPrimaryKeyLogin", record);
//		return rows;
//	}
//
//	public Integer insertErrorNumber(SysLogLogin record) {
//			Object newKey=getSqlMapClientTemplate().insert("SysLogLogin.insertErrorNumber", record);
//		   return (Integer)newKey;
//	}
}