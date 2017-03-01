package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.service.IBaseService;
import com.ailk.eaap.op2.sso.main.model.SysFunction;
import com.ailk.eaap.op2.sso.main.model.TreeModel;

public interface FunctionService extends IBaseService
{
	public List getAllFunction(String sql);
	public List getBusinessSystem(String sql);
	public int getResultSize(String sql) ;
	public void doSearchFlow(SysFunction bean) ;
	public List<SysFunction> getSysBusinessList() ;
	public int updateFunction(SysFunction sysFunction ,String type) ;
	public boolean checkHaveParentId(String parentId) ;
	public int updateFunction(Long functionId ,String type) ;
	public SysFunction getFuntionByfuntionaname(String funtionname);
	//----------add by Guangshu.Mi------------
	public List<TreeModel> getMainNodeTreeModels(String personId,String lan);
	public List<TreeModel> getTreeModels(String parentId,String personId,String lan);
	//----------end by Guangshu.Mi------------
}
