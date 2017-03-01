package com.ailk.eaap.op2.sso.main.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.RoleDataBean;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysRole;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.linkage.rainbow.dao.SqlMapDAO;
/**
 * 角色Dao
 * @author zhaobl
 *
 */
public class SysRoleDaoImpl implements ISysRoleDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
    
	public int deleteSysRole(String sysRoleId) {
		try{
			sqlMapDao.delete("sysRole.deleteSysRoleById", sysRoleId);
			return 0;
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    		return 1;
		}
	}

	public void insertSysRole(Map map) {
		try{
			Object shownum = sqlMapDao.queryForObject("sysRole.queryShownum", null);
			if(shownum != null)
				map.put("shownum", shownum);
			sqlMapDao.insert("sysRole.insertSysRole", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public List<SysRole> queryAllSysRole(Map map) {
		List sysRoleList = new ArrayList();
		try{
			sysRoleList = sqlMapDao.queryForList("sysRole.queryAllSysRole", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleList != null)
			return (List<SysRole>)sysRoleList;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysRole.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public void updateSysRole(Map map) {
		try{
			sqlMapDao.update("sysRole.updateSysRoleById", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public SysRole querySysRoleById(String sysRoleId) {
		Object sysRole = new Object();
		try{
			sysRole = sqlMapDao.queryForObject("sysRole.querySysRoleById", sysRoleId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRole != null)
			return (SysRole)sysRole;
		return null;
	}

	public List<SysRole> queryAll() {
		List sysRoleList = new ArrayList();
		try{
			sysRoleList = sqlMapDao.queryForList("sysRole.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysRoleList != null)
			return (List<SysRole>)sysRoleList;
		return null;
	}

	public boolean isRoleExist(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysRole.isRoleExist", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(count > 0)
			return true;
		return false;
	}
	
	
	
	
	
	
//	private static final String SELECT_ALL_ROLETYPES = "select sys_role_type_id,sys_role_type_name" +
//	" from SYS_ROLE_TYPE";
//
//	private final static String DELETE_ROLE_SQL = "delete from SYS_ROLE a &condition&";
//	
//	private final static String SELECT_DEPTROLE_COUNT_SQL = "select count(*) from SYS_DEPT_ROLE a" +
//		" &condition&";
//	
//	private final static String SELECT_CUSTROLE_COUNT_SQL = "select count(*) from SYS_CUSTOMER_ROLE a" +
//		" &condition&";
//	
//	private final static String SELECT_SYSRIGHT_COUNT_SQL = "select count(*) from SYS_RIGHT a" +
//		" &condition&";
//	
//	private final static String SELECT_ROLEFUNCTION_COUNT_SQL = "select count(*) from SYS_ROLE_FUNCTION a" +
//	" &condition&";
//	
//	//private final static String INSERT_ROLE_SQL = "insert into SYS_ROLE (sys_role_id,sys_role_name,sys_status_id,shownum)" +
//	//	"values(seq_sys_role.nextval,?,'','')";
//	private final static String INSERT_ROLE_SQL = "insert into sys_role(sys_role_id,sys_role_name,sys_status_id)values "+
//		"(seq_sys_role.nextval,?,1)";
//	
//	
//	private final static String UPDATE_ROLE_SQL = "update SYS_ROLE set sys_role_name=? " +
//		" where sys_role_id = ?";
//
//	private JdbcTemplate xrbJdbcTemplate;
//	
//	/*public List<Map<String, Object>> getRolesByRoleTypeId(){
//		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_role");   
//		return rows;
//	}*/
//	
//	public List<Map<String, Object>> getRoles(){
//		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_role where sys_status_id=1");   
//		return rows;
//	}
//	
//	//3-16 增加数据判断条件 获取客户所属角色方法
//	public List<Map<String, Object>> getRoles(Long custid){
//		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select distinct r.* from sys_role r ,sys_customer_role cr " +
//				" where r.sys_role_id = cr.sys_role_id and sys_status_id=1 and cr.sys_customer_id = "+ custid);   
//		return rows;
//	}
//	
//	public void del(Long sysroleid)
//	{
//		//xrbJdbcTemplate.update("delete from sys_role where SYS_ROLE_ID= " + sysroleid);
//
//		xrbJdbcTemplate.update("update sys_role set sys_status_id=3  where SYS_ROLE_ID= " + sysroleid);
//
//	}
//	
//	public List<Map<String, Object>> getUnAuthedRoles(){
//		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * " +
//																		"from sys_role r " +
//																		"where r.sys_role_id not in " +
//																		"(select distinct rf.role_id " +
//																		"from sys_role_function rf) and r.sys_status_id=1");   
//		return rows;
//	}
//	
//	public List<Map<String, Object>> getRoleByRoleId(Long SYS_ROLE_ID){
//		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_role where SYS_ROLE_ID ="+SYS_ROLE_ID);   
//		return rows;
//	}
//	/*
//	public List<Map<String, Object>> getAuthedRolesByDeptId(Long deptid){
//		List<Map<String, Object>> rows = 
//			xrbJdbcTemplate.queryForList("select distinct r.sys_role_id,r.sys_role_name "+
//											"from sys_role r,sys_dept_role dr ,sys_dept d "+
//											"where dr.role_id = r.sys_role_id "+
//											"and dr.dept_id = d.dept_id "+
//											"and d.dept_id = "+deptid);   
//		return rows;
//	}*/
//	//alert by wanggm 2010-02-04
//	public List<Map<String, Object>> getAuthedRoleByCustId(String custid){
//		List<Map<String, Object>> rows =  
//			xrbJdbcTemplate.queryForList("select r.sys_role_id, r. sys_role_name"
//											+" from sys_role r, sys_customer_role cr"
//											+" where r.sys_role_id = cr.sys_role_id"
//											+" and cr.sys_customer_id = "+custid);   
//		return rows;
//	}
//	public List<Map<String, Object>> getRoles(String deptid,String custname){
//		List<Map<String, Object>> rows = 
//				xrbJdbcTemplate.queryForList("select distinct r.sys_role_id,r.sys_role_name "+
//												"from sys_role r,SYS_CUSTOMER_ROLE cr ,sys_dept d "+
//												"where cr.sys_role_id = r.sys_role_id "+
//												"and cr.sys_customer_id in(select sys_customer_id from sys_customer where "+
//												"sys_customer_name = '"+custname+"')");   
//		return rows;
//	}
//	
//	public List<Map<String, Object>> getAuthedRolesByDeptId(String deptid){
//		List<Map<String, Object>> rows = 
//			xrbJdbcTemplate.queryForList("select distinct r.sys_role_id "+
//											"from sys_role r,sys_dept_role dr ,sys_dept d "+
//											"where dr.role_id = r.sys_role_id "+
//											"and dr.dept_id = d.dept_id "+
//											"and d.dept_id = "+deptid);   
//		return rows;
//	}
//	public List<Map<String, Object>> getAuthedRoleByCustAndRoleTypeId(Long custid){
//		List<Map<String, Object>> rows = 
//			xrbJdbcTemplate.queryForList("select distinct r.sys_role_id sys_role_id, r.sys_role_name sys_role_name" +
//					"  from sys_customer c, sys_customer_role cr, sys_role r" +
//					" where cr.sys_customer_id = c.sys_customer_id" +
//					"   and cr.sys_role_id = r.sys_role_id" +
//					"   and c.sys_customer_id = " +custid);   
//		return rows;
//	}
//
//	public int getResultSize(String sql) {
//		int count = xrbJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
//		return count;
//	}
//
//	public List<Map<String, Object>> getPageResult(String sql) {
//		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
//		return list;
//	}
//	
//	public List<KeyValueBean> selectRoleTypes() {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList(SELECT_ALL_ROLETYPES);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_role_type_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_role_type_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//
//	//查询 客户所属 所有角色 12.28
//	public List<KeyValueBean> selectAuthRole(String customerId) {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList("select sys_role_id,sys_role_name" +
//					" from SYS_ROLE " +
//					" where sys_role_id in(select cr.sys_role_id from SYS_CUSTOMER_ROLE cr" +
//					" where cr.sys_customer_id="+customerId+")");
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_role_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_role_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//	
//	public List<KeyValueBean> selectOperRole(String customerId) {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList("select sys_role_id,sys_role_name" +
//					" from SYS_ROLE " +
//					" where sys_role_id in(select cr.sys_role_id from SYS_CUSTOMER_ROLE cr" +
//					" where cr.sys_customer_id="+customerId+")");
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_role_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_role_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//	
//	public List getRoleByrolename(String sql)
//	{
//		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
//		return list;
//	}
//	
//	public String insertRole(final RoleDataBean editBean) {
//		String result = "";
//		try {
//			xrbJdbcTemplate.update(INSERT_ROLE_SQL,   
//                    new PreparedStatementSetter() {   
//                        public void setValues(PreparedStatement pstm) throws SQLException {   
//                        	pstm.setObject(1, editBean.getSysRoleNm());
//                        	//pstm.setObject(2, editBean.getSysRoleTypeId());
//                        	//pstm.setObject(3, editBean.getSysStatusId());
//                        }   
//                    });  
//			result="新增角色成功！";
//		} catch (Exception e) {
//			result =  "新增角色发生异常！";
//		}
//		return result;
//	}
//
//	public String updateRole(final RoleDataBean editBean) {
//		String result = "";
//		try {
//			xrbJdbcTemplate.update(UPDATE_ROLE_SQL,   
//                    new PreparedStatementSetter() {   
//                        public void setValues(PreparedStatement pstm) throws SQLException {   
//                        	pstm.setObject(1, editBean.getSysRoleNm());
//                        	//pstm.setObject(2, editBean.getSysRoleTypeId());
//                        	//pstm.setObject(3, editBean.getSysStatusId());
//                        	pstm.setObject(2, editBean.getSysRoleId());
//                        }   
//                    });  
//			result="更新角色成功！";
//		} catch (Exception e) {
//			result =  "更新角色发生异常！";
//		}
//		return result;
//	}
//
//	public String deleteRoles(String[] id) {
//		String result = "";
//		String condition = "";
//		String delSql = "";
//		String countDeptRoleSql = "";
//		String countCustRoleSql = "";
//		String countSysRightSql = "";
//		String countRoleFuncSql = "";
//		try {
//			condition = buildPriKeyCondition(id);
//			delSql = DELETE_ROLE_SQL.replaceAll("&condition&", condition);
//			countDeptRoleSql = SELECT_DEPTROLE_COUNT_SQL.replaceAll("&condition&", condition);
//			countDeptRoleSql = countDeptRoleSql.replaceAll("a.sys_role_id", "a.role_id");
//			countCustRoleSql = SELECT_CUSTROLE_COUNT_SQL.replaceAll("&condition&", condition);
//			countSysRightSql = SELECT_SYSRIGHT_COUNT_SQL.replaceAll("&condition&", condition);
//			countRoleFuncSql = SELECT_ROLEFUNCTION_COUNT_SQL.replaceAll("&condition&", condition);
//			countRoleFuncSql = countRoleFuncSql.replaceAll("a.sys_role_id", "a.role_id");
//			int a = xrbJdbcTemplate.queryForInt(countDeptRoleSql);
//			int b = xrbJdbcTemplate.queryForInt(countCustRoleSql);
//			int c = xrbJdbcTemplate.queryForInt(countSysRightSql);
//			int d = xrbJdbcTemplate.queryForInt(countRoleFuncSql);
//			if(a+b+c+d == 0){
//				xrbJdbcTemplate.update(delSql);
//				result = "删除成功！";
//			}else{
//				result = "您要删除的角色中已经有与之关联的数据，不能删除！";
//			}
//		} catch (Exception e) {
//			result =  "发生异常！";
//		}
//		return result;
//	}
//	
//	private String buildPriKeyCondition(String[] keys){
//		StringBuffer buffer = new StringBuffer("where a.sys_role_id in(");
//		for(int i=0;i<keys.length;i++){
//			if(i+1!=keys.length){
//				buffer.append(keys[i]).append(",");
//			}else{
//				buffer.append(keys[i]);
//			}
//		}
//		buffer.append(")");
//		return buffer.toString();
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
//	public void synStatusChange(String roleId) {
//		final int roleIdInt = Integer.parseInt(roleId);
//		String sql = "update sys_role t set t.role_state = 'N' where t.sys_role_id=?";
//		xrbJdbcTemplate.update(sql,new PreparedStatementSetter() {   
//            public void setValues(PreparedStatement pstm) throws SQLException {   
//            	pstm.setObject(1, roleIdInt);
//            }   
//        });
//	}
}
