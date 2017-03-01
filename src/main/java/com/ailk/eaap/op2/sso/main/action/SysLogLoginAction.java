package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.aSysPerson;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysLogLoginService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 登录日志action
 * @author zhaobl
 *
 */
public class SysLogLoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ISysLogLoginService sysLogLoginServ;
	private IPersonServ sysPersonServ;
	
	private List personList = new ArrayList();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	private String tenantId;
	
	private ISysLogOperateService sysLogOperateServ;
	/**
	 * 跳转到登录日志页面
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
	 * 查询展示登录日志列表
	 * @param para
	 * @return
	 */
	public Map showGrid(Map para){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("登录日志");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysLogLoginAction");
		sysLogOperate.setMethodName("showGrid");
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		List<Map> sysLogLoginList = new ArrayList<Map>();
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
			sysLogLoginList = sysLogLoginServ.queryAllSysLogLogin(hashMap);
			total = sysLogLoginServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysLogLoginList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysLogLoginList = sysLogLoginServ.queryAllSysLogLogin(hashMap);
			total = sysLogLoginServ.queryCount(hashMap);
			hashMap1.put("total", total);
			hashMap1.put("dataList", pagination.convertJson(sysLogLoginList));
		}
		return hashMap1;
	}


	public ISysLogLoginService getSysLogLoginServ() {
		sysLogLoginServ = (ISysLogLoginService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogLoginServ");
		return sysLogLoginServ;
	}

	public void setSysLogLoginServ(ISysLogLoginService sysLogLoginServ) {
		this.sysLogLoginServ = sysLogLoginServ;
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
