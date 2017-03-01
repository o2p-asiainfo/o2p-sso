package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map;


import com.ailk.eaap.op2.sso.main.dao.ISysCustomerDao;
import com.ailk.eaap.op2.sso.main.dao.ISysDeptDao;
import com.ailk.eaap.op2.sso.main.dao.SysDeptDao;
import com.ailk.eaap.op2.sso.main.model.DeptDataBean;
import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SvcDeptBean;
import com.ailk.eaap.op2.sso.main.model.SysCustomer;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;
/**
 * 部门管理服务
 * @author zhaobl
 *
 */

public class SysDeptServiceImpl implements ISysDeptService {
	private ISysDeptDao sysDeptDao;
	public ISysDeptDao getSysDeptDao() {
		return sysDeptDao;
	}
	public void setSysDeptDao(ISysDeptDao sysDeptDao){
		this.sysDeptDao = sysDeptDao;
	}
	
	public List<SysDept> queryAllSysDept(Map map) {
		List<SysDept> sysDeptList = sysDeptDao.queryAllSysDept(map);
		List<SysDept> sysDeptListNew = new ArrayList<SysDept>();
		for(int i=0; i<sysDeptList.size(); i++){
			SysDept sysDept = sysDeptList.get(i);
			Long parentDeptId =  sysDept.getParentDeptId();
			if(parentDeptId != null){
				SysDept parentDept = sysDeptDao.querySysDeptByDeptId(parentDeptId.toString());
				String parentDeptName = parentDept.getDeptName();
				if(parentDeptName != null){
					sysDept.setParentDeptName(parentDeptName);
				}
			}
			sysDeptListNew.add(sysDept);
		}
		return sysDeptListNew;
	}
	public int queryCount(Map map) {
		int count = sysDeptDao.queryCount(map);
		return count;
	}
	public List<SysDept> queryAll() {
		List<SysDept> sysDeptList = sysDeptDao.queryAll();
		return sysDeptList;
	}
	public void insertSysDept(Map map) {
		sysDeptDao.insertSysDept(map);
	}
	public int deleteSysDept(String deptId) {
		int flag = sysDeptDao.deleteSysDept(deptId);
		return flag;
	}
	public SysDept querySysDeptById(String deptId) {
		SysDept sysDept = sysDeptDao.querySysDeptByDeptId(deptId);
		return sysDept;
	}
	public void updateSysDept(Map map) {
		sysDeptDao.updateSysDept(map);
	}
	public boolean isDeptExist(Map map) {
		boolean flag = sysDeptDao.isDeptExist(map);
		return flag;
	}
	public boolean isUpdateDeptExist(Map map) {
		String deptId =  map.get("deptId").toString();
		String deptName = map.get("deptName").toString();
		SysDept sysDept = sysDeptDao.querySysDeptByDeptId(deptId);
		if(deptName.equals(sysDept.getDeptName())){
			return false;
		}else{
			return this.isDeptExist(map);
		}
	}

	
	
	
	
	
	
//	private SysDeptDao sysDeptDao;
	
//	/*private final static String SELECT_DEPT_LIST_SQL = "select a.dept_id,a.dept_name,a.parent_dept_id,a.sys_customer_id,a.sys_status_id," +
//			" c.sys_customer_name,b.sys_status_name,d.dept_name preDeptNm " +
//			" from xrb.SYS_DEPT a " +
//			" left join xrb.SYS_DEPT d on a.parent_dept_id = d.dept_id" +
//			" left join xrb.sys_status b on a.sys_status_id = b.sys_status_id" +
//			" left join xrb.SYS_CUSTOMER c on a.sys_customer_id = c.sys_customer_id" +
//			" where 1=1 ";*/
//	
//	private final static String SELECT_DEPT_LIST_SQL = "select a.dept_id,a.dept_name,a.parent_dept_id,a.sys_customer_id,a.sys_status_id," +
//	" c.sys_customer_name,b.sys_status_name,d.dept_name preDeptNm " +
//	" from SYS_DEPT a " +
//	" left join SYS_DEPT d on a.parent_dept_id = d.dept_id" +
//	" left join sys_status b on a.sys_status_id = b.sys_status_id" +
//	" left join SYS_CUSTOMER c on a.sys_customer_id = c.sys_customer_id" +
//	" where 1=1 ";
//	
//	public String doLoadSelect() 
//	{
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> customers = new ArrayList<KeyValueBean>();
//		List<KeyValueBean> status = new ArrayList<KeyValueBean>();		
//		customers=sysDeptDao.selectCustomer();
//		status=sysDeptDao.selectStatus();
//		temp=temp.append("{custormers:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(customers));
//		temp=temp.append("],status:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(status));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	// 增加数据域判断 获取可选取 公司信息的方法
//	public String doLoadSelect(Long cusId) 
//	{
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> customers = new ArrayList<KeyValueBean>();
//		List<KeyValueBean> status = new ArrayList<KeyValueBean>();		
//		customers=sysDeptDao.selectCustomer(cusId);
//		status=sysDeptDao.selectStatus();
//		temp=temp.append("{custormers:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(customers));
//		temp=temp.append("],status:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(status));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	public String doEditDept(SvcDeptBean bean) {
//		String result = "";
//		DeptDataBean editBean = new DeptDataBean();
//		editBean.setCustomerId(bean.getCompId());
//		editBean.setDeptId(bean.getEditDeptId());
//		editBean.setParentDeptId(bean.getPreDept());
//		editBean.setStatusId(bean.getDeptState());
//		editBean.setDeptName(bean.getDeptName());		
//		if("add".equals(bean.getEditType())){
//			result = sysDeptDao.insertDept(editBean);
//		}else if("edit".equals(bean.getEditType())){
//			result = sysDeptDao.updateDept(editBean);
//		}else{
//			
//		}
//		doSearchDeptList(bean);
//		return result;
//	}
//	
//	public List<SysDept> deptList(String customerId) 
//	{
//		return sysDeptDao.deptList(customerId);
//	}
//	
//	public String doloadPreDept(String customerId) {
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> depts = new ArrayList<KeyValueBean>();
//		depts=sysDeptDao.selectDept(customerId);
//		temp=temp.append("{depts:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(depts));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	public String doloadOtherDept(String customerId ,String deptId ,String sysPersonId) {
//		String jsonObj = "";
//		StringBuffer temp = new StringBuffer("");
//		List<KeyValueBean> depts = new ArrayList<KeyValueBean>();
//		depts=sysDeptDao.selectOtherDept(customerId ,deptId ,sysPersonId);
//		temp=temp.append("{depts:[");
//		temp=temp.append(ConvertUtil.convertValuleListToJsonObj(depts));
//		temp=temp.append("]}");
//		jsonObj=temp.toString();
//		return jsonObj;
//	}
//	
//	public void doSearchDeptList(SvcDeptBean bean) {
//		List<DeptDataBean> list = new ArrayList<DeptDataBean>();
//		String sql = buildSearchDeptListSql(bean);
//		Integer size = sysDeptDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
//			List<Map<String,Object>> pageResult = sysDeptDao.getPageResult(pageSql);
//			convertDeptListResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}
//	//增加 数据域判断 获取部门数据方法 3-16
//	public void doSearchDeptList(SvcDeptBean bean,String idType,String cusName) {
//		List<DeptDataBean> list = new ArrayList<DeptDataBean>();
//		String sql = buildSearchDeptListSql(bean,idType,cusName);
//		Integer size = sysDeptDao.getResultSize(sql);
//		if(size > 0){
//			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
//			List<Map<String,Object>> pageResult = sysDeptDao.getPageResult(pageSql);
//			convertDeptListResult(pageResult, list);
//			bean.getPageBean().setTotalRecord(size.toString());
//			String totalPage = SqlUtil.getPageSize(size, Integer.parseInt(bean.getPageBean().getPageCount())).toString();
//			bean.getPageBean().setTotalPage(totalPage);
//		}
//		bean.setSearchResult(list);
//	}
//	public String doDeleteDept(SvcDeptBean bean) {		
//		String result = sysDeptDao.deleteDepts(bean.getDeptId());
//		if(result.length() == 5){
//			bean.setCheckedId("");
//		}
//		doSearchDeptList(bean);
//		return result;
//	}
//	
//	public DeptDataBean doSearchEditDept(String deptId) {
//		List<DeptDataBean> list = new ArrayList<DeptDataBean>();
//		DeptDataBean result = new DeptDataBean();
//		String sql = SELECT_DEPT_LIST_SQL + " and a.dept_id = " + deptId;
//		List<Map<String,Object>> pageResult = sysDeptDao.getPageResult(sql);
//		if(pageResult != null && pageResult.size() > 0){
//			convertDeptListResult(pageResult, list);
//			result = list.get(0);
//		}		
//		return result;
//	}
//	
//	private String buildSearchDeptListSql(SvcDeptBean bean){
//		StringBuffer buffer = new StringBuffer(SELECT_DEPT_LIST_SQL);
//		if(!ConvertUtil.isEmpty(bean.getCompId())){
//			buffer.append(" and a.sys_customer_id = ").append(bean.getCompId());
//		}
//		if(!ConvertUtil.isEmpty(bean.getDeptName())){
//			buffer.append(" and a.dept_name like '%").append(bean.getDeptName()).append("%'");
//		}
//		if(!ConvertUtil.isEmpty(bean.getDeptState())){
//			buffer.append(" and a.sys_status_id = ").append(bean.getDeptState());
//		}
//		if(!ConvertUtil.isEmpty(bean.getPreDept())){
//			buffer.append(" and a.parent_dept_id = ").append(bean.getPreDept());
//		}
//		buffer.append(" order by a.dept_id");
//		return buffer.toString();
//	}
//	//3-16 增加 数据域判断方法
//	private String buildSearchDeptListSql(SvcDeptBean bean,String idType,String cusName){
//		StringBuffer buffer = new StringBuffer(SELECT_DEPT_LIST_SQL);
//		if((idType.equals("1"))||(idType.equals("2") && cusName.equals("电信集团"))){
//			buffer.append(" and 1=1 ");
//		}
//		if(idType.equals("2") && !cusName.equals("电信集团")){
//			buffer.append(" and c.sys_customer_name = '").append(cusName).append("'");
//		}
//		if(!ConvertUtil.isEmpty(bean.getCompId())){
//			buffer.append(" and a.sys_customer_id = ").append(bean.getCompId());
//		}
//		if(!ConvertUtil.isEmpty(bean.getDeptName())){
//			buffer.append(" and a.dept_name like '%").append(bean.getDeptName()).append("%'");
//		}
//		if(!ConvertUtil.isEmpty(bean.getDeptState())){
//			buffer.append(" and a.sys_status_id = ").append(bean.getDeptState());
//		}
//		if(!ConvertUtil.isEmpty(bean.getPreDept())){
//			buffer.append(" and a.parent_dept_id = ").append(bean.getPreDept());
//		}
//		buffer.append(" order by a.dept_id");
//		return buffer.toString();
//	}
//	private void convertDeptListResult(List<Map<String,Object>> result,List<DeptDataBean> list){
//		for(Map<String,Object> item : result){
//			DeptDataBean bean = new DeptDataBean();
//			bean.setCustomerId(ConvertUtil.nullToSpace(item.get("sys_customer_id")));
//			bean.setDeptId(ConvertUtil.nullToSpace(item.get("dept_id")));
//			bean.setParentDeptId(ConvertUtil.nullToSpace(item.get("parent_dept_id")));
//			bean.setStatusId(ConvertUtil.nullToSpace(item.get("sys_status_id")));	
//			bean.setCustomerNm(ConvertUtil.nullToSpace(item.get("sys_customer_name")));
//			bean.setDeptName(ConvertUtil.nullToSpace(item.get("dept_name")));
//			bean.setParentDeptNm(ConvertUtil.nullToSpace(item.get("preDeptNm")));
//			bean.setStatusNm(ConvertUtil.nullToSpace(item.get("sys_status_name")));
//			list.add(bean);
//		}
//	}
//	
//	public SysDeptDao getSysDeptDao() {
//		return sysDeptDao;
//	}
//
//	public void setSysDeptDao(SysDeptDao sysDeptDao) {
//		this.sysDeptDao = sysDeptDao;
//	}

}
