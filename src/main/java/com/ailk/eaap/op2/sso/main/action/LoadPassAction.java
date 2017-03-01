package com.ailk.eaap.op2.sso.main.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.action.BaseAction;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.service.IUserService;

public class LoadPassAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	
	private Map<String,Object> paramMap;
    private String currPage;
	private String pageNum;
	private String sysPersonId ;
	private List<MessageBean> result;
	private String msgSeqId;
	private String username ;
	private String email ;
	private String deptId ;
	private String mobile ;
    private String  stype ;
    private String password ;
    private String jsonResult;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<MessageBean> getResult() {
		return result;
	}

	public void setResult(List<MessageBean> result) {
		this.result = result;
	}

	public String getMsgSeqId() {
		return msgSeqId;
	}

	public void setMsgSeqId(String msgSeqId) {
		this.msgSeqId = msgSeqId;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
 

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public String updatepassword()
	{
		paramMap = new HashMap<String, Object>();
		SYSPERSON bean = new SYSPERSON();
		SYSPERSON sp  = (SYSPERSON)session.get("userbean");
		bean.setSysPersonId(sp.getSysPersonId());
		bean.setPassword(password);
		if(sp.getPassword().equals(password)){
			return "error";
		}
		else{
			userService.updatePassword(bean);
			return "success";
		}
	}
	
	public String loadPassword(){
		SYSPERSON sp  = (SYSPERSON)session.get("userbean");
		String sysPersonId = sp.getSysPersonId();
		jsonResult = userService.doloadPassword(sysPersonId);
		try{ 
			response.setContentType("text/plain;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");   
	        PrintWriter out = response.getWriter();  
	        out.print(jsonResult); 
	        //out.write(jsonResult);   
	        //out.close();   
	        out.flush();
		}catch(Exception ex){ 
			return NONE;
		} finally{
			
		}
		return NONE;
		//return SUCCESS;
	}
	public String loadUserName(){
		SYSPERSON sp  = (SYSPERSON)session.get("userbean");
		String name = sp.getName();
		jsonResult = name;
		try{ 
			response.setContentType("text/plain;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");   
	        PrintWriter out = response.getWriter();  
	        out.print(jsonResult);  
	        out.flush();
		}catch(Exception ex){ 
			return NONE;
		} finally{
			
		}
		return NONE;
	}
	public String logout(){
	     session.clear() ;
         return SUCCESS;
	}
	 
	

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getSysPersonId() {
		return sysPersonId;
	}

	public void setSysPersonId(String sysPersonId) {
		this.sysPersonId = sysPersonId;
	}
	 
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public IUserService getUserService() {
		return userService;
	}
	 
}
