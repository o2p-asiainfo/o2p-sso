package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SYSPERSON;

public interface IUserService {
	void doSearchFlow(SYSPERSON bean); 
	
	List<SYSPERSON> getDeptList() ;
	
	public void updatePassword(SYSPERSON bean);
	
	public void updatePasswordById(SYSPERSON bean);
	
	public void insertDeptUser(Long deptid,Long userid);
	
	public List<SYSPERSON> getCardList() ;
	
	public List<SYSPERSON> getIdTypeList() ;
	
	public List<SYSPERSON> getUnAuthedDeptList() ;
	
	int updatePerson(SYSPERSON sysperson ,String type) ;
	//1-12
	int updateDeptPerson(SYSPERSON sysperson ,String customerId ,String type) ;
	
	void LoginCheck(SYSPERSON bean) ;
	
	public int authCheck(Long sys_person_id);
	
	String doloadPassword(String sys_person_id);
	
	//3-17 增加 数据域判断 获取员工信息 接口
	void doSearchFlow(SYSPERSON bean,String idType,String cusName); 
	
	List<SYSPERSON> getDeptList(Long cusId) ;
	
	public List<SYSPERSON> getPassword(String sys_person_id);
}
