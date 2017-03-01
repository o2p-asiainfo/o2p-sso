package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.main.dao.ISysPersonRightDao;
/**
 * 
 * @author zhaobl
 *
 */
public class SysPersonRightServiceImpl implements ISysPersonRightService {
	private ISysPersonRightDao sysPersonRightDao;
	public ISysPersonRightDao getSysPersonRightDao() {
		return sysPersonRightDao;
	}

	public void setSysPersonRightDao(ISysPersonRightDao sysPersonRightDao) {
		this.sysPersonRightDao = sysPersonRightDao;
	}

	public void deleteSysPersonRight(String sysRightId) {
		sysPersonRightDao.deleteSysPersonRight(sysRightId);
	}

	public List<Map> queryAllSysPersonRight(Map map) {
		List<Map> sysPersonRightList = sysPersonRightDao.queryAllSysPersonRight(map);
		return sysPersonRightList;
	}

	public int queryCount(Map map) {
		int count = sysPersonRightDao.queryCount(map);
		return count;
	}

	public List<Map> queryNotSelectedFunction(Map map) {
		List<Map> sysFunctionList = sysPersonRightDao.queryNotSelectedFunction(map);
		return sysFunctionList;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List<Map> sysRoleList = sysPersonRightDao.queryNotSelectedRole(map);
		return sysRoleList;
	}

	public void insertSysPersonRight(List<Map> list) {
		sysPersonRightDao.insertSysPersonRight(list);
	}

	public String querySeq() {
		String seq = sysPersonRightDao.querySeq();
		return seq;
	}

	public Map querySysPersonRightById(String sysRightId) {
		Map sysPersonRightMap = sysPersonRightDao.querySysPersonRightById(sysRightId);
		return sysPersonRightMap;
	}

	public void updateSysPersonRight(Map map) {
		sysPersonRightDao.updateSysPersonRight(map);
	}

}
