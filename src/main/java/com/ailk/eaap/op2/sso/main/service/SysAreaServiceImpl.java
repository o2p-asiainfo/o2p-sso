package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.dao.ISysAreaDao;
import com.ailk.eaap.op2.sso.main.model.SysArea;

public class SysAreaServiceImpl implements ISysAreaService {
	private ISysAreaDao sysAreaDao;

	public List<SysArea> queryAll() {
		List<SysArea> sysAreaList = sysAreaDao.queryAll();
		return sysAreaList;
	}

	public ISysAreaDao getSysAreaDao() {
		return sysAreaDao;
	}

	public void setSysAreaDao(ISysAreaDao sysAreaDao) {
		this.sysAreaDao = sysAreaDao;
	}

	
}
