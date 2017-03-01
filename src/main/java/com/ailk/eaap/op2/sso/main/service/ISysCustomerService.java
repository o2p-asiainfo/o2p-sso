package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysCustomer;
/**
 * 
 * @author zhaobl
 *
 */
public interface ISysCustomerService {
	/**
	 * 查询出所有客户信息
	 * @return
	 */
	public List<SysCustomer> queryAll();
	/**
	 * 查询出符合条件的所有客户信息
	 * @return
	 */
	public List<SysCustomer> queryAllSysCustomer(Map map);
	/**
	 * 根据客户ID查询客户表
	 * @return
	 */
	public SysCustomer querySysCustomerById(String sysCustomerId);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	
	public int queryCount(Map map); 
	/**
	 * 判断新添加客户名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isCustomerExist(Map map);
	/**
	 * 判断新修改客户名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isUpdateCustomerExist(Map map);
	/**
	 * 插入新客户
	 * @param map
	 */
	public void insertSysCustomer(Map map);
	/**
	 * 删除
	 * @param deptId
	 */
	public int deleteSysCustomer(String sysCustomerId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysCustomer(Map map);
}
