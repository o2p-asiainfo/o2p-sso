package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.dao.SysRightDao;

public class SysRightServiceImpl implements SysRightService 
{
	private SysRightDao sysRightDao ;
	
	public List getCanAuthRoleList(Long auth_person_id) 
	{
		return sysRightDao.getCanAuthRoleList(auth_person_id);
	}
    
	public List getCanAuthRoleList(Long auth_person_id,Long be_authed_person_id) 
	{
		return sysRightDao.getCanAuthRoleList(auth_person_id, be_authed_person_id);
	}
	
	public void insertSysRight(String sysPersonId , String SysRoleId ,String functionId,String SYS_ROLE_TYPE_ID){
		sysRightDao.insertSysRight(sysPersonId, SysRoleId, functionId,SYS_ROLE_TYPE_ID) ;
	}
	public List getAuthedRoleList(Long sys_person,Long roletype)
	{
		return sysRightDao.getAuthedRoleList(sys_person, roletype);
	}
	public void deleteSysRight(String sysPersonId){
		sysRightDao.deleteSysRight(sysPersonId) ;
	}
	public List getFunctionIds(String [] functionIds)
	{
		return sysRightDao.getFunctionIds(functionIds);
	}
	//1-18修改
	public List getRightListBySyspersonId(String syspersonid ,String deptId){
		return sysRightDao.getRightListBySyspersonId(syspersonid ,deptId) ;
	}
	public List getRoleIdListBySyspersonId(String syspersonid ,String deptId) {
		return sysRightDao.getRoleIdListBySyspersonId(syspersonid ,deptId) ;
	}
	
	public List getAuthedFunctionByUseridRoleType(String userid,String roleid,String usertypeid)
	{
		return sysRightDao.getAuthedFunctionByUseridRoleType(userid, roleid, usertypeid);
	}
	public SysRightDao getSysRightDao() {
		return sysRightDao;
	}

	public void setSysRightDao(SysRightDao sysRightDao) {
		this.sysRightDao = sysRightDao;
	}

	public void delByRoleidandFunctionid(Long role_id, Long function_id) {
		sysRightDao.delByRoleidandFunctionid(role_id, function_id);
	}

	public List selectBeauthedFunctionList(Long authedUserid, String roleid,
			Long roleType) {
		return sysRightDao.selectBeauthedFunctionList(authedUserid, roleid, roleType);
	}

	public List selectCanAuthFunctionList(Long authUserid, String roleid) {
		return sysRightDao.selectCanAuthFunctionList(authUserid, roleid);
	}

	public List selectCanAuthRoles(Long authuserid, Long beauthedUserid) {
		return sysRightDao.selectCanAuthRoles(authuserid, beauthedUserid);
	}

	
}
