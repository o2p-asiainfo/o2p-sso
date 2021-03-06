package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysRole;
/**
 * 角色serv
 * @author zhaobl
 *
 */
public interface ISysRoleService {
	/**
	 * 查询出全部角色信息（状态正常）
	 * @return
	 */
	public List<SysRole> queryAll();
	/**
	 * 查询出符合条件的所有角色信息
	 * @return
	 */
	public List<SysRole> queryAllSysRole(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 判断新添加角色名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isRoleExist(Map map);
	/**
	 * 判断新修改角色名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isUpdateRoleExist(Map map);
	/**
	 * 根据角色编号查询出角色
	 * @param sysRoleId
	 * @return
	 */
	public SysRole querySysRoleById(String sysRoleId);
	/**
	 * 插入新角色
	 * @param map
	 */
	public void insertSysRole(Map map);
	/**
	 * 删除
	 * @param sysRoleId
	 */
	public int deleteSysRole(String sysRoleId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysRole(Map map);
}
