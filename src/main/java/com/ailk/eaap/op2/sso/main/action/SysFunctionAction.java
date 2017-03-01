package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysBusinessSystem;
import com.ailk.eaap.op2.sso.main.model.SysFunction;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.service.ISysBusinessSystemService;
import com.ailk.eaap.op2.sso.main.service.ISysFunctionService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 功能点管理
 * @author zhaobl
 *
 */

public class SysFunctionAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ISysFunctionService sysFunctionServ;
	private ISysBusinessSystemService sysBusinessSystemServ;
	
	private List stateList = new ArrayList();
	private List sysBusinessList = new ArrayList();
	private List parentFunctionList = new ArrayList();
	private List displayModeList = new ArrayList();
	private SysFunction sysFunction = new SysFunction();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ;
	/**
	 * 跳转到功能点页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		List<SysFunction>  functionList = getSysFunctionServ().queryAll();
		for(int i=0; i<functionList.size(); i++){
			Map map = new HashMap();
			SysFunction sysFunction = functionList.get(i);
			if(sysFunction.getFunctionName() == null || sysFunction.getFunctionName().equals(""))
				continue;
			map.put("parentFunctionId", sysFunction.getFunctionId());
			map.put("parentFunctionName", sysFunction.getFunctionName());
			parentFunctionList.add(map);
		}
		
		List<SysBusinessSystem>  sysBusinessSystemList = getSysBusinessSystemServ().querySysBusinessSystem();
		for(int i=0; i<sysBusinessSystemList.size(); i++){
			Map map = new HashMap();
			SysBusinessSystem sysBusinessSystem = sysBusinessSystemList.get(i);
			if(sysBusinessSystem.getBusinessSystemName() == null || sysBusinessSystem.getBusinessSystemName().equals(""))
				continue;
			map.put("sysBusinessId", sysBusinessSystem.getBusinessSystemId());
			map.put("sysBusinessName", sysBusinessSystem.getBusinessSystemName());
			sysBusinessList.add(map);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询展示功能点列表
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
		sysLogOperate.setModelName("功能点管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysFunctionAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<SysFunction> sysFunctionList = new ArrayList<SysFunction>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		String functionName = getRequest().getParameter("sysFunctionName");//功能点名称
		if(functionName != null)
			functionName = functionName.trim();
		String parentFunctionId = getRequest().getParameter("parentFunctionName");//父功能点ID
		String businessSystemId = getRequest().getParameter("sysBusinessName");//所属业务系统ID
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "functionName: "+functionName +",parentFunctionId: "+parentFunctionId +",businessSystemId: "+businessSystemId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		if(!StringUtil.isEmpty(functionName) || !StringUtil.isEmpty(parentFunctionId) || !StringUtil.isEmpty(businessSystemId) || !StringUtil.isEmpty(stateId)){
			hashMap.put("functionName", functionName);
			hashMap.put("parentFunctionId", parentFunctionId);
			hashMap.put("businessSystemId", businessSystemId);
			hashMap.put("stateId", stateId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysFunctionList = sysFunctionServ.queryAllSysFunction(hashMap);
			total = sysFunctionServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysFunctionList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysFunctionList = sysFunctionServ.queryAllSysFunction(hashMap);
			total = sysFunctionServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysFunctionList));
		}
		return hashMap;
	}
	/**
	 * 跳转到添加功能点页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		Map map1 = new HashMap();
		map1.put("displayModeId", "0");
		map1.put("displayModeName", "框架内显示");
		Map map2 = new HashMap();
		map2.put("displayModeId", "1");
		map2.put("displayModeName", "打开新窗口");
		displayModeList.add(map1);
		displayModeList.add(map2);
		
		List<SysFunction>  functionList = getSysFunctionServ().queryAll();
		for(int i=0; i<functionList.size(); i++){
			Map map = new HashMap();
			SysFunction sysFunction = functionList.get(i);
			if(sysFunction.getFunctionName() == null || sysFunction.getFunctionName().equals(""))
				continue;
			map.put("parentFunctionId", sysFunction.getFunctionId());
			map.put("parentFunctionName", sysFunction.getFunctionName());
			parentFunctionList.add(map);
		}
		
		List<SysBusinessSystem>  sysBusinessSystemList = getSysBusinessSystemServ().querySysBusinessSystem();
		for(int i=0; i<sysBusinessSystemList.size(); i++){
			Map map = new HashMap();
			SysBusinessSystem sysBusinessSystem = sysBusinessSystemList.get(i);
			if(sysBusinessSystem.getBusinessSystemName() == null || sysBusinessSystem.getBusinessSystemName().equals(""))
				continue;
			map.put("sysBusinessId", sysBusinessSystem.getBusinessSystemId());
			map.put("sysBusinessName", sysBusinessSystem.getBusinessSystemName());
			sysBusinessList.add(map);
		}
		return SUCCESS;
	}
	/**
	 * 判断新添加功能点名称是否存在， 1存在
	 */
	public void isFunctionExist(){
		Map map = new HashMap();
		String functionName = getRequest().getParameter("functionName");
		if(functionName != null)
			functionName = functionName.trim();
		map.put("functionName", functionName);
		boolean flag = getSysFunctionServ().isFunctionExist(map);
		String stringJson;
		if(flag){
			stringJson ="{\"result\":\"1\"}";
		}else{
			stringJson ="{\"result\":\"0\"}";
		}	
		try {
			writeString(stringJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 添加功能点
	 * @return
	 */
	public String addSysFunction(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("功能点管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysFunctionAction");
		sysLogOperate.setMethodName("addSysFunction");
		
		Map hashMap = new HashMap();
		String functionName = getRequest().getParameter("sysFunctionName");//功能点名称
		if(functionName != null)
			functionName = functionName.trim();
		String parentFunctionId = getRequest().getParameter("parentFunctionName");//父功能点ID
		String businessSystemId = getRequest().getParameter("sysBusinessName");//所属业务系统ID
		String url = getRequest().getParameter("url");//功能点URL
		if(url != null)
			url = url.trim();
		String displayMode = getRequest().getParameter("displayMode");//窗口显示模式
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "functionName: "+functionName +",parentFunctionId: "+parentFunctionId +",url: "+url +",displayMode: "+displayMode +",businessSystemId: "+businessSystemId +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("functionName", functionName);
		hashMap.put("parentFunctionId", parentFunctionId);
		hashMap.put("businessSystemId", businessSystemId);
		hashMap.put("url", url);
		hashMap.put("displayMode", displayMode);
		hashMap.put("stateId", stateId);
		getSysFunctionServ().insertSysFunction(hashMap);
		return SUCCESS;
	}
	/**
	 * 删除选中功能点
	 */
	public void deleteSysFunction(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("功能点管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysFunctionAction");
		sysLogOperate.setMethodName("deleteSysFunction");
		
		String functionId = getRequest().getParameter("functionId");
		
		//操作日志参数
		String param = "functionId: "+functionId;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		String stringJson;
		if(functionId != null && !functionId.equals("")){
			int flag = getSysFunctionServ().deleteSysFunction(functionId);
			if(flag == 0){
				stringJson ="{\"result\":\"0\"}";
			}else{
				stringJson ="{\"result\":\"1\"}";
			}	
			try {
				writeString(stringJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 跳转到修改功能点页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preUpdate(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", "正常");
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", "暂停");
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", "作废");
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		
		Map map1 = new HashMap();
		map1.put("displayModeId", "0");
		map1.put("displayModeName", "框架内显示");
		Map map2 = new HashMap();
		map2.put("displayModeId", "1");
		map2.put("displayModeName", "打开新窗口");
		displayModeList.add(map1);
		displayModeList.add(map2);
		
		List<SysFunction>  functionList = getSysFunctionServ().queryAll();
		for(int i=0; i<functionList.size(); i++){
			Map map = new HashMap();
			SysFunction sysFunction = functionList.get(i);
			if(sysFunction.getFunctionName() == null || sysFunction.getFunctionName().equals(""))
				continue;
			map.put("parentFunctionId", sysFunction.getFunctionId());
			map.put("parentFunctionName", sysFunction.getFunctionName());
			parentFunctionList.add(map);
		}
		
		List<SysBusinessSystem>  sysBusinessSystemList = getSysBusinessSystemServ().querySysBusinessSystem();
		for(int i=0; i<sysBusinessSystemList.size(); i++){
			Map map = new HashMap();
			SysBusinessSystem sysBusinessSystem = sysBusinessSystemList.get(i);
			if(sysBusinessSystem.getBusinessSystemName() == null || sysBusinessSystem.getBusinessSystemName().equals(""))
				continue;
			map.put("sysBusinessId", sysBusinessSystem.getBusinessSystemId());
			map.put("sysBusinessName", sysBusinessSystem.getBusinessSystemName());
			sysBusinessList.add(map);
		}
		
		String functionId = getRequest().getParameter("functionId");
		sysFunction = getSysFunctionServ().querySysFunctionById(functionId);
		return SUCCESS;
	}
	/**
	 * 判断新修改功能点名称是否存在， 1存在
	 */
	public void isUpdateFunctionExist(){
		Map map = new HashMap();
		String functionId = getRequest().getParameter("functionId");
		String functionName = getRequest().getParameter("functionName");
		if(functionName != null)
			functionName = functionName.trim();
		map.put("functionId", functionId);
		map.put("functionName", functionName);
		boolean flag = getSysFunctionServ().isUpdateFunctionExist(map);
		String stringJson;
		if(flag){
			stringJson ="{\"result\":\"1\"}";
		}else{
			stringJson ="{\"result\":\"0\"}";
		}	
		try {
			writeString(stringJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 修改选中功能点信息
	 * @return
	 */
	public String updateSysFunction(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("功能点管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysFunctionAction");
		sysLogOperate.setMethodName("updateSysFunction");
		
		Map hashMap = new HashMap();
		String functionId = getRequest().getParameter("functionId");
		String functionName = getRequest().getParameter("sysFunctionName");//功能点名称
		if(functionName != null)
			functionName = functionName.trim();
		String parentFunctionId = getRequest().getParameter("parentFunctionName");//父功能点ID
		String businessSystemId = getRequest().getParameter("sysBusinessName");//所属业务系统ID
		String url = getRequest().getParameter("url");//功能点URL
		if(url != null)
			url = url.trim();
		String displayMode = getRequest().getParameter("displayMode");//窗口显示模式
		String stateId = getRequest().getParameter("state");//状态ID
		String shownum = getRequest().getParameter("shownum");//排序号
		
		//操作日志参数
		String param = "functionName: "+functionName +",parentFunctionId: "+parentFunctionId +",url: "+url +",displayMode: "+displayMode +",businessSystemId: "+businessSystemId +",shownum: "+shownum +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("functionId", functionId);
		hashMap.put("functionName", functionName);
		hashMap.put("parentFunctionId", parentFunctionId);
		hashMap.put("businessSystemId", businessSystemId);
		hashMap.put("url", url);
		hashMap.put("displayMode", displayMode);
		hashMap.put("shownum", shownum);
		hashMap.put("stateId", stateId);
		if(functionId != null && !functionId.equals(""))
			getSysFunctionServ().updateSysFunction(hashMap);
		return SUCCESS;
	}


	public List getStateList() {
		return stateList;
	}

	public void setStateList(List stateList) {
		this.stateList = stateList;
	}
	

	public ISysFunctionService getSysFunctionServ() {
		sysFunctionServ = (ISysFunctionService)SpringBeanInvoker.getBean("eaap-op2-sso-sysFunctionServ");
		return sysFunctionServ;
	}

	public void setSysFunctionServ(ISysFunctionService sysFunctionServ) {
		this.sysFunctionServ = sysFunctionServ;
	}

	public ISysBusinessSystemService getSysBusinessSystemServ() {
		sysBusinessSystemServ = (ISysBusinessSystemService)SpringBeanInvoker.getBean("eaap-op2-sso-sysBusinessSystemServ");
		return sysBusinessSystemServ;
	}

	public void setSysBusinessSystemServ(
			ISysBusinessSystemService sysBusinessSystemServ) {
		this.sysBusinessSystemServ = sysBusinessSystemServ;
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}

	public List getSysBusinessList() {
		return sysBusinessList;
	}

	public void setSysBusinessList(List sysBusinessList) {
		this.sysBusinessList = sysBusinessList;
	}

	public List getParentFunctionList() {
		return parentFunctionList;
	}

	public void setParentFunctionList(List parentFunctionList) {
		this.parentFunctionList = parentFunctionList;
	}

	public List getDisplayModeList() {
		return displayModeList;
	}

	public void setDisplayModeList(List displayModeList) {
		this.displayModeList = displayModeList;
	}

	public SysFunction getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
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

}
