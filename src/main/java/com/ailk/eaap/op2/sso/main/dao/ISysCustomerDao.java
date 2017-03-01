package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.SysCustomer;
/**
 * 
 * @author zhaobl
 *
 */

public interface ISysCustomerDao {
	/**
	 * 查询出所有客户信息（状态正常）
	 * @return
	 */
	public List<SysCustomer> queryAll();
	/**
	 * 查询出符合条件的所有客户信息
	 * @return
	 */
	public List<SysCustomer> queryAllSysCustomer(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 根据客户ID查询客户表
	 * @return
	 */
	public SysCustomer querySysCustomerById(String sysCustomerId);
	/**
	 * 判断新添加客户名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isCustomerExist(Map map);
	/**
	 * 插入新客户
	 * @param map
	 */
	public void insertSysCustomer(Map map);
	/**
	 * 删除(0:删除成功；1：删除失败，--有子表关联)
	 * @param sysCustomerId
	 */
	public int deleteSysCustomer(String sysCustomerId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysCustomer(Map map);
}
