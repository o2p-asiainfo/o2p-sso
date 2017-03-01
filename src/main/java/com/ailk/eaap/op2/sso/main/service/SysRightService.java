package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

public interface SysRightService {

	public List getCanAuthRoleList(Long auth_person_id) ;
	
	public List getCanAuthRoleList(Long auth_person_id,Long be_authed_person_id) ;
	
	public void insertSysRight(String sysPersonId , String SysRoleId ,String functionId,String SYS_ROLE_TYPE_ID) ;
	
	public List getFunctionIds(String [] functionIds) ;
	public void deleteSysRight(String sysPersonId) ;
	//1-18 修改
	public List getRightListBySyspersonId(String syspersonid ,String deptId) ;
	public List getRoleIdListBySyspersonId(String syspersonid ,String deptId) ;
	
	public void delByRoleidandFunctionid(Long role_id,Long function_id);
	
	public List getAuthedFunctionByUseridRoleType(String userid,String roleid,String usertypeid);
	
	/**
	 * 根据被授权人员的编号，权限类型查询已授权的角色
	 * @param authuserid
	 * @param beauthedUserid
	 * @return
	 */
	public List getAuthedRoleList(Long sys_person,Long roletype);
	
	/**
	 * 根据授权人的授权角色和被授权人员所在部门角色的交集获取被授权人员的可授权角色
	 * @param sys_person
	 * @param roletype
	 * @return
	 */
	public List selectCanAuthRoles(Long authuserid,Long beauthedUserid);
	
	/**
	 * 根据授权人员的可授权角色，以及功能点获取功能点信息。
	 * @param authUserid
	 * @param roleid
	 * @param roletype
	 * @return
	 */
	public List selectCanAuthFunctionList(Long authUserid,String roleid);
	
	/**
	 * 根据授权人员的编号，角色，角色类型查询已授权信息
	 * @param authedUserid
	 * @param roleid
	 * @param roleType
	 * @return
	 */
	public List selectBeauthedFunctionList(Long authedUserid,String roleid,Long roleType);
}
