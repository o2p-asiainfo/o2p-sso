package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
/**
 * 操作日志Serv
 * @author zhaobl
 *
 */
public interface ISysLogOperateService {
	/**
	 * 插入操作日志
	 * @param sysLogOperate
	 */
	public void insertSysLogOperate(SysLogOperate sysLogOperate);
	/**
	 * 查询出符合条件的所有操作日志信息
	 * @return
	 */
	public List<Map> queryAllSysLogOperate(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map);
}
