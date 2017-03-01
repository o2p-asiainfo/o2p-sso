package com.ailk.eaap.op2.sso.main.dao;

import java.util.Map;
import java.util.List;

/**
 * 角色权限Dao
 * @author zhaobl
 *
 */
public interface ISysRoleFunctionDao {
	/**
	 * 查询出符合条件的所有角色权限信息
	 * @return
	 */
	public List<Map> queryAllSysRoleFunction(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 删除一条角色权限
	 * @param sysRoleFuncId
	 */
	public void deleteSysRoleFunction(String sysRoleFuncId);
	/**
	 * 根据角色ID查询出此角色未赋权的功能点
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
	 * 新增角色权限（为角色赋权新功能点）
	 * @param list
	 */
	public void insertSysRoleFunction(List<Map> list);
}
