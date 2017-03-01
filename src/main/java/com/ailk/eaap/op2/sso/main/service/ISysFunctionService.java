package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysFunction;
/**
 * 
 * @author zhaobl
 *
 */
public interface ISysFunctionService {
	/**
	 * 查询出所有功能点信息
	 * @return
	 */
	public List<SysFunction> queryAll();
	/**
	 * 查询出符合条件的所有功能点信息
	 * @return
	 */
	public List<SysFunction> queryAllSysFunction(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 根据功能点ID查询
	 * @return
	 */
	public SysFunction querySysFunctionById(String functionId);
	/**
	 * 判断新添加功能点名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isFunctionExist(Map map);
	/**
	 * 判断新修改功能点名称是否存在
	 * @param map
	 * @return
	 */
	public boolean isUpdateFunctionExist(Map map);
	/**
	 * 插入新功能点
	 * @param map
	 */
	public void insertSysFunction(Map map);
	/**
	 * 删除
	 * @param deptId
	 */
	public int deleteSysFunction(String functionId);
	/**
	 * 修改
	 * @return
	 */
	public void updateSysFunction(Map map);
}
