package com.ailk.eaap.op2.sso.main.dao;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysDept;

public interface ISysDeptDao {
	/**
	 * 查出所有部门信息
	 * @return
	 */
	public List<SysDept> queryAll();
	/**
	 * 查询出符合条件的所有部门
	 * @return
	 */
	public List<SysDept> queryAllSysDept(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map);
	/**
	 * 根据部门ID查询部门表
	 * @return
	 */
	public SysDept querySysDeptByDeptId(String deptId);
	/**
	 * 判断新添加部门名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isDeptExist(Map map);
	/**
	 * 插入新部门
	 * @param map
	 */
	public void insertSysDept(Map map);
	/**
	 * 删除(0:删除成功；1：删除失败，--有子表关联)
	 * @param deptId
	 */
	public int deleteSysDept(String deptId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysDept(Map map);
}
