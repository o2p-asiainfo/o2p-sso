package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
/**
 * 业务系统DAO  SYS_BUSINESS_SYSTEM
 * @author zhaobl
 *
 */
public interface ISysBusinessSystemDao {
	/**
	 * 查询所有业务系统
	 * @return
	 */
	public List<SysBusinessSystem> querySysBusinessSystem();
	/**
	 * 根据businessSystemId 查询系统表
	 * @return
	 */
	public SysBusinessSystem querySysBusinessSystemById(String businessSystemId);
}
