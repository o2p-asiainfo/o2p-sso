package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;
/**
 * 员工权限serv
 * @author zhaobl
 *
 */
public interface ISysPersonRightService {
	/**
	 * 查询出符合条件的所有员工权限信息
	 * @return
	*/
	public List<Map> queryAllSysPersonRight(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 删除一条员工权限
	 * @param sysRightId
	 */
	public void deleteSysPersonRight(String sysRightId);
	/**
	 * 根据授权员工Id和被授权员工Id查询出此员工未赋权的角色
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedRole(Map map);
	/**
	 * 根据授权员工Id和被授权员工Id查询出此员工未赋权的功能点
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedFunction(Map map);
	/**
	 * 查询序列
	 * @return
	 */
	public String querySeq();
	/**
	 * 新增员工权限（为员工赋权新角色/功能点）
	 * @param list
	 */
	public void insertSysPersonRight(List<Map> list);
	/**
	 * 根据员工权限ID查询员工权限表
	 * @return
	 */
	public Map querySysPersonRightById(String sysRightId);
	/**
	 * 修改（角色类型或状态）
	 * @return
	 */
	public void updateSysPersonRight(Map map);
}
