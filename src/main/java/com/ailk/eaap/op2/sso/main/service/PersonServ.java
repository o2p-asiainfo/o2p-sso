package com.ailk.eaap.op2.sso.main.service;

import java.util.Map;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.sso.main.dao.IPersonDAO;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.linkage.rainbow.dao.SqlMapDAO;

public class PersonServ implements IPersonServ{
	private IPersonDAO personDAO;
	
	public aSysPerson getSysPerson(Map map) {
		aSysPerson sysPerson = querySysPerson(map);
		if(sysPerson != null) {
			SysDept sysDept = querySysDeptByPersonId(sysPerson.getSysPersonId());
			sysPerson.setSysDept(sysDept);
			if(sysPerson.getSysAreaId() != null) {
				SysArea sysArea = querySysArea(sysPerson.getSysAreaId());
				sysPerson.setSysArea(sysArea);
			}
		}
		return sysPerson;
	}
	
	public aSysPerson querySysPerson(Map map){
		aSysPerson sysPerson = personDAO.qeuryPerson(map);
		return sysPerson;
	}
	
	public boolean isPasswordExpire(String sysPersonId) {
		boolean flag = personDAO.isPasswordExpire(sysPersonId);
		return flag;
	}
	
	public SysArea querySysArea(Long areaId){
		SysArea sysArea = personDAO.querySysArea(areaId);
		return sysArea;
	}
	
	public SysDept querySysDeptByPersonId(Long sysPersonId){
		SysDept sysDept = personDAO.querySysDeptByPersonId(sysPersonId);
		return sysDept;
	}
	
	public void updatePassword(String sysPersonId, String newPassWord) {
		personDAO.updatePassword(sysPersonId, newPassWord);
	}
	
	public int deleteSysPerson(String sysPersonId) {
	    try {
			personDAO.deleteSysPerson(sysPersonId);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	public void insertSysPerson(Map map) {
		personDAO.insertSysPerson(map);
	}

	public List<Map> queryAllSysPerson(Map map) {
		List<Map> sysPersonList = personDAO.queryAllSysPerson(map);
		return sysPersonList;
	}

	public int queryCount(Map map) {
		int count = personDAO.queryCount(map);
		return count;
	}

	public Map querySysPersonById(String sysPersonId) {
		Map map = personDAO.querySysPersonById(sysPersonId);
		return map;
	}

	public void updateSysPerson(Map map) {
		personDAO.updateSysPerson(map);
	}

	public boolean isPersonExist(Map map) {
		boolean flag = personDAO.isPersonExist(map);
		return flag;
	}

	public boolean isUpdatePersonExist(Map map) {
		String sysPersonId =  map.get("sysPersonId").toString();
		String cardNumber = map.get("cardNumber").toString();
		Map sysPersonMap = personDAO.querySysPersonById(sysPersonId);
		if(cardNumber.equals(sysPersonMap.get("CARD_NUMBER").toString())){
			return false;
		}else{
			return this.isPersonExist(map);
		}
	}
	
	public List<Map> queryAll() {
		List<Map> sysPersonList = personDAO.queryAll();
		return sysPersonList;
	}

	public List<Map> queryAllByDeptId(Map map) {
		List<Map> sysPersonList = personDAO.queryAllByDeptId(map);
		return sysPersonList;
	}
	

	public Tenant getTenant(Tenant tenantMap){
		Tenant tenant = personDAO.getTenant(tenantMap);
		return tenant;
	}
	
	
	public IPersonDAO getPersonDAO() {
		return personDAO;
	}
	public void setPersonDAO(IPersonDAO personDAO) {
		this.personDAO = personDAO;
	}


}
