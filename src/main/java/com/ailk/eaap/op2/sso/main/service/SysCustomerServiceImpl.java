package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.sso.main.dao.ISysCustomerDao;
import com.ailk.eaap.op2.sso.main.dao.SysCustomerDao;
import com.ailk.eaap.op2.sso.main.dao.SysCustomerSqlUtil;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysCustomerBean;
import com.ailk.eaap.op2.sso.main.model.SysCustomerDataBean;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;
/**
 * 客户表 serv
 * @author zhaobl
 *
 */
public class SysCustomerServiceImpl implements ISysCustomerService {
	private ISysCustomerDao sysCustomerDao;

	public List<SysCustomer> queryAll() {
		List<SysCustomer> sysCustomerList = sysCustomerDao.queryAll();
		return sysCustomerList;
	}

	public List<SysCustomer> queryAllSysCustomer(Map map) {
		List<SysCustomer> sysCustomerList = sysCustomerDao.queryAllSysCustomer(map);
		return sysCustomerList;
	}

	public SysCustomer querySysCustomerById(String sysCustomerId) {
		SysCustomer sysCustomer = sysCustomerDao.querySysCustomerById(sysCustomerId);
		return sysCustomer;
	}

	public ISysCustomerDao getSysCustomerDao() {
		return sysCustomerDao;
	}

	public void setSysCustomerDao(ISysCustomerDao sysCustomerDao) {
		this.sysCustomerDao = sysCustomerDao;
	}

	public int deleteSysCustomer(String sysCustomerId) {
		int flag = sysCustomerDao.deleteSysCustomer(sysCustomerId);
		return flag;
	}

	public void insertSysCustomer(Map map) {
		sysCustomerDao.insertSysCustomer(map);
	}

	public int queryCount(Map map) {
		int count = sysCustomerDao.queryCount(map);
		return count;
	}

	public void updateSysCustomer(Map map) {
		sysCustomerDao.updateSysCustomer(map);
	}

	public boolean isCustomerExist(Map map) {
		boolean flag = sysCustomerDao.isCustomerExist(map);
		return flag;
	}

