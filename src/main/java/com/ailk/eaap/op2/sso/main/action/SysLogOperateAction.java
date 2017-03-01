package com.ailk.eaap.op2.sso.main.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 操作日志Action
 * @author zhaobl
 *
 */
public class SysLogOperateAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ISysLogOperateService sysLogOperateServ;
	private IPersonServ sysPersonServ;
	
	private List personList = new ArrayList();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	private String tenantId;
	
	/**
	 * 跳转到操作日志页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String cardNumber = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if("cardNumber".equals(cookie.getName())){
					cardNumber = cookie.getValue();
				}
			}
		}		
		Map mapPs =new HashMap();
		mapPs.put("username", cardNumber);
		mapPs.put("tenantId", tenantId);
		aSysPerson sysPerson = getSysPersonServ().getSysPerson(mapPs);
		String sysIdTypeId = sysPerson.getSysIdTypeId().toString();
		//1:超级管理员  2:管理员  3：操作员
		if(sysIdTypeId.equals("1")){
			List<Map> sysPersonList = getSysPersonServ().queryAll();
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else if(sysIdTypeId.equals("2")){
			SysDept sysDept = sysPerson.getSysDept();
			String deptId ="";
			if(sysDept != null)
				deptId = sysDept.getDeptId().toString();
			Map parmMap = new HashMap();
			parmMap.put("deptId", deptId);
			
			List<Map> sysPersonList = getSysPersonServ().queryAllByDeptId(parmMap);
			for(int i=0; i<sysPersonList.size(); i++){
				Map map = new HashMap();
				Map map1 = sysPersonList.get(i);
				if(map1.get("CARD_NUMBER") == null || map1.get("CARD_NUMBER").equals(""))
					continue;
				map.put("sysPersonId", map1.get("SYS_PERSON_ID"));
				map.put("cardNumber", map1.get("CARD_NUMBER"));
				personList.add(map);
			}
		}else{
			Map map = new HashMap();
			map.put("sysPersonId", sysPerson.getSysPersonId());
			map.put("cardNumber", sysPerson.getCardNumber());
			personList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示操作日志列表
	 * @param para
	 * @return
	 */
	public Map showGrid(Map para){
		
		List<Map> sysLogOperateList = new ArrayList<Map>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;  //开始行号
		Map hashMap = new HashMap();
		Map hashMap1 = new HashMap();
		String sysPersonId = getRequest().getParameter("cardNumber");//员工编号
		String minDate = getRequest().getParameter("minDate");//最小日期
		String maxDate = getRequest().getParameter("maxDate");//最大日期
		if(!StringUtil.isEmpty(sysPersonId) || !StringUtil.isEmpty(minDate) || !StringUtil.isEmpty(maxDate)){
			hashMap.put("sysPersonId", sysPersonId);
			hashMap.put("minDate", minDate);
			hashMap.put("maxDate", maxDate);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			
//			Map map = new HashMap();
//			map.put("CARD_NUMBER", 1);
//			map.put("NAME", 2);
//			map.put("MODEL_NAME", 3);
//			map.put("OP_SUM", 4);
//			map.put("CLASS_NAME", 5);
//			map.put("METHOD_NAME", 6);
//			map.put("OPERATE_TIME", 7);
//			map.put("PARAM_NAME", 8);
//			map.put("SYS_LOG_OPERATE_ID", 9);
//			map.put("SYS_PERSON_ID", 10);
//			map.put("FUNCTION_ID", 11);
//			sysLogOperateList.add(map);
			sysLogOperateList = sysLogOperateServ.queryAllSysLogOperate(hashMap);
			total = sysLogOperateServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysLogOperateList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			
//			Map map = new HashMap();
//			map.put("CARD_NUMBER", 1);
//			map.put("NAME", 2);
//			map.put("MODEL_NAME", 3);
//			map.put("OP_SUM", 4);
//			map.put("CLASS_NAME", 5);
//			map.put("METHOD_NAME", 6);
//			map.put("OPERATE_TIME", 7);
//			map.put("PARAM_NAME", 8);
//			map.put("SYS_LOG_OPERATE_ID", 9);
//			map.put("SYS_PERSON_ID", 10);
//			map.put("FUNCTION_ID", 11);
//			sysLogOperateList.add(map);
			sysLogOperateList = sysLogOperateServ.queryAllSysLogOperate(hashMap);
			total = sysLogOperateServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysLogOperateList));
		}
		return hashMap1;
	}



	public IPersonServ getSysPersonServ() {
		sysPersonServ = (IPersonServ)SpringBeanInvoker.getBean("personServ");
		return sysPersonServ;
	}

	public void setSysPersonServ(IPersonServ sysPersonServ) {
		this.sysPersonServ = sysPersonServ;
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}
	
	public List getPersonList() {
		return personList;
	}

	public void setPersonList(List personList) {
		this.personList = personList;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
