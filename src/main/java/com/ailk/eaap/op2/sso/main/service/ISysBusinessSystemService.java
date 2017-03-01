package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
/**
 * 业务系统serv
 * @author zhaobl
 *
 */
public interface ISysBusinessSystemService {
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
