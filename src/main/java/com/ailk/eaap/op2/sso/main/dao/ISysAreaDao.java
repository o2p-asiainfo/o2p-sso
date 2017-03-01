package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysArea;

public interface ISysAreaDao {
	/**
	 * 查询全部地区信息
	 * @return
	 */
	public List<SysArea> queryAll(); 
}
