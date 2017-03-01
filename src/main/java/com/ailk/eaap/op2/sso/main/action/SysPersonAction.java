package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.model.SysArea;
import com.ailk.eaap.op2.sso.main.model.SysDept;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.service.ISysAreaService;
import com.ailk.eaap.op2.sso.main.service.ISysDeptService;
import com.ailk.eaap.op2.sso.main.service.ISysLogOperateService;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.linkage.rainbow.ui.paginaction.Pagination;
import com.linkage.rainbow.ui.struts.BaseAction;
/**
 * 员工信息管理
 * @author zhaobl
 *
 */
public class SysPersonAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IPersonServ sysPersonServ ;
	private ISysDeptService sysDeptServ;
	private ISysAreaService sysAreaServ ;
	
	private List stateList = new ArrayList();
	private List deptList = new ArrayList();
	private List sysIdTypeList = new ArrayList();
	private List sysUserTypeList = new ArrayList();
	private List sysCardTypeList = new ArrayList();
	private List sysAreaList = new ArrayList();
	private Map sysPersonMap = new HashMap();
	/** 分页  **/
	private int rows; //每页显示行数
	private int page; //第几页
	private Pagination pagination = new Pagination();
	private int total;  //总数量
	
	private ISysLogOperateService sysLogOperateServ; 
	/**
	 * 跳转到员工管理页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preShow(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", this.getText("eaap.op2.sso.person.inUse"));		//正常
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", this.getText("eaap.op2.sso.person.pause"));	//暂停
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", this.getText("eaap.op2.sso.person.repealed"));	//作废
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		//身份
		Map hashMap4 = new HashMap();
		hashMap4.put("sysIdTypeId", "1");
		hashMap4.put("sysIdTypeName", this.getText("eaap.op2.sso.person.superAdministrator"));		//超级管理员
		Map hashMap5 = new HashMap();
		hashMap5.put("sysIdTypeId", "2");
		hashMap5.put("sysIdTypeName", this.getText("eaap.op2.sso.person.administrator"));				//管理员
		Map hashMap6 = new HashMap();
		hashMap6.put("sysIdTypeId", "3");
		hashMap6.put("sysIdTypeName", this.getText("eaap.op2.sso.person.operator"));						//操作员
		sysIdTypeList.add(hashMap4);
		sysIdTypeList.add(hashMap5);
		sysIdTypeList.add(hashMap6);
		//用户类型
		Map hashMap7 = new HashMap();
		hashMap7.put("sysUserTypeId", "1");
		hashMap7.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.internalUser"));		//内部用户
		Map hashMap8 = new HashMap();
		hashMap8.put("sysUserTypeId", "2");
		hashMap8.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.externalUsers"));	//外部用户
		sysUserTypeList.add(hashMap7);
		sysUserTypeList.add(hashMap8);
		//部门
		List<SysDept> sysDeptList = getSysDeptServ().queryAll();
		for(int i=0; i<sysDeptList.size(); i++){
			Map map = new HashMap();
			SysDept sysDept = sysDeptList.get(i);
			if(sysDept.getDeptName() == null || sysDept.getDeptName().equals(""))
				continue;
			map.put("deptId", sysDept.getDeptId());
			map.put("sysDeptName", sysDept.getDeptName());
			deptList.add(map);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询展示员工表
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
		sysLogOperate.setModelName("员工管理");
		sysLogOperate.setOpSum("query");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonAction");
		sysLogOperate.setMethodName("showGrid");
		
		List<Map> sysPersonList = new ArrayList<Map>();
		rows = pagination.getRows();
		page = pagination.getPage();
		int startRow = (page - 1) * rows + 1;;  //开始行号
		Map hashMap = new HashMap();
		String cardNumber = getRequest().getParameter("cardNumber");//工号
		if(cardNumber != null)
			cardNumber = cardNumber.trim();
		String name = getRequest().getParameter("name");//姓名
		if(name != null)
			name = name.trim();
		String mobile = getRequest().getParameter("mobile");//手机
		if(mobile != null)
			mobile = mobile.trim();
		String deptId = getRequest().getParameter("sysDeptName");//部门ID
		String sysIdTypeId = getRequest().getParameter("sysIdTypeName");//身份ID
		String sysUserType = getRequest().getParameter("sysUserTypeName");//用户类型
		String stateId = getRequest().getParameter("state");//状态ID
		
		//操作日志参数
		String param = "cardNumber: "+cardNumber +",name: "+name +",mobile: "+mobile +",deptId: "+deptId +",sysIdTypeId: "+sysIdTypeId +",sysUserType: "+sysUserType +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);

		Tenant tenant = (Tenant) session.getAttribute("Tenant");
		hashMap.put("tenantId", tenant.getTenantId());
		if(!StringUtil.isEmpty(cardNumber) || !StringUtil.isEmpty(name) || !StringUtil.isEmpty(mobile) || !StringUtil.isEmpty(deptId)
				|| !StringUtil.isEmpty(sysIdTypeId) || !StringUtil.isEmpty(sysUserType) || !StringUtil.isEmpty(stateId)){
			hashMap.put("cardNumber", cardNumber);
			hashMap.put("name", name);
			hashMap.put("mobile", mobile);
			hashMap.put("deptId", deptId);
			hashMap.put("sysIdTypeId", sysIdTypeId);
			hashMap.put("sysUserType", sysUserType);
			hashMap.put("stateId", stateId);
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysPersonList = sysPersonServ.queryAllSysPerson(hashMap);
			total = sysPersonServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysPersonList));
		}else{
			//MYSQL 
			hashMap.put("pro_mysql", startRow - 1);
			hashMap.put("page_record", rows);
			
			hashMap.put("startRow", startRow);
			hashMap.put("rows", rows);
			sysPersonList = sysPersonServ.queryAllSysPerson(hashMap);
			total = sysPersonServ.queryCount(hashMap);
			hashMap.put("total", total);
			hashMap.put("dataList", pagination.convertJson(sysPersonList));
		}
		return hashMap;
	}
	/**
	 * 跳转到添加员工页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preAdd(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateName", this.getText("eaap.op2.sso.person.inUse"));		//正常
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", this.getText("eaap.op2.sso.person.pause"));	//暂停
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", this.getText("eaap.op2.sso.person.repealed"));	//作废
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		//身份
		Map hashMap4 = new HashMap();
		hashMap4.put("sysIdTypeId", "1");
		hashMap4.put("sysIdTypeName", this.getText("eaap.op2.sso.person.superAdministrator"));		//超级管理员
		Map hashMap5 = new HashMap();
		hashMap5.put("sysIdTypeId", "2");
		hashMap5.put("sysIdTypeName", this.getText("eaap.op2.sso.person.administrator"));				//管理员
		Map hashMap6 = new HashMap();
		hashMap6.put("sysIdTypeId", "3");
		hashMap6.put("sysIdTypeName", this.getText("eaap.op2.sso.person.operator"));						//操作员
		sysIdTypeList.add(hashMap4);
		sysIdTypeList.add(hashMap5);
		sysIdTypeList.add(hashMap6);
		//用户类型
		Map hashMap7 = new HashMap();
		hashMap7.put("sysUserTypeId", "1");
		hashMap7.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.internalUser"));		//内部用户
		Map hashMap8 = new HashMap();
		hashMap8.put("sysUserTypeId", "2");
		hashMap8.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.externalUsers"));	//外部用户
		sysUserTypeList.add(hashMap7);
		sysUserTypeList.add(hashMap8);
		//证件类型
		Map hashMap9 = new HashMap();
		hashMap9.put("sysCardTypeId", "1");
		hashMap9.put("sysCardTypeName", this.getText("eaap.op2.sso.person.IDCard"));		//身份证
		Map hashMap10 = new HashMap();
		hashMap10.put("sysCardTypeId", "2");
		hashMap10.put("sysCardTypeName", this.getText("eaap.op2.sso.person.workPermit"));		//工作证
		sysCardTypeList.add(hashMap9);
		sysCardTypeList.add(hashMap10);
		//区域
		List<SysArea>  areaList = getSysAreaServ().queryAll();
		for(int i=0; i<areaList.size(); i++){
			Map map = new HashMap();
			SysArea sysArea = areaList.get(i);
			if(sysArea.getSysAreaName() == null || sysArea.getSysAreaName().equals(""))
				continue;
			map.put("sysAreaId", sysArea.getSysAreaId());
			map.put("sysAreaName", sysArea.getSysAreaName());
			sysAreaList.add(map);
		}
		//部门
		List<SysDept> sysDeptList = getSysDeptServ().queryAll();
		for(int i=0; i<sysDeptList.size(); i++){
			Map map = new HashMap();
			SysDept sysDept = sysDeptList.get(i);
			if(sysDept.getDeptName() == null || sysDept.getDeptName().equals(""))
				continue;
			map.put("deptId", sysDept.getDeptId());
			map.put("sysDeptName", sysDept.getDeptName());
			deptList.add(map);
		}
		
		return SUCCESS;
	}
	/**
	 * 判断新添加工号名称是否存在， 1存在
	 */
	public void isPersonExist(){
		Map map = new HashMap();
		String cardNumber = getRequest().getParameter("cardNumber");
		if(cardNumber != null)
			cardNumber = cardNumber.trim();
		map.put("cardNumber", cardNumber);
		boolean flag = getSysPersonServ().isPersonExist(map);
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
	 * 添加员工
	 * @return
	 */
	public String addSysPerson(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工管理");
		sysLogOperate.setOpSum("add");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonAction");
		sysLogOperate.setMethodName("addSysPerson");
		
		Map hashMap = new HashMap();
		String cardNumber = getRequest().getParameter("cardNumber");//工号
		if(cardNumber != null)
			cardNumber = cardNumber.trim();
		String name = getRequest().getParameter("name");//姓名
		if(name != null)
			name = name.trim();
		String mobile = getRequest().getParameter("mobile");//手机
		if(mobile != null)
			mobile = mobile.trim();
		String email = getRequest().getParameter("email");//邮箱
		if(email != null)
			email = email.trim();
		String deptId = getRequest().getParameter("sysDeptName");//部门ID
		String sysIdTypeId = getRequest().getParameter("sysIdTypeName");//身份ID
		String sysUserType = getRequest().getParameter("sysUserTypeName");//用户类型
		String stateId = getRequest().getParameter("state");//状态ID
		String sysCardTypeId = getRequest().getParameter("sysCardTypeName"); //证件类型ID
		String sysAreaId = getRequest().getParameter("sysAreaName");//区域ID
		
		//操作日志参数
		UserRoleInfo userRoleInfo = (UserRoleInfo) request.getSession().getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
		String param = "cardNumber: "+cardNumber +",name: "+name +",email: "+email +",sysCardTypeId: "+sysCardTypeId +",sysAreaId: "+sysAreaId +",mobile: "+mobile +",deptId: "+deptId +",sysIdTypeId: "+sysIdTypeId +",sysUserType: "+sysUserType +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		sysLogOperate.setTenantId(userRoleInfo.getTenantId());
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("cardNumber", cardNumber);
		hashMap.put("name", name);
		hashMap.put("mobile", mobile);
		hashMap.put("deptId", deptId);
		hashMap.put("sysIdTypeId", sysIdTypeId);
		hashMap.put("sysUserType", sysUserType);
		hashMap.put("stateId", stateId);
		hashMap.put("email", email);
		hashMap.put("sysCardTypeId", sysCardTypeId);
		hashMap.put("sysAreaId", sysAreaId);
		hashMap.put("tenantId", userRoleInfo.getTenantId());
		getSysPersonServ().insertSysPerson(hashMap);
		return SUCCESS;
	}
	/**
	 * 删除选中员工
	 */
	public void deleteSysPerson(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工管理");
		sysLogOperate.setOpSum("delete");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonAction");
		sysLogOperate.setMethodName("deleteSysPerson");
		
		String sysPersonId = getRequest().getParameter("sysPersonId");
		
		//操作日志参数
		String param = "sysPersonId: "+sysPersonId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		String stringJson;
		if(sysPersonId != null && !sysPersonId.equals("")){
			int flag = getSysPersonServ().deleteSysPerson(sysPersonId);
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
	 * 跳转到修改员工页面
	 * 页面加载前下拉框等的初始化
	 * @return
	 */
	public String preUpdate(){
		Map hashMap1 = new HashMap();
		hashMap1.put("stateId", "1");
		hashMap1.put("stateName", this.getText("eaap.op2.sso.person.inUse"));		//正常
		Map hashMap2 = new HashMap();
		hashMap2.put("stateId", "2");
		hashMap2.put("stateName", this.getText("eaap.op2.sso.person.pause"));	//暂停
		Map hashMap3 = new HashMap();
		hashMap3.put("stateId", "3");
		hashMap3.put("stateName", this.getText("eaap.op2.sso.person.repealed"));	//作废
		stateList.add(hashMap1);
		stateList.add(hashMap2);
		stateList.add(hashMap3);
		//身份
		Map hashMap4 = new HashMap();
		hashMap4.put("sysIdTypeId", "1");
		hashMap4.put("sysIdTypeName", this.getText("eaap.op2.sso.person.superAdministrator"));		//超级管理员
		Map hashMap5 = new HashMap();
		hashMap5.put("sysIdTypeId", "2");
		hashMap5.put("sysIdTypeName", this.getText("eaap.op2.sso.person.administrator"));				//管理员
		Map hashMap6 = new HashMap();
		hashMap6.put("sysIdTypeId", "3");
		hashMap6.put("sysIdTypeName", this.getText("eaap.op2.sso.person.operator"));						//操作员
		sysIdTypeList.add(hashMap4);
		sysIdTypeList.add(hashMap5);
		sysIdTypeList.add(hashMap6);
		//用户类型
		Map hashMap7 = new HashMap();
		hashMap7.put("sysUserTypeId", "1");
		hashMap7.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.internalUser"));		//内部用户
		Map hashMap8 = new HashMap();
		hashMap8.put("sysUserTypeId", "2");
		hashMap8.put("sysUserTypeName",  this.getText("eaap.op2.sso.person.externalUsers"));	//外部用户
		sysUserTypeList.add(hashMap7);
		sysUserTypeList.add(hashMap8);
		//证件类型
		Map hashMap9 = new HashMap();
		hashMap9.put("sysCardTypeId", "1");
		hashMap9.put("sysCardTypeName", this.getText("eaap.op2.sso.person.IDCard"));		//身份证
		Map hashMap10 = new HashMap();
		hashMap10.put("sysCardTypeId", "2");
		hashMap10.put("sysCardTypeName", this.getText("eaap.op2.sso.person.workPermit"));		//工作证
		sysCardTypeList.add(hashMap9);
		sysCardTypeList.add(hashMap10);
		//区域
		List<SysArea>  areaList = getSysAreaServ().queryAll();
		for(int i=0; i<areaList.size(); i++){
			Map map = new HashMap();
			SysArea sysArea = areaList.get(i);
			if(sysArea.getSysAreaName() == null || sysArea.getSysAreaName().equals(""))
				continue;
			map.put("sysAreaId", sysArea.getSysAreaId());
			map.put("sysAreaName", sysArea.getSysAreaName());
			sysAreaList.add(map);
		}
		//部门
		List<SysDept> sysDeptList = getSysDeptServ().queryAll();
		for(int i=0; i<sysDeptList.size(); i++){
			Map map = new HashMap();
			SysDept sysDept = sysDeptList.get(i);
			if(sysDept.getDeptName() == null || sysDept.getDeptName().equals(""))
				continue;
			map.put("deptId", sysDept.getDeptId());
			map.put("sysDeptName", sysDept.getDeptName());
			deptList.add(map);
		}
		
		String sysPersonId = getRequest().getParameter("sysPersonId");
		sysPersonMap = getSysPersonServ().querySysPersonById(sysPersonId);
		return SUCCESS;
	}
	/**
	 * 判断新修改客户名称是否存在， 1存在
	 */
	public void isUpdatePersonExist(){
		Map map = new HashMap();
		String sysPersonId = getRequest().getParameter("sysPersonId");
		String cardNumber = getRequest().getParameter("cardNumber");
		if(cardNumber != null)
			cardNumber = cardNumber.trim();
		map.put("sysPersonId", sysPersonId);
		map.put("cardNumber", cardNumber);
		boolean flag = getSysPersonServ().isUpdatePersonExist(map);
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
	 * 修改选中员工信息
	 * @return
	 */
	public String updateSysPerson(){
		//操作日志
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("cardNumber");
		SysLogOperate sysLogOperate = new SysLogOperate();
		sysLogOperate.setSysPersonId(userInfo.getId());
		sysLogOperate.setFunctionId(request.getParameter("function_id"));
		sysLogOperate.setModelName("员工管理");
		sysLogOperate.setOpSum("update");
		sysLogOperate.setClassName("com.ailk.eaap.op2.sso.main.action.SysPersonAction");
		sysLogOperate.setMethodName("updateSysPerson");
		
		Map hashMap = new HashMap();
		String sysPersonId = getRequest().getParameter("sysPersonId");
		String cardNumber = getRequest().getParameter("cardNumber");//工号
		if(cardNumber != null)
			cardNumber = cardNumber.trim();
		String name = getRequest().getParameter("name");//姓名
		if(name != null)
			name = name.trim();
		String mobile = getRequest().getParameter("mobile");//手机
		if(mobile != null)
			mobile = mobile.trim();
		String email = getRequest().getParameter("email");//邮箱
		if(email != null)
			email = email.trim();
		String deptId = getRequest().getParameter("sysDeptName");//部门ID
		String sysIdTypeId = getRequest().getParameter("sysIdTypeName");//身份ID
		String sysUserType = getRequest().getParameter("sysUserTypeName");//用户类型
		String stateId = getRequest().getParameter("state");//状态ID
		String sysCardTypeId = getRequest().getParameter("sysCardTypeName"); //证件类型ID
		String sysAreaId = getRequest().getParameter("sysAreaName");//区域ID
		
		//操作日志参数
		String param = "sysPersonId: "+sysPersonId +"cardNumber: "+cardNumber +",name: "+name +",email: "+email +",sysCardTypeId: "+sysCardTypeId +",sysAreaId: "+sysAreaId +",mobile: "+mobile +",deptId: "+deptId +",sysIdTypeId: "+sysIdTypeId +",sysUserType: "+sysUserType +",stateId: "+stateId ;
		sysLogOperate.setParamName(param);
		getSysLogOperateServ().insertSysLogOperate(sysLogOperate);
		
		hashMap.put("sysPersonId", sysPersonId);
		hashMap.put("cardNumber", cardNumber);
		hashMap.put("name", name);
		hashMap.put("mobile", mobile);
		hashMap.put("deptId", deptId);
		hashMap.put("sysIdTypeId", sysIdTypeId);
		hashMap.put("sysUserType", sysUserType);
		hashMap.put("stateId", stateId);
		hashMap.put("email", email);
		hashMap.put("sysCardTypeId", sysCardTypeId);
		hashMap.put("sysAreaId", sysAreaId);
		if(sysPersonId != null && !sysPersonId.equals(""))
			getSysPersonServ().updateSysPerson(hashMap);
		return SUCCESS;
	}
	
	
	public ISysDeptService getSysDeptServ() {
		sysDeptServ = (ISysDeptService)SpringBeanInvoker.getBean("eaap-op2-sso-sysDeptServ");
		return sysDeptServ;
	}

	public void setSysDeptServ(ISysDeptService sysDeptServ) {
		this.sysDeptServ = sysDeptServ;
	}

	public IPersonServ getSysPersonServ() {
		sysPersonServ = (IPersonServ)SpringBeanInvoker.getBean("personServ");
		return sysPersonServ;
	}

	public void setSysPersonServ(IPersonServ sysPersonServ) {
		this.sysPersonServ = sysPersonServ;
	}
	
	public ISysAreaService getSysAreaServ() {
		sysAreaServ = (ISysAreaService)SpringBeanInvoker.getBean("eaap-op2-sso-sysAreaServ");
		return sysAreaServ;
	}

	public void setSysAreaServ(ISysAreaService sysAreaServ) {
		this.sysAreaServ = sysAreaServ;
	}

	public ISysLogOperateService getSysLogOperateServ() {
		sysLogOperateServ = (ISysLogOperateService)SpringBeanInvoker.getBean("eaap-op2-sso-sysLogOperateServ");
		return sysLogOperateServ;
	}

	public void setSysLogOperateServ(ISysLogOperateService sysLogOperateServ) {
		this.sysLogOperateServ = sysLogOperateServ;
	}

	public List getStateList() {
		return stateList;
	}

	public void setStateList(List stateList) {
		this.stateList = stateList;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}

	public List getSysIdTypeList() {
		return sysIdTypeList;
	}

	public void setSysIdTypeList(List sysIdTypeList) {
		this.sysIdTypeList = sysIdTypeList;
	}

	public List getSysUserTypeList() {
		return sysUserTypeList;
	}

	public void setSysUserTypeList(List sysUserTypeList) {
		this.sysUserTypeList = sysUserTypeList;
	}

	public List getSysCardTypeList() {
		return sysCardTypeList;
	}

	public void setSysCardTypeList(List sysCardTypeList) {
		this.sysCardTypeList = sysCardTypeList;
	}

	public List getSysAreaList() {
		return sysAreaList;
	}

	public void setSysAreaList(List sysAreaList) {
		this.sysAreaList = sysAreaList;
	}

	public Map getSysPersonMap() {
		return sysPersonMap;
	}

	public void setSysPersonMap(Map sysPersonMap) {
		this.sysPersonMap = sysPersonMap;
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
