package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.linkage.rainbow.dao.SqlMapDAO;
/**
 * 
 * @author zhaobl
 *
 */
public class SysCustomerDaoImpl implements ISysCustomerDao {
	private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());
	public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }
	
    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }
    
    
	public List<SysCustomer> queryAllSysCustomer(Map map) {
		List sysCustomerList = new ArrayList();
		try{
			sysCustomerList = sqlMapDao.queryForList("sysCustomer.queryAllSysCustomer", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysCustomerList != null)
			return (List<SysCustomer>)sysCustomerList;
		return null;
	}

	public SysCustomer querySysCustomerById(String sysCustomerId) {
		Object sysCustomer = new Object();
		try{
			sysCustomer = sqlMapDao.queryForObject("sysCustomer.querySysCustomerById", sysCustomerId);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysCustomer != null)
			return (SysCustomer)sysCustomer;
		return null;
	}

	public List<SysCustomer> queryAll() {
		List sysCustomerList = new ArrayList();
		try{
			sysCustomerList = sqlMapDao.queryForList("sysCustomer.queryAll", null);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(sysCustomerList != null)
			return (List<SysCustomer>)sysCustomerList;
		return null;
	}
	
	public int queryCount(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysCustomer.queryCount", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		return count;
	}

	public void insertSysCustomer(Map map) {
		try{
			sqlMapDao.insert("sysCustomer.insertSysCustomer", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public int deleteSysCustomer(String sysCustomerId) {
		try{
			sqlMapDao.delete("sysCustomer.deleteSysCustomerById", sysCustomerId);
			return 0;
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    		return 1;
		}
	}

	public void updateSysCustomer(Map map) {
		try{
			sqlMapDao.update("sysCustomer.updateSysCustomerById", map);
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
	}

	public boolean isCustomerExist(Map map) {
		int count = 0;
		try{
			count = ((Integer)sqlMapDao.queryForObject("sysCustomer.isCustomerExist", map)).intValue();
		}catch(Exception e){
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
		}
		if(count > 0)
			return true;
		return false;
	}
	
	
	
	
	
	
	
	/**
	private JdbcTemplate xrbJdbcTemplate;
	//private HttpServletRequest request;
	private static final String SEARCH_SQL ="select distinct a.* from sys_area a ,sys_customer c "+
				" where 1=1 ";
	public List<Map<String, Object>> getAreas(){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_area");   
		return rows;
	}
	//根据人员类型，公司名称进行数据域判断
	public List<Map<String, Object>> getAreas(String sysUserType,String customerName){
		List<Map<String, Object>> rows = null;
		if((sysUserType.equals("1"))||(sysUserType.equals("2") && customerName.equals("电信集团"))){
			rows = xrbJdbcTemplate.queryForList(SEARCH_SQL);
		}else{
			rows = xrbJdbcTemplate.queryForList(SEARCH_SQL + 
					" and a.sys_area_id = c.sys_area_id and c.sys_customer_name = '"+ customerName+"'");
		}
		return rows;
	}
	
	public List<Map<String, Object>> getCustomers(){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_customer");   
		return rows;
	}
	
	//根据数据域 判断条件 获取 公司信息DAO 方法
	public List<Map<String, Object>> getCustomers(Long cusId){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_customer where sys_customer_id = "+cusId);   
		return rows;
	}
	
	public List<Map<String, Object>> getUnAuthedCustomer(){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * " +
				"from sys_customer c where c.sys_customer_id not in " +
				"(select distinct cr.SYS_CUSTOMER_ID " +
				"from sys_customer_role cr) ");   
		return rows;
	}
	// 增加数据域判断方法
	public List<Map<String, Object>> getUnAuthedCustomer(String cusName){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * " +
				"from sys_customer c where c.sys_customer_id not in " +
				"(select distinct cr.SYS_CUSTOMER_ID " +
				"from sys_customer_role cr) and c.SYS_CUSTOMER_NAME = '"+cusName+"'");   
		return rows;
	}
	public int checkDept(Long syscustomerid)
	{
		int num = xrbJdbcTemplate.queryForInt("select count(*) from SYS_DEPT where sys_customer_id= " + syscustomerid);
		return num;
	}
	
	public int checkRole(Long syscustomerid){
		int num = xrbJdbcTemplate.queryForInt("select count(*) from SYS_CUSTOMER_ROLE where sys_customer_id= " + syscustomerid);
		return num;
	}
	
	public List<Map<String, Object>> getUnTotalAuthedCustomer(){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select distinct c.* " +
				"from sys_customer c,sys_dept d where c.sys_customer_id  = d.sys_customer_id" +
				" and d.dept_id not in"+
				"(select distinct dr.dept_id" +
				" from sys_dept_role dr)" );   
		return rows;
	}
	
	//3-17 增加 数据域判断方法 获取未完全授权 公司信息
	public List<Map<String, Object>> getUnTotalAuthedCustomer(String cusName){
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select distinct c.* " +
				"from sys_customer c,sys_dept d where c.sys_customer_id  = d.sys_customer_id" +
				" and d.dept_id not in"+
				"(select distinct dr.dept_id" +
				" from sys_dept_role dr) " +
				" and c.sys_customer_name = '"+cusName+"'" ); 
		return rows;
	}
	
	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER)
	{
		xrbJdbcTemplate.update("insert into sys_customer values (seq_sys_customer.nextval,'"+SYS_CUSTOMER_NAME+"',"
				+SYS_AREA_ID+","+SYS_CARD_TYPE_ID+",'"+SYS_CARD_NUMBER+"')");
	}
	
	public void updateBycustomerid(Long sys_customer_id,
			String SYS_CUSTOMER_NAME, Long SYS_AREA_ID, Long SYS_CARD_TYPE_ID,
			String SYS_CARD_NUMBER) {
		xrbJdbcTemplate.update("update sys_customer set SYS_CUSTOMER_NAME ='"+SYS_CUSTOMER_NAME+"',SYS_AREA_ID="
				+SYS_AREA_ID+",SYS_CARD_TYPE_ID="+SYS_CARD_TYPE_ID+",SYS_CARD_NUMBER='"+SYS_CARD_NUMBER+
				"' where SYS_CUSTOMER_ID =" +sys_customer_id );
	}
	//alert 3-5
	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID)
	{
		long SYS_CARD_TYPE_ID = 1;
		String SYS_CARD_NUMBER = "1";
		String sql = "insert into sys_customer (SYS_CUSTOMER_ID,SYS_CUSTOMER_NAME,SYS_AREA_ID,SYS_CARD_TYPE_ID,SYS_CARD_NUMBER) values (seq_sys_customer.nextval,'"+SYS_CUSTOMER_NAME+"',"
		+SYS_AREA_ID+","+SYS_CARD_TYPE_ID+",'"+SYS_CARD_NUMBER+"')";
		xrbJdbcTemplate.update(sql);
	}
	
	public void updateBycustomerid(Long sys_customer_id,
			String SYS_CUSTOMER_NAME, Long SYS_AREA_ID) {
		xrbJdbcTemplate.update("update sys_customer set SYS_CUSTOMER_NAME ='"+SYS_CUSTOMER_NAME+"',SYS_AREA_ID="
				+SYS_AREA_ID+",SYS_CARD_TYPE_ID=1"+",SYS_CARD_NUMBER='1'"+
				" where SYS_CUSTOMER_ID =" +sys_customer_id );
	}
	public List<Map<String, Object>> getPageResult(String sql) {
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
		return list;
	}
	
	public int getResultSize(String sql) 
	{
		int count = xrbJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
		return count;
	}
	
	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}

	public void del(Long syscustomerid) {
		xrbJdbcTemplate.update("delete from sys_customer where SYS_CUSTOMER_ID= " + syscustomerid);
	}

	**/
}
