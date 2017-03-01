package com.ailk.eaap.op2.sso.main.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.action.BaseAction;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.service.IUserService;

public class DeptUserAction extends BaseAction {
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
	private String customerId ;
	private String mobile ;
    private String  stype ;
    private String  password ;
    private String  cardTypeId ;
    private String  cardNumber ;
	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getUsername() {
	 
		return username ;
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
	
	public String list()
	{
		 SYSPERSON bean = new SYSPERSON();
		 paramMap = new HashMap<String, Object>();
	 
	    bean.setName(username) ;
		bean.setEmail(email);
		bean.setDeptId(deptId);
		bean.setMobile(mobile);
		bean.setSysPersonId(sysPersonId) ;
		if("notadmin".equals(session.get("userright"))){
			bean.setUserRight("notadmin") ;
			bean.setDeptId(((SYSPERSON)session.get("userbean")).getDeptId()) ;
		}else{
			bean.setUserRight("admin") ; 
		}
		
		PageBean pageBean = new PageBean();
		if(currPage!=null){
			pageBean.setCurrentPage(currPage);
		}
		if(pageNum!=null){
			pageBean.setPageCount(pageNum);
		}
		bean.setPageBean(pageBean);
		//3-17 增加数据域判断条件 根据session 里取出的 SysIdTypeId、CustomerName进行判断
		SYSPERSON person  = (SYSPERSON)session.get("userbean");
		String idType = person.getSysIdTypeId();
		String cusName = person.getCustomerName();
		userService.doSearchFlow(bean,idType,cusName);
		//userService.doSearchFlow(bean);
		paramMap.put("pageBean", bean.getPageBean());
		paramMap.put("searchResult",bean.getSearchResult());
	 
	 
		List<SYSPERSON> deptList = userService.getDeptList() ;
		List<SYSPERSON> cardList = userService.getCardList() ;
		paramMap.put("cardList", cardList) ;
		paramMap.put("deptList", deptList) ;
		
		return SUCCESS;
		
	}

	
	public String editper(){
		SYSPERSON bean = new SYSPERSON();
		String customerId = request.getParameter("customerId");
		paramMap = new HashMap<String, Object>();
		bean.setSysPersonId(sysPersonId) ;
		bean.setDeptId(deptId);
		PageBean pageBean = new PageBean();
		if(currPage!=null){
			pageBean.setCurrentPage(currPage);
		}
		if(pageNum!=null){
			pageBean.setPageCount(pageNum);
		}
		bean.setPageBean(pageBean);
		SYSPERSON person  = (SYSPERSON)session.get("userbean");
		String idType = person.getSysIdTypeId();
		String cusName = person.getCustomerName();
		String cusId = person.getSysCustomerId();
		userService.doSearchFlow(bean,idType,cusName);
		//userService.doSearchFlow(bean);
		paramMap.put("searchResult",bean.getSearchResult());
		//customerId = bean.getSearchResult().get(0).getCustomerId();
		deptId = bean.getSearchResult().get(0).getDeptId() ;
		cardTypeId = bean.getSearchResult().get(0).getSysCardTypeId() ;
		List<SYSPERSON> deptList = userService.getDeptList() ;
		List<SYSPERSON> cardList = userService.getCardList() ;
		paramMap.put("cardList", cardList) ;
		paramMap.put("deptList", deptList) ;
		return SUCCESS;
			
	}
	
	public String update(){
		String customerId = request.getParameter("customerId");
		SYSPERSON bean = new SYSPERSON();
		bean.setName(username) ;
		bean.setEmail(email);
		bean.setDeptId(deptId);
		bean.setMobile(mobile);
		bean.setPassword(password) ;
		bean.setSysPersonId(sysPersonId) ;
		bean.setCardNumber(cardNumber);
		bean.setSysCardTypeId(cardTypeId) ;
		if("add".equals(stype)){
			//userService.updatePerson(bean ,"add") ;
			//1-12
			userService.updateDeptPerson(bean ,customerId ,"add") ;
		}
		return SUCCESS;
	}
	
	public String insert(){
		String customerId = request.getParameter("customerId");
		SYSPERSON bean = new SYSPERSON();
		bean.setName(username) ;
		bean.setEmail(email);
		bean.setDeptId(deptId);
		bean.setMobile(mobile);
		bean.setPassword(password) ;
		bean.setSysPersonId(sysPersonId) ;
		bean.setCardNumber(cardNumber);
		bean.setSysCardTypeId(cardTypeId) ;
		if("add".equals(stype)){
			//userService.updatePerson(bean ,"update") ;
			//userService.updateDeptPerson(bean ,customerId ,"add") ;
			userService.insertDeptUser(Long.valueOf(deptId), Long.valueOf(sysPersonId));
		}
		else{
			//userService.updatePerson(bean ,"update") ;
			userService.updateDeptPerson(bean ,customerId ,"add") ;
		}
		return SUCCESS;
	}
	
	/**
	 * 用户授权
	 * @return
	 */
	public String preUserRight(){
		    SYSPERSON bean = new SYSPERSON();
		    paramMap = new HashMap<String, Object>();
		    bean.setSysPersonId(sysPersonId) ;
			if("notadmin".equals(session.get("userright"))){
				bean.setUserRight("notadmin") ;
				bean.setDeptId(((SYSPERSON)session.get("userbean")).getDeptId()) ;
			}else{
				bean.setUserRight("admin") ; 
			}
			
			PageBean pageBean = new PageBean();
			if(currPage!=null){
				pageBean.setCurrentPage(currPage);
			}
			if(pageNum!=null){
				pageBean.setPageCount(pageNum);
			}
			bean.setPageBean(pageBean);
			userService.doSearchFlow(bean);
			paramMap.put("pageBean", bean.getPageBean());
			paramMap.put("searchResult",bean.getSearchResult());
			
			 
			List<SYSPERSON> deptList = userService.getDeptList() ;
			paramMap.put("deptList", deptList) ;
			return SUCCESS;
			
		}
	
	
	public IUserService getUserService() {
		return userService;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

 
	 
	
	 
}
