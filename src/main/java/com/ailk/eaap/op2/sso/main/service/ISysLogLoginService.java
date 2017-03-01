package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysLogLogin;
/**
 * 登录日志Serv
 * @author zhaobl
 *
 */
public interface ISysLogLoginService {
	/**
	 * 插入登录日志
	 * @param sysLogLogin
	 */
	public void insertSysLogLogin(SysLogLogin sysLogLogin);
	/**
	 * 查询出符合条件的所有登录日志信息
	 * @return
	 */
	public List<Map> queryAllSysLogLogin(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
}
