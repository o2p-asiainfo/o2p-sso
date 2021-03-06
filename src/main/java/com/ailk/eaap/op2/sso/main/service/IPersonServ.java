package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
/**
 * 员工信息服务
 * @author zhaobl
 *
 */
public interface IPersonServ {
	/**
	 * 
	 * @Title: getUserInfo
	 * @Description: TODO(获取用户信息)
	 * @param username
	 * @param password
	 * @return SysPersonBO
	 */
//	public SysPersonBO getUserInfo(String username, String password);
	/**
	 * 得到员工信息（包括部门、区域信息）
	 * @param username
	 * @return
	 */
	public aSysPerson getSysPerson(Map map);
	/**
	 * 根据工号查询员工表
	 * @param cardNumber
	 * @return
	 */
	public aSysPerson querySysPerson(Map map);
	/**
	 * 密码是否过期
	 * @param sysPersonId
	 * @return
	 */
	public boolean isPasswordExpire(String sysPersonId);
	/**
	 * 根据区域编号查询区域表
	 * @param areaId
	 * @return
	 */
	public SysArea querySysArea(Long areaId);
	/**
	 * 根据员工ID查询部门表
	 * @param sysPersonId
	 * @return
	 */
	public SysDept querySysDeptByPersonId(Long sysPersonId);
	/**
	 * 根据员工ID修改密码
	 * @param sysPersonId
	 * @param newPassWord
	 */
	public void updatePassword(String sysPersonId, String newPassWord);
	
	
	
	/**
	 * 查询出符合条件的所有员工信息
	 * @return
	 */
	public List<Map> queryAllSysPerson(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 查询出所有员工信息
	 * @return
	 */
	public List<Map> queryAll();
	/**
	 * 根据部门ID查询出所有员工信息
	 * @return
	 */
	public List<Map> queryAllByDeptId(Map map);
	/**
	 * 根据员工ID查询员工表
	 * @return
	 */
	public Map querySysPersonById(String sysPersonId);
	/**
	 * 判断新添加工号名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isPersonExist(Map map);
	/**
	 * 判断新修改工号名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isUpdatePersonExist(Map map);
	/**
	 * 插入新员工
	 * @param map
	 */
	public void insertSysPerson(Map map);
	/**
	 * 删除(0:删除成功；1：删除失败，--有子表关联)
	 * @param sysPersonId
	 */
	public int deleteSysPerson(String sysPersonId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysPerson(Map map);
	/**
	 * 得到租户对象
	 * @param tenant
	 * @return
	 */
	public Tenant getTenant(Tenant tenant);
}
