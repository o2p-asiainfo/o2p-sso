//package com.ailk.eaap.op2.sso.main.action;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.struts2.ServletActionContext;
//
//import com.ailk.eaap.op2.sso.framework.action.BaseAction;
//import com.ailk.eaap.op2.sso.main.model.PageBean;
//import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
//import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
//import com.ailk.eaap.op2.sso.main.model.SysFunction;
//import com.ailk.eaap.op2.sso.main.service.FunctionService;
//import com.ailk.eaap.op2.sso.main.util.AuthUtils;
//import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
//
//public class FunctionAction extends BaseAction{
//	private Map<String,Object> paramMap;
//	private String currPage;
//	private String pageNum;
//	private String doNum ;
//	private String functionId;
//	private String sysStatuId;
//	private String businessSystemId;
//	private String businessSystemName;
//	private String  business_system_id;
//	private String functionName;
//	private String parentFunctionId;
//	private String url; 
//	private String  stype ;
//	public SysFunction [] tf = null;
//	public SysBusinessSystem [] bs = null;
//	private FunctionService functionService;
//	//private BSFService businessSystemFunctionService ;
//	public List functionList = new ArrayList();
//	 
//	public String getBusinessSystem() 
//	{
//		SYSPERSON sysperson = (SYSPERSON)session.get("userbean") ;
//		String sql = "select distinct s.business_system_id,s.business_system_name,s.ip, s.port,s.domain " +
//							"from sys_person p ,sys_right r,sys_function f,sys_business_system s " +
//							"where r.sys_person_id = p.sys_person_id " +
//							"and r.function_id = f.function_id " +
//							"and f.business_system_id = s.business_system_id " +
//							"and p.sys_person_id = "+ConvertUtil.nullToSpace(sysperson.getSysPersonId()) ;
//		List<Map<String, Object>> list = functionService.getBusinessSystem(sql);
//		bs = new SysBusinessSystem[list.size()];
//		 for(int i=0;i<list.size();i++)
//         { 
//       	 Map<String, Object> map = list.get(i) ;
//       	 
//       	bs[i] = new SysBusinessSystem();
//       	bs[i].setBusinessSystemId(Long.valueOf(ConvertUtil.nullToSpace(map.get("business_system_id")))) ;
//       	bs[i].setBusinessSystemName(ConvertUtil.nullToSpace(map.get("business_system_name")));
//       	bs[i].setIp(ConvertUtil.nullToSpace(map.get("ip")));
//       	bs[i].setPort(Long.valueOf(ConvertUtil.nullToSpace(map.get("port"))));
//       	bs[i].setDomain(ConvertUtil.nullToSpace(map.get("domain")));
//         }
//		 request.setAttribute("bs", bs);
//				return "success";
//	}
//	
//	public String getFunctionListByUsernamePasswordBusinessSystem()
//	{
//		//businessSystemFunctionService.getFunctionListByUsernamePasswordBusinessSystem("9999", "88", "1");
//		return "success";
//	}
//	
//	public String getFunctionList() throws IOException
//	{
//		SYSPERSON sysperson = (SYSPERSON)session.get("userbean") ;
//		if(sysperson == null){
//			return "notLogin" ;
//		}
//		/*String sqlCount = "  select  distinct right.sys_right_id  "
//						   +" from SYS_RIGHT right, SYS_ROLE role  "
//						   +"where right.sys_person_id =" + ConvertUtil.nullToSpace(sysperson.getSysPersonId())
//						   +"   and role.sys_role_name = 'admin' "
//						   +"and role.sys_role_id = right.sys_role_id" ;
//		
//		String rightCount = " select distinct sys_role_id"
//						  +"   from sys_right"
//						  +"  where sys_role_id in"
//						  +"        (select sys_role_id from sys_role where sys_role_type = 1)"
//						  +"    and sys_person_id ="+ConvertUtil.nullToSpace(sysperson.getSysPersonId()) ;
//		 int  num = functionService.getResultSize(sqlCount) ;
//		 int rightNum = functionService.getResultSize(rightCount) ;*/
//		 //String functionSql = "select  *  from sys_function where business_system_id = 1 " ;
//		
//		String functionSql = "select  *  from sys_function where business_system_id = 1 " 
//							+ " and function_id in (select distinct function_id " 
//							+" from SYS_RIGHT  where sys_person_id ="
//							+ ConvertUtil.nullToSpace(sysperson.getSysPersonId())+" and sys_role_type_id = 2)  " ;	 
//         List<Map<String, Object>> list = functionService.getAllFunction(functionSql + " order by parent_function_id,shownum ");
//         tf = new SysFunction[list.size()];
//         for(int i=0;i<list.size();i++)
//          { 
//        	 url = "";
//        	 Map map = new HashMap();
//        	 map.clear();
//        	 map =  list.get(i) ;
//        	 
//        	 tf[i] = new SysFunction();
//        	 tf[i].setFunctionId(ConvertUtil.nullToSpace(map.get("function_id"))) ;
//        	 tf[i].setParentFunctionId(ConvertUtil.nullToSpace(map.get("PARENT_FUNCTION_ID")));
//        	 tf[i].setFunctionName(ConvertUtil.nullToSpace(map.get("FUNCTION_NAME")));
//        	 if(ConvertUtil.nullToSpace(map.get("URL"))!=null
//        			 && !(ConvertUtil.nullToSpace(map.get("URL")).equals("")) 
//        			 &&  (ConvertUtil.nullToSpace(map.get("URL"))!="")  )
//        		 
//			 {
//        		 url = map.get("URL").toString() + "&info="+sysperson.getSysPersonId();
//			 }
//        	 tf[i].setUrl(url); 
//          }
//         //request.setAttribute("tfunction", tf);
//         session.put("tfunction", tf);
//   	     return "success";
//	}
//	
//	public String showlist(){
//		paramMap = new HashMap<String, Object>();
//		SysFunction sysFunction = new SysFunction() ;
//	    PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		sysFunction.setPageBean(pageBean);
//		sysFunction.setFunctionName(functionName) ;
//		functionService.doSearchFlow(sysFunction) ;
//		List<SysFunction> sysBusinessList = functionService.getSysBusinessList() ;
//		paramMap.put("sysBusinessList",sysBusinessList) ;
//		paramMap.put("pageBean", sysFunction.getPageBean());
//		paramMap.put("searchResult",sysFunction.getSearchResult());
//		return SUCCESS ;
//	}
//	
//	
//	public String editper(){
//		    SysFunction sysFunction = new SysFunction() ;
//		    paramMap = new HashMap<String, Object>();
//		    sysFunction.setFunctionId(functionId) ;
//			PageBean pageBean = new PageBean();
//			if(currPage!=null){
//				pageBean.setCurrentPage(currPage);
//			}
//			if(pageNum!=null){
//				pageBean.setPageCount(pageNum);
//			}
//			sysFunction.setPageBean(pageBean);
//			functionService.doSearchFlow(sysFunction);
//			paramMap.put("searchResult",sysFunction.getSearchResult());
//			businessSystemId = String.valueOf(sysFunction.getSearchResult().get(0).getBusinessSystemId()) ;
//			sysStatuId = sysFunction.getSearchResult().get(0).getSysStatuId() ;
//			List<SysFunction> sysBusinessList = functionService.getSysBusinessList() ;
//			paramMap.put("sysBusinessList",sysBusinessList) ;
//		    return SUCCESS;
//			
//		}
//	
//	public String addper(){
//	    stype = "add" ;
//	    paramMap = new HashMap<String, Object>();
//	    List<SysFunction> sysBusinessList = functionService.getSysBusinessList() ;
//		paramMap.put("sysBusinessList",sysBusinessList) ;
//		return SUCCESS;
//		
//	}
//	
//	
//	
//	public String update(){
//	    SysFunction sysFunction = new SysFunction() ;
//	    sysFunction.setFunctionId(functionId) ;
//	    sysFunction.setFunctionName(functionName) ;
//	    sysFunction.setUrl(url) ;
//	    sysFunction.setBusinessSystemId(Long.valueOf(businessSystemId)) ;
//	    sysFunction.setParentFunctionId(parentFunctionId) ;
//	    sysFunction.setSysStatuId(sysStatuId) ;
//		if("add".equals(stype)){
//			functionService.updateFunction(sysFunction ,"add") ;
//			
//			SysFunction sf = functionService.getFuntionByfuntionaname(functionName);
//			AuthUtils au = new AuthUtils();
//			au.addfunction(Integer.parseInt(sf.getFunctionId()));
//		}
//		else{
//			functionService.updateFunction(sysFunction ,"update") ;
//		}
//		return SUCCESS;
//	}
//	
//	public String delete()
//	{
//		String [] ids = ServletActionContext.getRequest().getParameterValues("functionId");
//		if(ids.length>0)
//		{
//			for(int i=0;i<ids.length;i++)
//			{
//				doNum = String.valueOf(functionService.updateFunction(Long.valueOf(ids[i]) ,"delete")) ;
//				
//				 AuthUtils au = new AuthUtils();
//				au.delfunction(Integer.parseInt(ids[i]));
//					
//				if(doNum!=null && "-1".equals(doNum)){
//			    	return "error" ;
//			    }
//				//functionService.del(Long.valueOf(ids[i]));
//			}
//		}
//		return SUCCESS;
//	}
//	/*
//	public String delete(){
//		SysFunction sysFunction = new SysFunction() ;
//		sysFunction.setFunctionId(functionId) ;
//	    doNum = String.valueOf(functionService.updateFunction(sysFunction ,"delete")) ;
//	    if(doNum!=null && "-1".equals(doNum)){
//	    	return "error" ;
//	    }
//	    	
//		return SUCCESS;
//	}*/
//	
//	public String check(){
//	boolean isHave = functionService.checkHaveParentId(parentFunctionId) ;
//		if(isHave){
//			return SUCCESS ;
//		}else{
//			return "error" ;
//		}
//	
//	}
//	public FunctionService getFunctionService() {
//		return functionService;
//	}
//	public void setFunctionService(FunctionService functionService) {
//		this.functionService = functionService;
//	}
//	public void setFunctionList(List functionList) {
//		this.functionList = functionList;
//	}
//
//	public SysFunction[] getTf() {
//		return tf;
//	}
//
//	public Map<String, Object> getParamMap() {
//		return paramMap;
//	}
//
//	public void setParamMap(Map<String, Object> paramMap) {
//		this.paramMap = paramMap;
//	}
//
//	public void setTf(SysFunction[] tf) {
//		this.tf = tf;
//	}
//
//	public String getCurrPage() {
//		return currPage;
//	}
//
//	public void setCurrPage(String currPage) {
//		this.currPage = currPage;
//	}
//
//	public String getPageNum() {
//		return pageNum;
//	}
//
//	public void setPageNum(String pageNum) {
//		this.pageNum = pageNum;
//	}
//
//	public String getFunctionId() {
//		return functionId;
//	}
//
//	public void setFunctionId(String functionId) {
//		this.functionId = functionId;
//	}
//
//	 
//
//	public String getBusinessSystemId() {
//		return businessSystemId;
//	}
//
//	public void setBusinessSystemId(String businessSystemId) {
//		this.businessSystemId = businessSystemId;
//	}
//
//	public String getBusinessSystemName() {
//		return businessSystemName;
//	}
//
//	public void setBusinessSystemName(String businessSystemName) {
//		this.businessSystemName = businessSystemName;
//	}
//
//	public String getBusiness_system_id() {
//		return business_system_id;
//	}
//
//	public void setBusiness_system_id(String business_system_id) {
//		this.business_system_id = business_system_id;
//	}
//
//	public String getFunctionName() {
//		return functionName;
//	}
//
//	public void setFunctionName(String functionName) {
//		this.functionName = functionName;
//	}
//
//	public String getParentFunctionId() {
//		return parentFunctionId;
//	}
//
//	public void setParentFunctionId(String parentFunctionId) {
//		this.parentFunctionId = parentFunctionId;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getSysStatuId() {
//		return sysStatuId;
//	}
//
//	public void setSysStatuId(String sysStatuId) {
//		this.sysStatuId = sysStatuId;
//	}
//
//	public String getStype() {
//		return stype;
//	}
//
//	public void setStype(String stype) {
//		this.stype = stype;
//	}
//
//	public String getDoNum() {
//		return doNum;
//	}
//
//	public void setDoNum(String doNum) {
//		this.doNum = doNum;
//	}
//
//	public SysBusinessSystem[] getBs() {
//		return bs;
//	}
//
//	public void setBs(SysBusinessSystem[] bs) {
//		this.bs = bs;
//	}
//	/**
//	public BSFService getBusinessSystemFunctionService() {
//		return businessSystemFunctionService;
//	}
//
//	public void setBusinessSystemFunctionService(
//			BSFService businessSystemFunctionService) {
//		this.businessSystemFunctionService = businessSystemFunctionService;
//	}
//	**/
//}
