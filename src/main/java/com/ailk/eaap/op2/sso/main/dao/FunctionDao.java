package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.model.TreeModel;

public interface FunctionDao 
{
	public List<Map<String, Object>> getAllFunction(String tf);
	public List<Map<String, Object>> getBusinessSystem(String sql);
	public int getResultSize(String sql) ;
	public List<Map<String, Object>> getPageResult(String sql) ;
	public int update(String sql) ;
	public boolean checkHaveParentId(String parentId) ;
	public List getFuntionByfuntionaname(String sql);
	//--------add by Guangshu.Mi-------
	public List<TreeModel> queryMainTreeModel(String personId,String lan);
	public List<TreeModel> queryTreeModel(String parentId,String personId,String lan);
	//-------end by Guangshu.Mi-----
}

