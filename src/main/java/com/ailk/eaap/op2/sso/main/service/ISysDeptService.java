package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysDept;

public interface ISysDeptService {
	/**
	 * 查询所有部门信息
	 * @return
	 */
	public List<SysDept> queryAll();
	/**
	 * 查询出符合条件的所有部门信息
	 * @param map
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
	 * 根据deptId查询部门信息
	 * @param deptId
	 * @return
	 */
	public SysDept querySysDeptById(String deptId);
	/**
	 * 判断新添加部门名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isDeptExist(Map map);
	/**
	 * 判断新修改部门名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isUpdateDeptExist(Map map);
	/**
	 * 插入新部门
	 * @param map
	 */
	public void insertSysDept(Map map);
	/**
	 * 删除
	 * @param deptId
	 */
	public int deleteSysDept(String deptId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysDept(Map map);
}
