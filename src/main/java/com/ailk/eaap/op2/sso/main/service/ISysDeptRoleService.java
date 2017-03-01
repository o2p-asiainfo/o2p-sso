package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;
/**
 * 部门角色Serv
 * @author zhaobl
 *
 */
public interface ISysDeptRoleService {
	/**
	 * 查询出符合条件的所有部门角色信息
	 * @return
	*/
	public List<Map> queryAllSysDeptRole(Map map);
	/**
	 * 查询出符合条件的数据总量
	 * @param map
	 * @return
	 */
	public int queryCount(Map map); 
	/**
	 * 删除一条部门角色
	 * @param deptRoleId
	 */
	public void deleteSysDeptRole(String deptRoleId);
	/**
	 * 根据部门ID查询出此部门未赋权的角色
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
	 * 新增部门角色（为部门赋权新角色）
	 * @param list
	 */
	public void insertSysDeptRole(List<Map> list);
}
