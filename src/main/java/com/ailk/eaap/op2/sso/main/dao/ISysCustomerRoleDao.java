package com.ailk.eaap.op2.sso.main.dao;

import java.util.Map;
import java.util.List;
/**
 * 客户角色Dao
 * @author zhaobl
 *
 */
public interface ISysCustomerRoleDao {
	/**
	 * 查询出符合条件的所有客户角色信息
	 * @return
	 */
	public List<Map> queryAllSysCustomerRole(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 删除一条客户角色
	 * @param sysCustomerRoleId
	 */
	public void deleteSysCustomerRole(String sysCustomerRoleId);
	/**
	 * 根据客户ID查询出此客户未赋权的角色
	 * @param map
	 * @return
	 */
	public List<Map> queryNotSelectedRole(Map map);
	/**
	 * 查询序列
	 * @return
	 */
	public String querySeq();
	/**
	 * 新增客户角色（为客户赋权新角色）
	 * @param list
	 */
	public void insertSysCustomerRole(List<Map> list);
}
