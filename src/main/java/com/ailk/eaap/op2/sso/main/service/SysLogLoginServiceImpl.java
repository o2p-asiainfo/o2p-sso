package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.main.dao.ISysLogLoginDao;
import com.ailk.eaap.op2.sso.main.model.SysLogLogin;

public class SysLogLoginServiceImpl implements ISysLogLoginService {
	private ISysLogLoginDao sysLogLoginDao;
	
	public ISysLogLoginDao getSysLogLoginDao() {
		return sysLogLoginDao;
	}
	public void setSysLogLoginDao(ISysLogLoginDao sysLogLoginDao) {
		this.sysLogLoginDao = sysLogLoginDao;
	}


	public void insertSysLogLogin(SysLogLogin sysLogLogin) {
		sysLogLoginDao.insertSysLogLogin(sysLogLogin);
	}
	public List<Map> queryAllSysLogLogin(Map map) {
		List<Map> sysLogLoginList = sysLogLoginDao.queryAllSysLogLogin(map);
		return sysLogLoginList;
	}
	public int queryCount(Map map) {
		int count = sysLogLoginDao.queryCount(map);
		return count;
	}

}
