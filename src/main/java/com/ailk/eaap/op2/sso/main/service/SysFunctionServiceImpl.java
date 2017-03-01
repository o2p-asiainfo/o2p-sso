package com.ailk.eaap.op2.sso.main.service;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.main.dao.ISysFunctionDao;
import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysFunction;
/**
 * 功能点管理服务
 * @author zhaobl
 *
 */
public class SysFunctionServiceImpl implements ISysFunctionService {
	private ISysFunctionDao sysFunctionDao;
	private ISysBusinessSystemService sysBusinessSystemServ; 
	
	public int deleteSysFunction(String functionId) {
		int flag = sysFunctionDao.deleteSysFunction(functionId);
		return flag;
	}

	public void insertSysFunction(Map map) {
		sysFunctionDao.insertSysFunction(map);
	}

	public List<SysFunction> queryAll() {
		List<SysFunction> sysFunctionList = sysFunctionDao.queryAll();
		return sysFunctionList;
	}

	public List<SysFunction> queryAllSysFunction(Map map) {
		List<SysFunction> sysFunctionList = sysFunctionDao.queryAllSysFunction(map);
		List<SysFunction> sysFunctionListNew = new ArrayList<SysFunction>();
		for(int i=0; i<sysFunctionList.size(); i++){
			SysFunction sysFunction = sysFunctionList.get(i);
			//设置父功能点名称
			String parentFunctionId = sysFunction.getParentFunctionId();
			if(parentFunctionId != null && !parentFunctionId.equals("")){
				SysFunction parentSysFunction = sysFunctionDao.querySysFunctionById(parentFunctionId);
				String parentFunctionName = parentSysFunction.getFunctionName();
				if(parentFunctionName != null)
					sysFunction.setParentFunctionName(parentFunctionName);
			}
			//设置所属业务系统名称
			String businessSystemId = sysFunction.getBusinessSystemId();
			if(businessSystemId != null && !businessSystemId.equals("")){
				SysBusinessSystem sysBusinessSystem = sysBusinessSystemServ.querySysBusinessSystemById(businessSystemId);
				String businessSystemName = sysBusinessSystem.getBusinessSystemName();
				if(businessSystemName != null)
					sysFunction.setBusinessSystemName(businessSystemName);
			}
			sysFunctionListNew.add(sysFunction);
		}
		return sysFunctionListNew;
	}

	public int queryCount(Map map) {
		int count = sysFunctionDao.queryCount(map);
		return count;
	}

	public SysFunction querySysFunctionById(String functionId) {
		SysFunction sysFunction = sysFunctionDao.querySysFunctionById(functionId);
		return sysFunction;
	}

	public ISysBusinessSystemService getSysBusinessSystemServ() {
		return sysBusinessSystemServ;
	}

	public void setSysBusinessSystemServ(
			ISysBusinessSystemService sysBusinessSystemServ) {
		this.sysBusinessSystemServ = sysBusinessSystemServ;
	}

	public void updateSysFunction(Map map) {
		sysFunctionDao.updateSysFunction(map);
	}
	
	public boolean isFunctionExist(Map map) {
		boolean flag = sysFunctionDao.isFunctionExist(map);
		return flag;
	}

	public boolean isUpdateFunctionExist(Map map) {
		String functionId =  map.get("functionId").toString();
		String functionName = map.get("functionName").toString();
		SysFunction sysFunction = sysFunctionDao.querySysFunctionById(functionId);
		if(functionName.equals(sysFunction.getFunctionName())){
			return false;
		}else{
			return this.isFunctionExist(map);
		}
	}
	
	public ISysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}

	public void setSysFunctionDao(ISysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}

}
