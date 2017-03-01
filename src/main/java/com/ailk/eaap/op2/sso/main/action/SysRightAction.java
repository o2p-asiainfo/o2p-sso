//package com.ailk.eaap.op2.sso.main.action;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.struts2.ServletActionContext;
//
//import com.ailk.eaap.op2.sso.framework.action.BaseAction;
//import com.ailk.eaap.op2.sso.main.model.SysFunction;
//import com.ailk.eaap.op2.sso.main.model.SysRole;
//import com.ailk.eaap.op2.sso.main.service.SysRightService;
//import com.ailk.eaap.op2.sso.main.service.SysRoleFunctionService;
//import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
//
//public class SysRightAction extends BaseAction 
//{
//	private SysRightService sysRightService;
//	private SysRoleFunctionService sysRoleFunctionService;
//	List<SysRole> returnList = new ArrayList<SysRole>();
//	List<SysRole> returnList2 = new ArrayList<SysRole>();
//	private String be_authed_person_id  ;
//	private String be_authed_dept_id ;//1-18
//	private String auth_person_id ;
//	private Long sys_id_type ;
//	private List<String> rightList = new ArrayList<String>();
//	private List<String> roleList = new ArrayList<String>();
//	
//	private boolean edit ; 
//	
//    
//
//	public String getAuth_person_id() {
//		return auth_person_id;
//	}
//
//
//	public void setAuth_person_id(String auth_person_id) {
//		this.auth_person_id = auth_person_id;
//	}
//
//
//	public String getBe_authed_person_id() {
//		return be_authed_person_id;
//	}
//
//
//	public void setBe_authed_person_id(String be_authed_person_id) {
//		this.be_authed_person_id = be_authed_person_id;
//	}
//
//
//	public String authfirst()
//	{
//		returnList = new ArrayList<SysRole>();
//		returnList2 = new ArrayList<SysRole>();
//		
//		List<SysRole> sysRole = new ArrayList();
//	    Map<SysRole, List<SysFunction>> map = new HashMap<SysRole, List<SysFunction>>();
//	    be_authed_person_id = ServletActionContext.getRequest().getParameter("sysPersonId");
//	    be_authed_dept_id = ServletActionContext.getRequest().getParameter("deptId");
//	    auth_person_id = session.get("userId").toString();
//	    
//	    sys_id_type = Long.valueOf(ServletActionContext.getRequest().getParameter("sysidtype"));
//	    
//		//List list = sysRightService.getCanAuthRoleList(Long.valueOf(auth_person_id));
//	    
//	    //可授权角色
//	    List list = sysRightService.getCanAuthRoleList(Long.valueOf(auth_person_id),Long.valueOf(be_authed_person_id));
//	    
//	    //被授权人员可访问的功能点
//	    rightList = sysRightService.getRightListBySyspersonId(be_authed_person_id ,be_authed_dept_id);
//	    
//	    //被授权人员具有的角色
//	    roleList = sysRightService.getRoleIdListBySyspersonId(be_authed_person_id ,be_authed_dept_id) ;
//	    
//	    edit = rightList.size()>0 ;
//		if(list.size()>0)
//		{
//			for(int i=0;i<list.size();i++)
//			{
//				//List<Map<String, Object>> roleFunction = sysRoleFunctionService.getAuthedFunctionsByRoleId(((SysRole)list.get(i)).getSysRoleId());
//				List<Map<String, Object>> roleFunction = 
//					sysRoleFunctionService.getCanAuthFunctionsByRoleId(((SysRole)list.get(i)).getSysRoleId(), Long.valueOf(auth_person_id));
//				
//				Iterator it = roleFunction.iterator();   
//				ArrayList<SysFunction> functionList = new ArrayList<SysFunction>();
//				while(it.hasNext()) 
//				{   
//					SysFunction sf = new SysFunction();
//				    Map userMap = (Map) it.next();   
//				    sf.setFunctionId(userMap.get("function_id").toString());   
//				    sf.setFunctionName(userMap.get("function_name").toString());    
//				    //sf.setBusinessSystemId(Long.valueOf(userMap.get("BUSINESS_SYSTEM_ID").toString()));   
//				    //sf.setBusinessSystemName(userMap.get("BUSINESS_SYSTEM_NAME").toString()); 
//				    if(rightList.contains(ConvertUtil.nullToSpace(userMap.get("function_id")))){
//				    	sf.setFlag(true) ;
//				    }else{
//				    	sf.setFlag(false) ;
//				    }
//				    
//				    functionList.add(sf);
//				}  
//				SysRole sysRole2 = (SysRole)list.get(i) ;
//				if(roleList.contains(ConvertUtil.nullToSpace(sysRole2.getSysRoleId()))){
//					sysRole2.setFlag(true) ;
//			    }else{
//			    	sysRole2.setFlag(false) ;
//			    }
//				sysRole2.setFunctionList(functionList) ;
//				sysRole2.setFunctionList2(functionList);
//				sysRole.add(sysRole2);
//				
//				returnList.add(sysRole2);
//				returnList2.add(sysRole2);
//			}
//		}
//		ServletActionContext.getRequest().setAttribute("sysRole", "sysRole");
//		return "success";
//	}
//	
//	public String view()
//	{
//		
//	    Map<SysRole, List<SysFunction>> map = new HashMap<SysRole, List<SysFunction>>();
//	    be_authed_person_id = ServletActionContext.getRequest().getParameter("sysPersonId");
//	    be_authed_dept_id = ServletActionContext.getRequest().getParameter("deptId");
//	    auth_person_id = session.get("userId").toString();
//	    sys_id_type = Long.valueOf(ServletActionContext.getRequest().getParameter("sysidtype"));
//	    
//	   // List canAuthRoleList = sysRightService.selectCanAuthRoles(Long.valueOf(auth_person_id), Long.valueOf(be_authed_person_id));
//	    List canAuthRoleList = sysRightService.getCanAuthRoleList(Long.valueOf(auth_person_id), Long.valueOf(be_authed_person_id));
//	    
//	    List authedRoleListA = sysRightService.getAuthedRoleList(new Long(be_authed_person_id), new Long(1));
//	    List authedRoleListO = sysRightService.getAuthedRoleList(new Long(be_authed_person_id), new Long(2));
//	    
//	    for(int i=0;i<canAuthRoleList.size();i++)
//	    {
//	    	SysRole sysRole = (SysRole)canAuthRoleList.get(i);
//	    	Long role_id = sysRole.getSysRoleId();
//	    	if(authedRoleListA.contains(String.valueOf(role_id)))
//	    	{
//	    		sysRole.setFlag(true);
//	    	}
//	    	else
//	    	{
//	    		sysRole.setFlag(false);
//	    	}
//	    	
//	    	//List functionList = sysRightService.selectCanAuthFunctionList(Long.valueOf(auth_person_id), String.valueOf(role_id));
//	    	List<Map<String, Object>> functionList = sysRoleFunctionService.getCanAuthFunctionsByRoleId(role_id, Long.valueOf(auth_person_id));
//	    	
//	    	List authedfunctionList = sysRightService.selectBeauthedFunctionList(Long.valueOf(be_authed_person_id),
//					    													String.valueOf(role_id), 
//					    													new Long(1));	    	
//	    	ArrayList<SysFunction> functionLists = new ArrayList<SysFunction>();
//	    	
//	    	/*for(int j=0;j<functionList.size();j++)
//	    	{
//	    		SysFunction sf = (SysFunction)functionList.get(j);
//	    		String function_id = sf.getFunctionId();
//	    		if(authedfunctionList.contains(function_id))
//	    		{
//	    			sf.setFlag(true);
//	    		}
//	    		else
//	    		{
//	    			sf.setFlag(false);
//	    		}
//	    		functionLists.add(sf);
//	    	}*/
//	    	Iterator it = functionList.iterator();   
//			ArrayList<SysFunction> functionList2 = new ArrayList<SysFunction>();
//			while(it.hasNext()) 
//			{   
//				SysFunction sf = new SysFunction();
//			    Map userMap = (Map) it.next();   
//			    sf.setFunctionId(userMap.get("function_id").toString());   
//			    sf.setFunctionName(userMap.get("function_name").toString());    
//			    //sf.setBusinessSystemId(Long.valueOf(userMap.get("BUSINESS_SYSTEM_ID").toString()));   
//			    //sf.setBusinessSystemName(userMap.get("BUSINESS_SYSTEM_NAME").toString()); 
//			    if(authedfunctionList.contains(ConvertUtil.nullToSpace(userMap.get("function_id")))){
//			    	sf.setFlag(true) ;
//			    }else{
//			    	sf.setFlag(false) ;
//			    }
//			    
//			    functionList2.add(sf);
//			}  
//			
//	    	sysRole.setFunctionList(functionList2);
//	    	returnList.add(sysRole);
//	    }
//	    
//	    for(int i=0;i<canAuthRoleList.size();i++)
//	    {
//	    	SysRole sysRole = (SysRole)canAuthRoleList.get(i);
//	    	Long role_id = sysRole.getSysRoleId();
//	    	if(authedRoleListO.contains(String.valueOf(role_id)))
//	    	{
//	    		sysRole.setFlag(true);
//	    	}
//	    	else
//	    	{
//	    		sysRole.setFlag(false);
//	    	}
//	    	//List functionList2 = sysRightService.selectCanAuthFunctionList(Long.valueOf(auth_person_id), String.valueOf(role_id));
//	    	List<Map<String, Object>> functionList = sysRoleFunctionService.getCanAuthFunctionsByRoleId(role_id, Long.valueOf(auth_person_id));
//	    	
//	    	List authedfunctionList2 = sysRightService.selectBeauthedFunctionList(Long.valueOf(be_authed_person_id),
//					    													String.valueOf(role_id), 
//					    													new Long(2));	    	
//	    	ArrayList<SysFunction> functionListo = new ArrayList<SysFunction>();
//	    	
//	    	/*for(int j=0;j<functionList2.size();j++)
//	    	{
//	    		SysFunction sf = (SysFunction)functionList2.get(j);
//	    		String function_id = sf.getFunctionId();
//	    		if(authedfunctionList2.contains(function_id))
//	    		{
//	    			sf.setFlag(true);
//	    		}
//	    		else
//	    		{
//	    			sf.setFlag(false);
//	    		}
//	    		functionListo.add(sf);
//	    	}*/
//	    	Iterator it = functionList.iterator();   
//			ArrayList<SysFunction> functionList2 = new ArrayList<SysFunction>();
//			while(it.hasNext()) 
//			{   
//				SysFunction sf = new SysFunction();
//			    Map userMap = (Map) it.next();   
//			    sf.setFunctionId(userMap.get("function_id").toString());   
//			    sf.setFunctionName(userMap.get("function_name").toString());    
//			    //sf.setBusinessSystemId(Long.valueOf(userMap.get("BUSINESS_SYSTEM_ID").toString()));   
//			    //sf.setBusinessSystemName(userMap.get("BUSINESS_SYSTEM_NAME").toString()); 
//			    if(authedfunctionList2.contains(ConvertUtil.nullToSpace(userMap.get("function_id")))){
//			    	sf.setFlag(true) ;
//			    }else{
//			    	sf.setFlag(false) ;
//			    }
//			    
//			    functionList2.add(sf);
//			}  
//			
//	    	sysRole.setFunctionList2(functionList2);
//	    	returnList2.add(sysRole);
//	    }
//		return "success";
//	}
//	
//	public String add()
//	{   
//		String [] functionIds ;
//		 List<Map<String, Object>> functionIdList  ;
//		
//		 sysRightService.deleteSysRight(be_authed_person_id) ;
//		 
//		 String [] roleIds2 = ServletActionContext.getRequest().getParameterValues("sysRoleId2");
//		 if(roleIds2!=null&&roleIds2.length>=1){ 
//		 for(String roleId2 : roleIds2)
//		 {
//			   functionIds = ServletActionContext.getRequest().getParameterValues("functionsId2"+roleId2) ;
//			   auth_person_id = session.get("userId").toString();
//			   if(functionIds!=null&&functionIds.length>=1)
//			   {
//				   functionIdList = sysRightService.getFunctionIds(functionIds) ;
//				   for(Map<String, Object> functionId :functionIdList)
//				   {
//				   sysRightService.insertSysRight(be_authed_person_id, roleId2, ConvertUtil.nullToSpace(functionId.get("function_id")),"2") ;
//				   }
//			   }
//			   else
//			   {
//				   //sysRightService.insertSysRight(be_authed_person_id, roleId, "") ;  
//			   }
//			   
//		   }
//		 }
//		 
//		 
//		
//		 String [] roleIds = ServletActionContext.getRequest().getParameterValues("sysRoleId");
//		 //sysRightService.deleteSysRight(be_authed_person_id) ;
//		 if(roleIds!=null&&roleIds.length>=1){ 
//		 for(String roleId : roleIds){
//			   functionIds = ServletActionContext.getRequest().getParameterValues("functionsId"+roleId) ;
//			   auth_person_id = session.get("userId").toString();
//			   if(functionIds!=null&&functionIds.length>=1)
//			   {
//				   functionIdList = sysRightService.getFunctionIds(functionIds) ;
//				   for(Map<String, Object> functionId :functionIdList)
//				   {
//					   sysRightService.insertSysRight(be_authed_person_id, roleId, ConvertUtil.nullToSpace(functionId.get("function_id")),"1") ;
//				   }
//			   }else{
//				   //sysRightService.insertSysRight(be_authed_person_id, roleId, "") ;  
//			   }
//			   
//		   }
//		 }
//		 
//		 
//		 return "success";
//	}
//	
//	
//	
//	public String update()
//	{
//		 
//		Map<SysRole, List<SysFunction>> map = new HashMap<SysRole, List<SysFunction>>();
//		 be_authed_person_id = ServletActionContext.getRequest().getParameter("sysPersonId");
//		
//		List auth_role_list = sysRightService.getAuthedRoleList(Long.valueOf(be_authed_person_id), new Long(1));
//		List operate_role_list = sysRightService.getAuthedRoleList(Long.valueOf(be_authed_person_id), new Long(2));
//		auth_person_id = session.get("userId").toString();
//		List list = sysRightService.getCanAuthRoleList(Long.valueOf(auth_person_id));
//		if(list.size()>0)
//		{
//			for(int i=0;i<list.size();i++)
//			{
//				List<Map<String, Object>> roleFunction =
//					sysRoleFunctionService.getAuthedFunctionsByRoleId(((SysRole)list.get(i)).getSysRoleId());
//				Iterator it = roleFunction.iterator();   
//				List<SysFunction> functionList = new ArrayList<SysFunction>();
//				while(it.hasNext()) 
//				{   
//					SysFunction sf = new SysFunction();
//				    Map userMap = (Map) it.next();   
//				    sf.setFunctionId(userMap.get("function_id").toString());   
//				    sf.setFunctionName(userMap.get("function_name").toString());    
//				    sf.setBusinessSystemId(Long.valueOf(userMap.get("BUSINESS_SYSTEM_ID").toString()));   
//				    sf.setBusinessSystemName(userMap.get("BUSINESS_SYSTEM_NAME").toString()); 
//				    functionList.add(sf);
//				}  
//			//	returnList.add(map.put((SysRole)list.get(i), functionList));
//			}
//		}
//		return "success";
//	}
//
//	public SysRightService getSysRightService() {
//		return sysRightService;
//	}
//
//	public void setSysRightService(SysRightService sysRightService) {
//		this.sysRightService = sysRightService;
//	}
//
//	public SysRoleFunctionService getSysRoleFunctionService() {
//		return sysRoleFunctionService;
//	}
//
//	public void setSysRoleFunctionService(
//			SysRoleFunctionService sysRoleFunctionService) {
//		this.sysRoleFunctionService = sysRoleFunctionService;
//	}
//
//	public List<SysRole> getReturnList() {
//		return returnList;
//	}
//
//	public void setReturnList(List<SysRole> returnList) {
//		this.returnList = returnList;
//	}
//
//
//	public List<String> getRightList() {
//		return rightList;
//	}
//
//
//	public void setRightList(List<String> rightList) {
//		this.rightList = rightList;
//	}
//
//
//	public boolean isEdit() {
//		return edit;
//	}
//
//
//	public void setEdit(boolean edit) {
//		this.edit = edit;
//	}
//
//
//	public List<String> getRoleList() {
//		return roleList;
//	}
//
//
//	public void setRoleList(List<String> roleList) {
//		this.roleList = roleList;
//	}
//
//
//	public String getBe_authed_dept_id() {
//		return be_authed_dept_id;
//	}
//
//
//	public void setBe_authed_dept_id(String be_authed_dept_id) {
//		this.be_authed_dept_id = be_authed_dept_id;
//	}
//
//
//	public List<SysRole> getReturnList2() {
//		return returnList2;
//	}
//
//
//	public void setReturnList2(List<SysRole> returnList2) {
//		this.returnList2 = returnList2;
//	}
//
//
//	public Long getSys_id_type() {
//		return sys_id_type;
//	}
//
//
//	public void setSys_id_type(Long sys_id_type) {
//		this.sys_id_type = sys_id_type;
//	}
//}
