package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.main.dao.ISysCustomerRoleDao;
/**
 * 客户角色服务
 * @author zhaobl
 *
 */
public class SysCustomerRoleServiceImpl implements ISysCustomerRoleService {

	private ISysCustomerRoleDao sysCustomerRoleDao;
	public ISysCustomerRoleDao getSysCustomerRoleDao() {
		return sysCustomerRoleDao;
	}

	public void setSysCustomerRoleDao(ISysCustomerRoleDao sysCustomerRoleDao) {
		this.sysCustomerRoleDao = sysCustomerRoleDao;
	}

	public void deleteSysCustomerRole(String sysCustomerRoleId) {
		sysCustomerRoleDao.deleteSysCustomerRole(sysCustomerRoleId);
	}

	public List<Map> queryAllSysCustomerRole(Map map) {
		List<Map> sysCustomerRoleList = sysCustomerRoleDao.queryAllSysCustomerRole(map);
		return sysCustomerRoleList;
	}

	public int queryCount(Map map) {
		int count = sysCustomerRoleDao.queryCount(map);
		return count;
	}

	public List<Map> queryNotSelectedRole(Map map) {
		List<Map> sysRoleList = sysCustomerRoleDao.queryNotSelectedRole(map);
		return sysRoleList;
	}

	public void insertSysCustomerRole(List<Map> list) {
		sysCustomerRoleDao.insertSysCustomerRole(list);
	}

	public String querySeq() {
		String seq = sysCustomerRoleDao.querySeq();
		return seq;
	}
	
	
	
}
