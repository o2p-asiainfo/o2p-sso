package com.ailk.eaap.op2.sso.main.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ailk.eaap.op2.sso.main.model.DeptDataBean;
import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.linkage.rainbow.dao.SqlMapDAO;
/**
 * 
 * @author zhaobl
 *
 */
public class SysDeptDaoImpl implements ISysDeptDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
    
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }

	public List<SysDept> queryAllSysDept(Map map) {
		List sysDeptList = new ArrayList();
		try{
			sysDeptList = sqlMapDao.queryForList("sysDept.queryAllSysDept", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysDeptList != null)
			return (List<SysDept>)sysDeptList;
		return null;
	}

	public SysDept querySysDeptByDeptId(String deptId) {
		Object sysDept = new Object();
		try{
			sysDept = sqlMapDao.queryForObject("sysDept.querySysDeptByDeptId", deptId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysDept != null)
			return (SysDept)sysDept;
		return null;
	}

	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysDept.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public List<SysDept> queryAll() {
		List sysDeptList = new ArrayList();
		try{
			sysDeptList = sqlMapDao.queryForList("sysDept.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysDeptList != null)
			return (List<SysDept>)sysDeptList;
		return null;
	}

	public void insertSysDept(Map map) {
		try{
			sqlMapDao.insert("sysDept.insertSysDept", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public int deleteSysDept(String deptId) {
		try{
			sqlMapDao.delete("sysDept.deleteSysDeptById", deptId);
			return 0;
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    		return 1;
		}
	}

	public void updateSysDept(Map map) {
		try{
			sqlMapDao.update("sysDept.updateSysDeptById", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public boolean isDeptExist(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysDept.isDeptExist", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(count > 0)
			return true;
		return false;
	}
	
	
	
	
	
	
	
//	private JdbcTemplate xrbJdbcTemplate;
//
//	/*private static final String SELECT_ALL_CUSTOMER = "select sys_customer_id,sys_customer_name" +
//			" from xrb.SYS_CUSTOMER";*/
//	private static final String SELECT_ALL_CUSTOMER = "select sys_customer_id,sys_customer_name" +
//	" from SYS_CUSTOMER";
//	
//	/*private static final String SELECT_ALL_DEPT = "select dept_id,dept_name" +
//			" from xrb.SYS_DEPT" +
//			" where sys_customer_id=:customerId";*/
//	
//	private static final String SELECT_ALL_DEPT = "select dept_id,dept_name" +
//	" from SYS_DEPT" +
//	" where sys_customer_id=:customerId";
//	
//	/*private static final String SELECT_ALL_STATUS = "select sys_status_id,sys_status_name" +
//			" from xrb.sys_status";*/
//	
//	private static final String SELECT_ALL_STATUS = "select sys_status_id,sys_status_name" +
//	" from sys_status";
//	
//	private final static String DELETE_DEPT_SQL = "delete from SYS_DEPT a &condition&";
//	
//	private final static String SELECT_DEPT_COUNT_SQL = "select count(*) from SYS_DEPT a" +
//			" &condition&";
//	
//	private final static String SELECT_ROLE_COUNT_SQL = "select count(*) from SYS_DEPT_ROLE a" +
//			" &condition&";
//	
//	private final static String SELECT_SYSPERSON_COUNT_SQL = "select count(*) from SYS_PERSON a" +
//			" &condition&";
//	
//	/*private final static String INSERT_DEPT_SQL = "insert into SYS_DEPT (dept_id,dept_name,parent_dept_id,sys_customer_id,sys_status_id)" +
//			"values(xrb.seq_sys_dept.nextval,?,?,?,?)";*/
//	
//	private final static String INSERT_DEPT_SQL = "insert into SYS_DEPT (dept_id,dept_name,parent_dept_id,sys_customer_id,sys_status_id) " +
//	"values (seq_sys_dept.nextval,?,?,?,?)";
//	
//	private final static String UPDATE_DEPT_SQL = "update SYS_DEPT set dept_name=?,parent_dept_id=?,sys_customer_id=?,sys_status_id=?" +
//			" where dept_id = ?";
//	
//	public List<Map<String, Object>> getPageResult(String sql) {
//		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
//		return list;
//	}
//	
//	public List<KeyValueBean> selectCustomer() {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList(SELECT_ALL_CUSTOMER);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_customer_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_customer_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//
//	//3-16 增加 数据域判断方法 用户可选取 公司信息
//	public List<KeyValueBean> selectCustomer(Long cusId) {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList(SELECT_ALL_CUSTOMER+" where sys_customer_id ="+ cusId);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_customer_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_customer_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//	
//	public List<KeyValueBean> selectDept(String customerId) {
//		List<KeyValueBean> list = null;
//		try {
//			NamedParameterJdbcTemplate njt = new NamedParameterJdbcTemplate(
//					xrbJdbcTemplate);
//			Map<String, Object> inParam = new Map<String, Object>();			
//			inParam.put("customerId", customerId);
//			List<Map<String, Object>> result = njt.queryForList(SELECT_ALL_DEPT, inParam);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("dept_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("dept_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//	
//	public List<SysDept> deptList(String customerId) {
//		List<SysDept> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList("select * from sys_dept d where d.sys_customer_id ="+customerId);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<SysDept>();
//				for(Map<String, Object> item : result){
//					SysDept bean = new SysDept();
//					bean.setDeptId(Long.valueOf(ConvertUtil.nullToSpace(item.get("dept_id"))));
//					bean.setDeptName(ConvertUtil.nullToSpace(item.get("dept_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//
//	//1-14 加载其他部门
//	public List<KeyValueBean> selectOtherDept(String customerId ,String deptId ,String sysPersonId) {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList("select dept_id, dept_name" +
//				//	" from xrb.SYS_DEPT " +
//					" from SYS_DEPT " +
//					" where sys_customer_id="+customerId+
//					" and dept_id not in(select dept_id from "+
//					" sys_dept_person where SYS_PERSON_ID ="+ sysPersonId +")");
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("dept_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("dept_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//	
//	public String insertDept(final DeptDataBean editBean) {
//		String result = "";
//		try {
//			xrbJdbcTemplate.update(INSERT_DEPT_SQL,   
//                    new PreparedStatementSetter() {   
//                        public void setValues(PreparedStatement pstm) throws SQLException {   
//                        	pstm.setObject(1, editBean.getDeptName());
//                        	pstm.setObject(2, editBean.getParentDeptId());
//                        	pstm.setObject(3, editBean.getCustomerId());
//                        	pstm.setObject(4, editBean.getStatusId());
//                        }   
//                    });  
//			result="新增部门成功！";
//		} catch (Exception e) {
//			result =  "新增部门发生异常！";
//		}
//		return result;
//	}
//
//	public String updateDept(final DeptDataBean editBean) {
//		String result = "";
//		try {
//			xrbJdbcTemplate.update(UPDATE_DEPT_SQL,   
//                    new PreparedStatementSetter() {   
//                        public void setValues(PreparedStatement pstm) throws SQLException {   
//                        	pstm.setObject(1, editBean.getDeptName());
//                        	pstm.setObject(2, editBean.getParentDeptId());
//                        	pstm.setObject(3, editBean.getCustomerId());
//                        	pstm.setObject(4, editBean.getStatusId());
//                        	pstm.setObject(5, editBean.getDeptId());
//                        }   
//                    });  
//			result="更新部门成功！";
//		} catch (Exception e) {
//			result =  "更新部门发生异常！";
//		}
//		return result;
//	}
//
//	public List<KeyValueBean> selectStatus() {
//		List<KeyValueBean> list = null;
//		try {
//			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList(SELECT_ALL_STATUS);
//			if(result != null && result.size() > 0){
//				list = new ArrayList<KeyValueBean>();
//				for(Map<String, Object> item : result){
//					KeyValueBean bean = new KeyValueBean();
//					bean.setKey(ConvertUtil.nullToSpace(item.get("sys_status_id")));
//					bean.setValue(ConvertUtil.nullToSpace(item.get("sys_status_name")));
//					list.add(bean);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}
//
//	public String deleteDepts(String[] id) {
//		String result = "";
//		String condition = "";
//		String delSql = "";
//		String countRoleSql = "";
//		String countDeptSql = "";
//		String countPersonSql = "";
//		try {
//			condition = buildPriKeyCondition(id);
//			delSql = DELETE_DEPT_SQL.replaceAll("&condition&", condition);
//			countRoleSql = SELECT_ROLE_COUNT_SQL.replaceAll("&condition&", condition);
//			countDeptSql = SELECT_DEPT_COUNT_SQL.replaceAll("&condition&", condition);
//			countDeptSql = countDeptSql.replace("a.dept_id", "a.parent_dept_id");
//			countPersonSql = SELECT_SYSPERSON_COUNT_SQL.replaceAll("&condition&", condition);
//			int a = xrbJdbcTemplate.queryForInt(countRoleSql);
//			int b = xrbJdbcTemplate.queryForInt(countDeptSql);
//			//int c = xrbJdbcTemplate.queryForInt(countPersonSql);
//			String dept_ids = "(";
//			for(int i=0;i<id.length;i++)
//			{
//				if(i==id.length-1)
//				{
//					dept_ids += id[i];
//				}
//				else
//				{
//					dept_ids += id[i]+",";
//				}
//			}
//		
//			int c = xrbJdbcTemplate.queryForInt("select count(p.sys_person_id) " +
//														"from sys_dept d,sys_dept_person dp ,sys_person p " +
//														"where dp.sys_person_id  = p.sys_person_id " +
//														"and dp.dept_id = d.dept_id " +
//														"and d.dept_id in "+ dept_ids+")");
//			if(a+b+c == 0){
//				xrbJdbcTemplate.update(delSql);
//				result = "删除成功！";
//			}else{
//				result = "您要删除的部门中已经有关联员工或者关联角色，不能删除！";
//			}
//		} catch (Exception e) {
//			result =  "发生异常！";
//		}
//		return result;
//	}
//	
//	
//	
//	public int getResultSize(String sql) 
//	{
//		int count = xrbJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
//		return count;
//	}
//
//	private String buildPriKeyCondition(String[] keys){
//		StringBuffer buffer = new StringBuffer("where a.dept_id in(");
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


}
