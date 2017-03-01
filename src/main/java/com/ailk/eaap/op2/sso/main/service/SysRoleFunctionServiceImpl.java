package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.main.dao.ISysRoleFunctionDao;

/**
 * 角色权限服务
 * @author zhaobl
 *
 */

public class SysRoleFunctionServiceImpl implements ISysRoleFunctionService {

	private ISysRoleFunctionDao sysRoleFunctionDao;
	public ISysRoleFunctionDao getSysRoleFunctionDao() {
		return sysRoleFunctionDao;
	}

	public void setSysRoleFunctionDao(ISysRoleFunctionDao sysRoleFunctionDao) {
		this.sysRoleFunctionDao = sysRoleFunctionDao;
	}

	
	public void deleteSysRoleFunction(String sysRoleFuncId) {
		sysRoleFunctionDao.deleteSysRoleFunction(sysRoleFuncId);
	}

	public List<Map> queryAllSysRoleFunction(Map map) {
		List<Map> sysRoleFunctionList = sysRoleFunctionDao.queryAllSysRoleFunction(map);
		return sysRoleFunctionList;
	}

	public int queryCount(Map map) {
		int count = sysRoleFunctionDao.queryCount(map);
		return count;
	}

	public List<Map> queryNotSelectedFunction(Map map) {
		List<Map> sysFunctionList = sysRoleFunctionDao.queryNotSelectedFunction(map);
		return sysFunctionList;
	}

	public void insertSysRoleFunction(List<Map> list) {
		sysRoleFunctionDao.insertSysRoleFunction(list);
	}

	public String querySeq() {
		String seq = sysRoleFunctionDao.querySeq();
		return seq;
	}
	
	
}
