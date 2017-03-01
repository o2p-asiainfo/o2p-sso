//package com.ailk.eaap.op2.sso.main.dao;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.ailk.eaap.op2.sso.main.model.SysFunction;
//import com.ailk.eaap.op2.sso.main.model.SysRole;
//import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
//import com.opensymphony.xwork2.ActionContext;
//
//public class SysRightDaoImpl implements SysRightDao 
//{
//	private JdbcTemplate xrbJdbcTemplate;
//	
//	public List getAuthedFunctionByUseridRoleType(String userid,String roleid,String usertypeid)
//	{
//		List rows = xrbJdbcTemplate.queryForList("select distinct f.function_id ,f.function_name " +
//				"from sys_right r,sys_function f " +
//				"where  r.function_id = f.function_id " +
//				"and r.sys_person_id = " +userid+
//				" and r.sys_role_id = " +roleid +
//				" and r.sys_role_type_id = "+usertypeid);   
//		List<SysFunction> list = new ArrayList<SysFunction>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysFunction sf = new SysFunction();
//		    Map userMap = (Map) it.next();   
//		    sf.setFunctionId(userMap.get("function_id").toString());   
//		    sf.setFunctionName(userMap.get("function_name").toString());     
//		    list.add(sf);
//		}  
//		return list;
//	}
//	
//	public List getCanAuthRoleList(Long auth_person_id)
//	{
//		
//		 String querySql = "select distinct r.sys_role_id sys_role_id,r.sys_role_name sys_role_name " +
//			"from sys_person p ,sys_right right,sys_role r,sys_role_type t " +
//			"where p.sys_person_id = right.sys_person_id "  +
//			"and right.sys_role_id = r.sys_role_id " +
//			"and t.sys_role_type_id = 1 and p.sys_person_id = "+auth_person_id ;
//			 
//		 if("admin".equals((String)ActionContext.getContext().getSession().get("userright"))){
//			 querySql = "select *  from sys_role" ;
//		 }
//        List rows = xrbJdbcTemplate.queryForList(querySql);   
//		List<SysRole> list = new ArrayList<SysRole>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysRole sr = new SysRole();
//		    Map userMap = (Map) it.next();   
//		    sr.setSysRoleId(Long.valueOf(userMap.get("sys_role_id").toString()));   
//		    sr.setSysRoleName(userMap.get("sys_role_name").toString());    
//		    list.add(sr);
//		}  
//		return list;
//	}
//	
//	public List getCanAuthRoleList(Long auth_person_id,Long be_authed_person_id)
//	{
//		
//		 String querySql = "select distinct r.sys_role_id sys_role_id,r.sys_role_name sys_role_name " +
//			"from sys_person p ,sys_right right,sys_role r,sys_role_type t " +
//			"where p.sys_person_id = right.sys_person_id "  +
//			"and right.sys_role_id = r.sys_role_id " +
//			"and t.sys_role_type_id = 1 and p.sys_person_id = "+auth_person_id 
//			+" and r.sys_role_id in ( select distinct r2.sys_role_id " +
//					"	from sys_dept d,sys_dept_role dr,sys_role r2 " +
//					" where d.dept_id = dr.dept_id" +
//					"				and dr.role_id = r2.sys_role_id" +
//					"				and d.dept_id in " +
//					"				(select dp.dept_id" +
//					"				from sys_dept_person dp,sys_person p2" +
//					"				where p2.sys_person_id = dp.sys_person_id" +
//					"				and p2.sys_person_id = "+be_authed_person_id+"	))";
//			 
//		 if("admin".equals((String)ActionContext.getContext().getSession().get("userright"))){
//			 querySql = "select *  from sys_role where sys_status_id=1" ;
//		 }
//        List rows = xrbJdbcTemplate.queryForList(querySql);   
//		List<SysRole> list = new ArrayList<SysRole>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysRole sr = new SysRole();
//		    Map userMap = (Map) it.next();   
//		    sr.setSysRoleId(Long.valueOf(userMap.get("sys_role_id").toString()));   
//		    sr.setSysRoleName(userMap.get("sys_role_name").toString());    
//		    list.add(sr);
//		}  
//		return list;
//	}
//	
//	public List getFunctionIds(String [] functionIds)
//	{    
//		String ids = "''" ;
//		for(String functionId : functionIds)
//		 {
//			ids += ","+functionId ;
//		 }
//		String getFunctionIdSql = " select distinct function_id"
//								 +"   from sys_function"
//								 +"  start with function_id in ("+ids+")"
//								 +" connect by function_id = PRIOR parent_function_id" ;
//		List rows = xrbJdbcTemplate.queryForList(getFunctionIdSql);   
//		return rows;
//	}
//	
//	public List getRightListBySyspersonId(String syspersonid ,String deptId)
//	{    
//		 
//		String getRightList = "select FUNCTION_ID from sys_right " ;
//			if(!"".equals(ConvertUtil.nullToSpace(syspersonid))){
//				getRightList+=" where sys_person_id ="+syspersonid ;
//			}
//		List rows = xrbJdbcTemplate.queryForList(getRightList);   
//		List<String> list = new ArrayList<String>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			Map sysRightMap = (Map) it.next();   
//		    list.add(ConvertUtil.nullToSpace(sysRightMap.get("FUNCTION_ID")));
//		}  
//		return list;
//	}
//	
//	public List getRoleIdListBySyspersonId(String syspersonid ,String deptId)
//	{    
//		 
//		String getRightList = " select distinct r.sys_role_id  from sys_right r" ;
//			if(!"".equals(ConvertUtil.nullToSpace(syspersonid))){
//				getRightList+=" where r.sys_person_id ="+syspersonid;
//			}
//		List rows = xrbJdbcTemplate.queryForList(getRightList);   
//		List<String> list = new ArrayList<String>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			Map sysRightMap = (Map) it.next();   
//		    list.add(ConvertUtil.nullToSpace(sysRightMap.get("sys_role_id")));
//		}  
//		return list;
//	}
//
//	public void insertSysRight(String sysPersonId , String SysRoleId ,String functionId,String SYS_ROLE_TYPE_ID)
//	{    
//		String authSysPersonId = (String)ActionContext.getContext().getSession().get("userId") ;
//		  
//		 
//		   String insertSql = " insert into SYS_RIGHT"
//							 +"   (sys_right_id, sys_person_id, SYS_ROLE_ID, Function_Id, Auth_Person_Id,auth_time,SYS_ROLE_TYPE_ID)"
//							 +" values"
//							 +"   (SEQ_SYS_RIGHT.NEXTVAL, '"+sysPersonId+"', '"
//							 +SysRoleId+"', '"+functionId+"', '"
//							 +ConvertUtil.nullToSpace(authSysPersonId)+"',sysdate"+","+SYS_ROLE_TYPE_ID+")" ;
//		xrbJdbcTemplate.update(insertSql);   
//	 
//	}
//	
//	public void deleteSysRight(String sysPersonId){
//		 xrbJdbcTemplate.update("delete from SYS_RIGHT where sys_person_id="+sysPersonId) ;
//	}
//	
//	public JdbcTemplate getXrbJdbcTemplate() {
//		return xrbJdbcTemplate;
//	}
//
//	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
//		this.xrbJdbcTemplate = xrbJdbcTemplate;
//	}
//
//
//	public void delByRoleidandFunctionid(Long role_id, Long function_id) {
//		xrbJdbcTemplate.update("delete from sys_right t where t.sys_role_id = "+role_id+ "and t.function_id ="+function_id) ;
//	}
//
//	public List getAuthedRoleList(Long sys_person,Long roletype)
//	{
//		List rows = xrbJdbcTemplate.queryForList("select distinct r.sys_role_id        sys_role_id," +
//				"       r.sys_role_name      sys_role_name," +
//				"       t.sys_role_type_id   sys_role_type_id," +
//				"       t.sys_role_type_name sys_role_type_name" +
//				"  from sys_right right, sys_role r, sys_role_type t " +
//				" where right.sys_role_id = r.sys_role_id " +
//				"   and right.SYS_ROLE_TYPE_ID = t.sys_role_type_id " +
//				"   and right.sys_person_id = "+sys_person + " and  t.sys_role_type_id ="+roletype);   
//		List list = new ArrayList();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysRole sr = new SysRole();
//		    Map userMap = (Map) it.next();   
//		    /*sr.setSysRoleId(Long.valueOf(userMap.get("sys_role_id").toString()));   
//		    sr.setSysRoleName(userMap.get("sys_role_name").toString());    
//		    sr.setSysRoleTypeId(Long.valueOf(userMap.get("sys_role_type_id").toString()));
//		    sr.setSysRoleTypeName(userMap.get("sys_role_type_name").toString());  */  
//		    list.add(userMap.get("sys_role_id").toString());
//		}  
//		return list;
//	}
//	
//	public List selectBeauthedFunctionList(Long authedUserid, String roleid,Long roleType)
//	{
//		/*select distinct f.function_id,f.function_name
//		from sys_right right ,sys_function f
//		where right.function_id = f.function_id
//		and right.sys_role_type_id = ?
//		and right.sys_role_id = ?
//		and right.sys_person_id = ?*/
//		List rows = xrbJdbcTemplate.queryForList("select distinct f.function_id,f.function_name " +
//				"from sys_right right ,sys_function f " +
//				"where right.function_id = f.function_id " +
//				"and right.sys_role_type_id =  " +roleType+
//				" and right.sys_role_id = " +roleid +
//				" and right.sys_person_id = "+authedUserid);   
//		List list = new ArrayList();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysFunction sf = new SysFunction();
//		    Map userMap = (Map) it.next();   
//		    /*sf.setFunctionId(userMap.get("function_id").toString());   
//		    sf.setFunctionName(userMap.get("function_name").toString()); */    
//		    list.add(userMap.get("function_id").toString());
//		}  
//		return list;
//	}
//
//	public List selectCanAuthFunctionList(Long authUserid, String roleid) 
//	{
//		/*select distinct f.function_id,f.function_name
//		from sys_right right ,sys_function f
//		where right.function_id = f.function_id
//		and right.sys_role_type_id = 1
//		and right.sys_role_id = ?
//		and right.sys_person_id = ?*/
//		List rows = xrbJdbcTemplate.queryForList("select distinct f.function_id,f.function_name " +
//				"from sys_right right ,sys_function f " +
//				"where right.function_id = f.function_id " +
//				"and right.sys_role_type_id = 1 " +
//				"and right.sys_role_id = " +roleid +
//				"and right.sys_person_id = "+authUserid);   
//		List<SysFunction> list = new ArrayList<SysFunction>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysFunction sf = new SysFunction();
//		    Map userMap = (Map) it.next();   
//		    sf.setFunctionId(userMap.get("function_id").toString());   
//		    sf.setFunctionName(userMap.get("function_name").toString());     
//		    list.add(sf);
//		}  
//		return list;
//	}
//
//	public List selectCanAuthRoles(Long authuserid, Long beauthedUserid) 
//	{
//		/*select distinct r.sys_role_id,r.sys_role_name
//		from sys_right right ,sys_role r
//		where right.sys_role_id = r.sys_role_id
//		and right.sys_person_id = ?
//		and right.sys_role_type_id = 1
//		and right.sys_role_id in 
//		(select distinct dr.role_id
//		from sys_dept_role dr,sys_dept d,sys_dept_person dp
//		where d.dept_id = dr.dept_id
//		and d.dept_id = dp.dept_id
//		and dp.sys_person_id = ?)*/
//		List rows = xrbJdbcTemplate.queryForList("select distinct r.sys_role_id,r.sys_role_name " +
//				"					from sys_right right ,sys_role r" +
//				"							where right.sys_role_id = r.sys_role_id" +
//				"							and right.sys_person_id = " + authuserid +
//				"							and right.sys_role_type_id = 1" +
//				"							and right.sys_role_id in " +
//				"							(select distinct dr.role_id " +
//				"							from sys_dept_role dr,sys_dept d,sys_dept_person dp" +
//				"							where d.dept_id = dr.dept_id" +
//				"							and d.dept_id = dp.dept_id" +
//				"							and dp.sys_person_id = "+beauthedUserid+")");   
//		List<SysRole> list = new ArrayList<SysRole>();
//		Iterator it = rows.iterator();   
//		while(it.hasNext()) {   
//			SysRole sr = new SysRole();
//		    Map userMap = (Map) it.next();   
//		    sr.setSysRoleId(Long.valueOf(userMap.get("sys_role_id").toString()));   
//		    sr.setSysRoleName(userMap.get("sys_role_name").toString());    
//		    list.add(sr);
//		}  
//		return list;
//	}
//
//}
