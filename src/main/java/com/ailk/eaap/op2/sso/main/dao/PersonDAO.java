package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.sso.framework.util.Constants;
import com.ailk.eaap.op2.sso.framework.util.MD5;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.linkage.rainbow.dao.SqlMapDAO;
/**
 * 员工信息DAO
 * @author zhaobl
 *
 */
public class PersonDAO implements IPersonDAO{
	private SqlMapDAO sqlMapDao;
	private SqlMapDAO sqlMapDaoConf;
	private Logger log = Logger.getLog(this.getClass());
	
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
	public SqlMapDAO getSqlMapDaoConf() {
		return sqlMapDaoConf;
	}
	public void setSqlMapDaoConf(SqlMapDAO sqlMapDaoConf) {
		this.sqlMapDaoConf = sqlMapDaoConf;
	}

	public aSysPerson qeuryPerson(Map map) {
		Object sysPerson = new Object();
		try {
			sysPerson =  sqlMapDao.queryForObject("sysPerson.querySysPerson", map);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(sysPerson == null)
			return null;
		return (aSysPerson) sysPerson;
	}
	
	public SysArea querySysArea(Long areaId) {
		Object sysArea = new Object();
		try {
			sysArea =  sqlMapDao.queryForObject("sysArea.querySysArea", areaId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(sysArea == null)
			return null;
		return (SysArea) sysArea;
	}

	public SysDept querySysDeptByPersonId(Long sysPersonId) {
		Object sysDept = new Object();
		try {
			sysDept =  sqlMapDao.queryForObject("sysDept.querySysDeptByPersonId", sysPersonId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(sysDept == null)
			return null;
		return (SysDept) sysDept;
	}

	public void updatePassword(String sysPersonId, String newPassWord) {
		Map map = new HashMap();
		map.put("sysPersonId", sysPersonId);
		map.put("newPassWord", newPassWord);
		map.put("passwordTimeout", Constants.passwordTimeout);
		try{
			sqlMapDao.update("sysPerson.updatePassword", map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean isPasswordExpire(String sysPersonId) {
		int count = (Integer)sqlMapDao.queryForObject("sysPerson.isPasswordExpire", sysPersonId);
		if(count > 0)
			return false;
		return true;
	}

	public void deleteSysPerson(String sysPersonId) throws Exception {
		try{
			sqlMapDao.delete("sysPerson.deleteSysDeptPersonByPersonId", sysPersonId);
			sqlMapDao.delete("sysPerson.deleteSysPersonById", sysPersonId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    		throw new Exception(e);
		}
	}

	public void insertSysPerson(Map map) {
		try{
			//String defaultPasswordMD5 = MD5.encode(Constants.defaultPassword).toUpperCase();
			String defaultPasswordMD5 = Constants.defaultPassword;	//明文
			map.put("password", defaultPasswordMD5);
			map.put("passwordTimeout", Constants.passwordTimeout);
			String sysPersonId = (String)sqlMapDao.insert("sysPerson.insertSysPerson", map);
			//部门员工表新增一条
			Map map1 = new HashMap();
			map1.put("sysPersonId", sysPersonId);
			map1.put("deptId", map.get("deptId"));
			sqlMapDao.insert("sysPerson.insertSysDeptPerson", map1);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<Map> queryAllSysPerson(Map map) {
		List sysPersonList = new ArrayList();
		try{
			sysPersonList = sqlMapDao.queryForList("sysPerson.queryAllSysPerson", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPersonList != null)
			return (List<Map>)sysPersonList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysPerson.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public Map querySysPersonById(String sysPersonId) {
		Object sysPerson = new Object();
		try{
			sysPerson = sqlMapDao.queryForObject("sysPerson.querySysPersonById", sysPersonId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPerson != null)
			return (HashMap)sysPerson;
		return null;
	}

	public void updateSysPerson(Map map) {
		try{
			sqlMapDao.update("sysPerson.updateSysPersonById", map);
			//部门员工表修改部门信息
			Map map1 = new HashMap();
			map1.put("sysPersonId", map.get("sysPersonId"));
			map1.put("deptId", map.get("deptId"));
			sqlMapDao.update("sysPerson.updateSysDeptPersonById", map1);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public boolean isPersonExist(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysPerson.isPersonExist", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(count > 0)
			return true;
		return false;
	}

	public List<Map> queryAll() {
		List sysPersonList = new ArrayList();
		try{
			sysPersonList = sqlMapDao.queryForList("sysPerson.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPersonList != null)
			return (List<Map>)sysPersonList;
		return null;
	}

	public List<Map> queryAllByDeptId(Map map) {
		List sysPersonList = new ArrayList();
		try{
			sysPersonList = sqlMapDao.queryForList("sysPerson.queryAllByDeptId", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysPersonList != null)
			return (List<Map>)sysPersonList;
		return null;
	}
	

	public Tenant getTenant(Tenant tenantMap) {
		Object tenant = new Object();
		try {
			tenant =  sqlMapDaoConf.queryForObject("tenant.getTenant", tenantMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(tenant == null)
			return null;
		return (Tenant) tenant;
	}

}
