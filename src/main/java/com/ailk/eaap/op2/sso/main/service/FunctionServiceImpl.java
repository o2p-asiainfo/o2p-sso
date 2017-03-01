//package com.ailk.eaap.op2.sso.main.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.ailk.eaap.op2.sso.framework.service.BaseServiceImpl;
//import com.ailk.eaap.op2.sso.main.dao.FunctionDao;
//import com.ailk.eaap.op2.sso.main.model.SysFunction;
//import com.ailk.eaap.op2.sso.main.model.TreeModel;
//import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
//import com.ailk.eaap.op2.sso.main.util.SqlUtil;
//
//public class FunctionServiceImpl extends BaseServiceImpl implements FunctionService {
//
//	private FunctionDao functionDao;
//	
//	public void doSearchFlow(SysFunction bean) {	
//		List<SysFunction> list = new ArrayList<SysFunction>();
//		String sql = "   select fun.* ,sys.business_system_name"
//			        +"     from sys_function fun, sys_business_system sys" 
//			        +"    where fun.sys_status_id = '1'"		
//					+"      and fun.business_system_id = sys.business_system_id" ;
//		if(!ConvertUtil.isEmpty(bean.getFunctionId())){
//			sql +=" and fun.function_id = '"+bean.getFunctionId()+"'" ;
//		}
//		if(!ConvertUtil.isEmpty(bean.getFunctionName())){
//			sql +=" and fun.function_name like '%"+bean.getFunctionName()+"%'" ;
//		}
//		 Integer size = functionDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql+" order by fun.PARENT_FUNCTION_ID", bean.getPageBean());
//			List<Map<String,Object>> pageResult = functionDao.getPageResult(pageSql);
//			this.convertResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}
//	
//	
//	private void convertResult(List<Map<String,Object>> result,List<SysFunction> list){
//		for(Map<String,Object> item : result){
//			SysFunction bean = new SysFunction();
//			bean.setFunctionId(ConvertUtil.nullToSpace(item.get("function_id"))) ;
//			bean.setFunctionName(ConvertUtil.nullToSpace(item.get("function_name"))) ;
//			bean.setBusinessSystemId(Long.valueOf(item.get("business_system_id").toString())) ;
//			bean.setSysStatuId(ConvertUtil.nullToSpace(item.get("sys_status_id"))) ;
//			bean.setUrl(ConvertUtil.nullToSpace(item.get("url"))) ;
//			bean.setShownum(ConvertUtil.nullToSpace(item.get("shownum"))) ;
//			bean.setParentFunctionId(ConvertUtil.nullToSpace(item.get("parent_function_id"))) ;
//			bean.setBusinessSystemName(ConvertUtil.nullToSpace(item.get("business_system_name"))) ;
//			list.add(bean);
//		}
//	}
//	
//	
//	public List<SysFunction> getSysBusinessList(){
//		 List<Map<String,Object>> Result = new ArrayList<Map<String,Object>>()  ;
//	     List<SysFunction> list = new ArrayList<SysFunction>();
//		 String sql = "select  business_system_id , business_system_name from sys_business_system " ;
//		 Result = functionDao.getPageResult(sql);
//		 this.convertResult(Result, list);
//		 return list ;
//	 }
//	
//	public SysFunction getFuntionByfuntionaname(String funtionname)
//	{
//		List<Map<String,Object>> Result = new ArrayList<Map<String,Object>>()  ;
//	     List<SysFunction> list = new ArrayList<SysFunction>();
//		 String sql = "select * from sys_function where function_name = '"+funtionname+"'" ;
//		 Result = functionDao.getPageResult(sql);
//		 SysFunction bean = new SysFunction();
//		 bean.setFunctionId(Result.get(0).get("function_id").toString());
//		 return bean ;
//	}
//	
//	public int updateFunction(SysFunction sysFunction ,String type){
//		String sql = "" ;
//		if("delete".equals(type))
//		{
//			sql = "delete from sys_function where function_id="+sysFunction.getFunctionId() ;
//		}
//		else if("update".equals(type)&&!ConvertUtil.isEmpty(sysFunction.getFunctionId()))
//		{
//			sql = "update sys_function" 
//				+"   set function_id = "+sysFunction.getFunctionId() ;
//			if(!ConvertUtil.isEmpty(sysFunction.getFunctionName())){
//				sql += " ,  function_name ='"+sysFunction.getFunctionName()+"'" ;
//			}
//			if(!ConvertUtil.isEmpty(String.valueOf(sysFunction.getBusinessSystemId())))
//			{
//				sql += " , business_system_id ="+sysFunction.getBusinessSystemId()+"" ;
//			}
//			if(!ConvertUtil.isEmpty(sysFunction.getParentFunctionId()))
//			{
//				sql += " , parent_function_id = '"+sysFunction.getParentFunctionId()+"'" ;
//			}
//			if(!ConvertUtil.isEmpty(sysFunction.getUrl()))
//			{
//				sql += " , url ='"+sysFunction.getUrl()+"'" ;
//			}
//			if(!ConvertUtil.isEmpty(sysFunction.getSysStatuId()))
//			{
//				sql += " , sys_status_id ='"+sysFunction.getSysStatuId()+"'" ;
//			}
//			 sql += " where function_id ="+sysFunction.getFunctionId() ;
//			
//		 }
//		else if("add".equals(type))
//		{
//			   sql = "insert into sys_function"
//				 +"  (function_id,"
//				 +"   function_name,"
//				 +"   business_system_id,"
//				 +"   parent_function_id,"
//				 +"   url,"
//				 +"   sys_status_id,"
//				 +"   shownum)"
//				 +" values"
//				 +"  (seq_sys_function.nextval, '"
//				 +sysFunction.getFunctionName()+"',"
//				 +sysFunction.getBusinessSystemId()+", '"
//				 +sysFunction.getParentFunctionId()+"', '"
//				 +sysFunction.getUrl()+"', '"
//				 +sysFunction.getSysStatuId()+"', '')" ;
//		 }
//		 
//		try {
//			return functionDao.update(sql) ;
//		} catch (Exception e) {
//			return -1 ;
//		}
//	}
//	
//	//批量删除
//	public int updateFunction(Long functionId ,String type){
//		String sql = "" ;
//		if("delete".equals(type)){
//			sql = "delete from sys_function where function_id="+functionId ;
//		}
//		try {
//			return functionDao.update(sql) ;
//		} catch (Exception e) {
//			return -1 ;
//		}
//	}
//	//----------add by Guangshu.Mi------------
//	public List<TreeModel> getMainNodeTreeModels(String personId,String lan){
//		return functionDao.queryMainTreeModel(personId,lan);
//	}
//	public List<TreeModel> getTreeModels(String parentId,String personId,String lan){
//		List<TreeModel> treeModels = functionDao.queryTreeModel(parentId,personId,lan);
//		for(int i=0;i<treeModels.size();i++){
////			List<TreeModel> childTreeModels = functionDao.queryTreeModel(treeModels.get(i).getId(),personId);
//			List<TreeModel> childTreeModels = this.getTreeModels(treeModels.get(i).getId(), personId,lan);
//			if(childTreeModels.size()>0){
//				treeModels.get(i).setChildren(childTreeModels);
//				treeModels.get(i).setLeaf(false);
//			}
//		}
//		return treeModels;
//	}
//	//----------end by Guangshu.Mi------------
//	
//	public boolean checkHaveParentId(String parentId){
//		return functionDao.checkHaveParentId(parentId) ;
//	}
//	
//	public List getAllFunction(String sql)
//	{ 
//		  return functionDao.getAllFunction(sql);
//	}
//	public int getResultSize(String sql){
//		return functionDao.getResultSize(sql) ;
//	}
//	public FunctionDao getFunctionDao() {
//		return functionDao;
//	}
//	public void setFunctionDao(FunctionDao functionDao) {
//		this.functionDao = functionDao;
//	}
//
//
//	public List getBusinessSystem(String sql) {
//		return functionDao.getBusinessSystem(sql);
//	}
//}
