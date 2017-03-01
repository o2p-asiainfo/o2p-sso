package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.dao.ISysBusinessSystemDao;
import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;

public class SysBusinessSystemServiceImpl implements ISysBusinessSystemService {

	private ISysBusinessSystemDao sysBusinessSystemDao;

	public List<SysBusinessSystem> querySysBusinessSystem() {
		List<SysBusinessSystem> list = sysBusinessSystemDao.querySysBusinessSystem();
		return list;
	}
	
	public SysBusinessSystem querySysBusinessSystemById(String businessSystemId) {
		SysBusinessSystem sysBusinessSystem = sysBusinessSystemDao.querySysBusinessSystemById(businessSystemId);
		return sysBusinessSystem;
	}
	
	public ISysBusinessSystemDao getSysBusinessSystemDao() {
		return sysBusinessSystemDao;
	}

	public void setSysBusinessSystemDao(ISysBusinessSystemDao sysBusinessSystemDao) {
		this.sysBusinessSystemDao = sysBusinessSystemDao;
	}

}
