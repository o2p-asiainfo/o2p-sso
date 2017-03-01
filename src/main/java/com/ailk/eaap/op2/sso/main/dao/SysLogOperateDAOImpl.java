package com.ailk.eaap.op2.sso.main.dao;


import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.linkage.rainbow.dao.SqlMapDAO;


public class SysLogOperateDAOImpl implements ISysLogOperateDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
	
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
	
	public void insertSysLogOperate(SysLogOperate sysLogOperate) {
		try{
			sqlMapDao.insert("sysLogOperate.insertSysLogOperate", sysLogOperate);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysLogOperate(Map map) {
		List sysLogOperateList = new ArrayList();
		try{
			sysLogOperateList = sqlMapDao.queryForList("sysLogOperate.queryAllSysLogOperate", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysLogOperateList != null)
			return (List<Map>)sysLogOperateList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysLogOperate.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	
	
	
	
//    public SysLogOperateDAOImpl() {
//        super();
//    }
//
//    public Integer insert(SysLogOperate record) {
//        Object newKey = getSqlMapClientTemplate().insert("SysLogOperate.insert", record);
//        return (Integer) newKey;
//    }
//
//    public int updateByPrimaryKey(SysLogOperate record) {
//        int rows = getSqlMapClientTemplate().update("SysLogOperate.updateByPrimaryKey", record);
//        return rows;
//    }
//
//    public int updateByPrimaryKeySelective(SysLogOperate record) {
//        int rows = getSqlMapClientTemplate().update("SysLogOperate.updateByPrimaryKeySelective", record);
//        return rows;
//    }
//
//    public List selectByExample(SysLogOperateExample example) {
//        List list = getSqlMapClientTemplate().queryForList("SysLogOperate.selectByExample", example);
//        return list;
//    }
//
//    public Page selectPageByExample(SysLogOperateExample example) {
//        List list = getSqlMapClientTemplate().queryForList("SysLogOperate.selectPageByExample", example);
//        Integer total = (Integer)getSqlMapClientTemplate().queryForObject("SysLogOperate.selectCountByExample", example);
//        return null;
////        return new PageImp(list,total,example.getPageNumber(),example.getPageSize());
//    }
//
//    public Integer selectCountByExample(SysLogOperateExample example) {
//        Integer total = (Integer)getSqlMapClientTemplate().queryForObject("SysLogOperate.selectCountByExample", example);
//        return total;
//    }
//
//    public SysLogOperate selectByPrimaryKey(Integer sysLogOperateId) {
//        SysLogOperate key = new SysLogOperate();
//        key.setSysLogOperateId(sysLogOperateId);
//        SysLogOperate record = (SysLogOperate) getSqlMapClientTemplate().queryForObject("SysLogOperate.selectByPrimaryKey", key);
//        return record;
//    }
//
//    public int deleteByExample(SysLogOperateExample example) {
//        int rows = getSqlMapClientTemplate().delete("SysLogOperate.deleteByExample", example);
//        return rows;
//    }
//
//    public int deleteByPrimaryKey(SysLogOperate key) {
//        if( key.getSysLogOperateId()==null || key.getSysLogOperateId().equals("")) throw new RuntimeException("�����ɾ��ʱ�����ֵ����Ϊ�գ�");
//        int rows = getSqlMapClientTemplate().delete("SysLogOperate.deleteByPrimaryKey", key);
//        return rows;
//    }
}