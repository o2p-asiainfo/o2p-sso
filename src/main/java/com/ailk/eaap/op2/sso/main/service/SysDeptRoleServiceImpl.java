package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.main.dao.ISysDeptRoleDao;


public class SysDeptRoleServiceImpl implements ISysDeptRoleService {

	private ISysDeptRoleDao sysDeptRoleDao;
	public ISysDeptRoleDao getSysDeptRoleDao() {
		return sysDeptRoleDao;
	}

	public void setSysDeptRoleDao(ISysDeptRoleDao sysDeptRoleDao) {
		this.sysDeptRoleDao = sysDeptRoleDao;
	}

	public void deleteSysDeptRole(String deptRoleId) {
		sysDeptRoleDao.deleteSysDeptRole(deptRoleId);
	}

	public List<Map> queryAllSysDeptRole(Map map) {
		List<Map> sysDeptRoleList = sysDeptRoleDao.queryAllSysDeptRole(map);
		return sysDeptRoleList;
	}

	public int queryCount(Map map) {
		int count = sysDeptRoleDao.queryCount(map);
		return count;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List<Map> sysRoleList = sysDeptRoleDao.queryNotSelectedRole(map);
		return sysRoleList;
	}

	public void insertSysDeptRole(List<Map> list) {
		sysDeptRoleDao.insertSysDeptRole(list);
	}

	public String querySeq() {
		String seq = sysDeptRoleDao.querySeq();
		return seq;
	}


}
