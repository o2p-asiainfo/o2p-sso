package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.dao.ISysLogOperateDao;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;

public class SysLogOperateServiceImpl implements ISysLogOperateService {

	private ISysLogOperateDao sysLogOperateDao;
	
	public ISysLogOperateDao getSysLogOperateDao() {
		return sysLogOperateDao;
	}

	public void setSysLogOperateDao(ISysLogOperateDao sysLogOperateDao) {
		this.sysLogOperateDao = sysLogOperateDao;
	}


	public void insertSysLogOperate(SysLogOperate sysLogOperate) {
		sysLogOperateDao.insertSysLogOperate(sysLogOperate);
	}

	public List<Map> queryAllSysLogOperate(Map map) {
		List<Map> list = sysLogOperateDao.queryAllSysLogOperate(map);
		return list;
	}

	public int queryCount(Map map) {
		int count = sysLogOperateDao.queryCount(map);
		return count;
	}

}