	public boolean isUpdateCustomerExist(Map map) {
		String sysCustomerId =  map.get("sysCustomerId").toString();
		String sysCustomerName = map.get("sysCustomerName").toString();
		SysCustomer sysCustomer = sysCustomerDao.querySysCustomerById(sysCustomerId);
		if(sysCustomerName.equals(sysCustomer.getSysCustomerName())){
			return false;
		}else{
			return this.isCustomerExist(map);
		}
	}

	
	
	
	
	
	
	
	
//	private SysCustomerDao sysCustomerDao;
//	private JdbcTemplate xrbJdbcTemplate;
//	HttpServletRequest request;
//	
//	public List<SysArea> getAreas()
//	{
//		List<SysArea> list = new ArrayList<SysArea>();
//		List<Map<String,Object>> Result = sysCustomerDao.getAreas();
//	    this.convertResult(Result, list);
//	    return list ;
//	}
//	//3-9 增加数据域判断 公司名称
//	public List<SysArea> getAreas(String sysUserType,String customerName)
//	{
//		List<SysArea> list = new ArrayList<SysArea>();
//		List<Map<String,Object>> Result = sysCustomerDao.getAreas(sysUserType,customerName);
//	    this.convertResult(Result, list);
//	    return list ;
//	}
//	public List<SysCustomer> getCustomers()
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getCustomers();
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	//3-18 根据 数据域判断条件 获取 公司信息
//	public List<SysCustomer> getCustomers(Long cusId)
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getCustomers(cusId);
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	
//	public List<SysCustomer> getUnAuthedCustomer()
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getUnAuthedCustomer();
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	//3-16 增加数据域判断 获取未授权用户信息方法
//	public List<SysCustomer> getUnAuthedCustomer(String cusName)
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getUnAuthedCustomer(cusName);
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	public List<SysCustomer> getUnTotalAuthedCustomer()
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getUnTotalAuthedCustomer();
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	
//	//3-17 增加数据域 判断 获取 未完全授权 公司信息
//	public List<SysCustomer> getUnTotalAuthedCustomer(String cusName)
//	{
//		List<SysCustomer> list = new ArrayList<SysCustomer>();
//		List<Map<String,Object>> Result = sysCustomerDao.getUnTotalAuthedCustomer(cusName);
//	    this.convertSysCustomerResult(Result, list);
//	    return list ;
//	}
//	
//	private void convertSysCustomerResult(List<Map<String,Object>> result,List<SysCustomer> list){
//		for(Map<String,Object> item : result){
//			SysCustomer bean = new SysCustomer();
//			bean.setSysCustomerId(Long.valueOf(ConvertUtil.nullToSpace(item.get("sys_customer_id"))));
//			bean.setSysCustomerName(ConvertUtil.nullToSpace(item.get("sys_customer_name")));
//			list.add(bean);
//		}
//	}
//	
//	public void del(Long syscustomerid)
//	{
//		sysCustomerDao.del(syscustomerid);
//	}
//	//2-25
//	public int checkDept(Long syscustomerid){ 
//		return sysCustomerDao.checkDept(syscustomerid);
//	}
//	
//	public int checkRole(Long syscustomerid){
//		return sysCustomerDao.checkRole(syscustomerid);
//	}
//	
//	public List<Map<String,Object>> getPageResult(String sql)
//	{
//		return sysCustomerDao.getPageResult(sql);
//	}
//	
//	private void convertResult(List<Map<String,Object>> result,List<SysArea> list)
//	{
//		for(Map<String,Object> item : result)
//		{
//			SysArea area = new SysArea();
//			area.setSysAreaId(Long.valueOf(ConvertUtil.nullToSpace(item.get("SYS_AREA_ID"))));
//			area.setSysAreaName(ConvertUtil.nullToSpace(item.get("SYS_AREA_NAME")));
//			list.add(area);
//		}
//	}
//	
//	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID,Long SYS_CARD_TYPE_ID,String SYS_CARD_NUMBER)
//	{
//		sysCustomerDao.insert(SYS_CUSTOMER_NAME, SYS_AREA_ID, SYS_CARD_TYPE_ID, SYS_CARD_NUMBER);
//	}
//	/*
//	public void doSearch(SysCustomerBean bean) {
//		List<SysCustomerDataBean> list = new ArrayList<SysCustomerDataBean>();
//		String sql = SysCustomerSqlUtil.buildSearchFlowSql(bean);
//		Integer size = sysCustomerDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
//			List<Map<String,Object>> pageResult = sysCustomerDao.getPageResult(pageSql + " order by sys_customer_id");
//			this.convertSysCustomerDataBeanResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}*/
//	//3-9 增加数据域判断 
//	public void doSearch(SysCustomerBean bean,String sysUserType,String customerName) {
//		List<SysCustomerDataBean> list = new ArrayList<SysCustomerDataBean>();
//		String sql = SysCustomerSqlUtil.buildSearchFlowSql(bean,sysUserType,customerName);
//		Integer size = sysCustomerDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
//			List<Map<String,Object>> pageResult = sysCustomerDao.getPageResult(pageSql + " order by sys_customer_id");
//			this.convertSysCustomerDataBeanResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}
//	
//	private void convertSysCustomerDataBeanResult(List<Map<String,Object>> result,List<SysCustomerDataBean> list){
//		for(Map<String,Object> item : result){
//			SysCustomerDataBean bean = new SysCustomerDataBean();
//			bean.setSysCustomerId(Long.valueOf(ConvertUtil.nullToSpace(item.get("sys_customer_id"))));
//			bean.setSysCustomerName(ConvertUtil.nullToSpace(item.get("sys_customer_name")));
//			bean.setSysAreaId(Long.valueOf(ConvertUtil.nullToSpace(item.get("sys_area_id"))));
//			bean.setSysAreaName(ConvertUtil.nullToSpace(item.get("sys_area_name")));
//			bean.setSysCardTypeId(Long.valueOf(ConvertUtil.nullToSpace(item.get("sys_card_type_id"))));
//			bean.setSysCardTypeName(ConvertUtil.nullToSpace(item.get("sys_card_type_name")));
//			bean.setSysCardNumber(ConvertUtil.nullToSpace(item.get("sys_card_number")));
//			list.add(bean);
//		}
//	}
//	
//	public SysCustomerDao getSysCustomerDao() {
//		return sysCustomerDao;
//	}
//
//	public void setSysCustomerDao(SysCustomerDao sysCustomerDao) {
//		this.sysCustomerDao = sysCustomerDao;
//	}
//
//	public void updateBycustomerid(Long sys_customer_id,
//			String SYS_CUSTOMER_NAME, Long SYS_AREA_ID, Long SYS_CARD_TYPE_ID,
//			String SYS_CARD_NUMBER) {
//		sysCustomerDao.updateBycustomerid(sys_customer_id, SYS_CUSTOMER_NAME, SYS_AREA_ID, SYS_CARD_TYPE_ID, SYS_CARD_NUMBER);
//	}
//
//	//alert 3-5
//	public void updateBycustomerid(Long sys_customer_id,
//			String SYS_CUSTOMER_NAME, Long SYS_AREA_ID) {
//		sysCustomerDao.updateBycustomerid(sys_customer_id, SYS_CUSTOMER_NAME, SYS_AREA_ID);
//	}
//	public void insert (String SYS_CUSTOMER_NAME,Long SYS_AREA_ID)
//	{
//		sysCustomerDao.insert(SYS_CUSTOMER_NAME, SYS_AREA_ID);
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
