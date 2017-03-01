package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ailk.eaap.op2.sso.framework.util.Constants;
import com.ailk.eaap.op2.sso.main.model.MessageBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.service.IUserService;
import com.ailk.eaap.op2.sso.main.util.MD5;
import com.linkage.rainbow.ui.struts.BaseAction;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private MD5 md5 = new MD5();
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
	private String custId ;
	private String customerName ;
	private String mobile ;
    private String  stype ;
    private String  password ;
    private String  cardTypeId ;
    private String  cardNumber ;
    private String  sysIdTypeId ;
    private String  sysIdTypeName ;
    private String msgCheckCode;
    private String msg ;
    
	public String getMsgCheckCode() {
		return msgCheckCode;
	}

	public void setMsgCheckCode(String msgCheckCode) {
		this.msgCheckCode = msgCheckCode;
	}

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
	
	public void list(){
		StringBuffer stringJson = new StringBuffer();
		stringJson.append("[");
		if(pageNum.equals("10")){
			if(currPage.equals("1")){
				stringJson.append("{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"admin\",\"userName\":\"superAdmin\",\"identity\":\"管理员\",\"mobil\":\"110\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"panyj\",\"userName\":\"潘\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"wenst\",\"userName\":\"温\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"trrb\",\"userName\":\"trrb\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"qwer\",\"userName\":\"qwer\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"432432\",\"userName\":\"432\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"gfds\",\"userName\":\"rew\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"bvdsfg\",\"userName\":\"543fd\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"134\",\"userName\":\"gres\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"sunb\",\"userName\":\"孙博\",\"identity\":\"操作员\",\"mobil\":\"1131234\"}");
			}else{
				stringJson.append("{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"faasd\",\"userName\":\"hjuy\",\"identity\":\"操作员\",\"mobil\":\"110\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"efwe\",\"userName\":\"httyt\",\"identity\":\"操作员\",\"mobil\":\"1131234\"}");
			}
		}else{
			stringJson.append("{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"admin\",\"userName\":\"superAdmin\",\"identity\":\"管理员\",\"mobil\":\"110\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"panyj\",\"userName\":\"潘\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"wenst\",\"userName\":\"温\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"trrb\",\"userName\":\"trrb\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"qwer\",\"userName\":\"qwer\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"432432\",\"userName\":\"432\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"gfds\",\"userName\":\"rew\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"bvdsfg\",\"userName\":\"543fd\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"134\",\"userName\":\"gres\",\"identity\":\"操作员\",\"mobil\":\"1111\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"sunb\",\"userName\":\"孙博\",\"identity\":\"操作员\",\"mobil\":\"1131234\"},"+
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"faasd\",\"userName\":\"hjuy\",\"identity\":\"操作员\",\"mobil\":\"110\"}," +
						"{\"company\":\"电信集团\",\"dept\":\"电信运营中心\",\"userNum\":\"efwe\",\"userName\":\"httyt\",\"identity\":\"操作员\",\"mobil\":\"1131234\"}");
		}
		stringJson.append("]");
		
		try {
			writeString(stringJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		SYSPERSON bean = new SYSPERSON();
//		paramMap = new HashMap<String, Object>();
//	 
//	    bean.setName(username) ;
//		bean.setEmail(email);
//		bean.setDeptId(deptId);
//		bean.setMobile(mobile);
//		bean.setSysPersonId(sysPersonId) ;
//		bean.setCustomerName(customerName);//1-14
//		if("notadmin".equals(session.get("userright"))){
//			bean.setUserRight("notadmin") ;
//			bean.setDeptId(((SYSPERSON)session.get("userbean")).getDeptId()) ;
//		}else{
//			bean.setUserRight("admin") ; 
//		}
//		
//		PageBean pageBean = new PageBean();
//		if(currPage!=null){
//			pageBean.setCurrentPage(currPage);
//		}
//		if(pageNum!=null){
//			pageBean.setPageCount(pageNum);
//		}
//		
//		bean.setPageBean(pageBean);
//		//3-18 增加 数据域判读 获取 用户相关信息 方法
//		SYSPERSON person  = (SYSPERSON)session.get("userbean");
////		String idType = person.getSysIdTypeId();
//		String idType = "1";
////		String cusName = person.getCustomerName();
//		String cusName = "电信集团";
//		String cusId = "";
////		String cusId = person.getSysCustomerId();
//		UserServiceImpl.doSearchFlow(person);
//		//userService.doSearchFlow(bean);
//		paramMap.put("pageBean", bean.getPageBean());
//		paramMap.put("searchResult",bean.getSearchResult());
//		
//		List<SYSPERSON> deptList = null;
//		if((idType.equals("1"))||(idType.equals("2") && cusName.equals("电信集团"))){
//			deptList = userService.getDeptList() ;
//		}
//		if(idType.equals("2") && !cusName.equals("电信集团")){
//			deptList = userService.getDeptList(Long.valueOf(cusId));
//		}
//		//List<SYSPERSON> deptList = userService.getDeptList() ;
//		List<SYSPERSON> cardList = userService.getCardList() ;
//		List<SYSPERSON> sysIdTypeId = userService.getIdTypeList() ;
//		paramMap.put("cardList", cardList) ;
//		paramMap.put("deptList", deptList) ;
//		paramMap.put("sysIdTypeId", sysIdTypeId) ;
////		return SUCCESS;
	}
	
	public String insert(){
		SYSPERSON bean = new SYSPERSON();
		bean.setName(username) ;
		bean.setEmail(email);
		bean.setDeptId(deptId);
		bean.setMobile(mobile);
		String defaultPasswordMd5 = Constants.defaultPassword;
		bean.setPassword(defaultPasswordMd5) ;
		bean.setSysPersonId(sysPersonId) ;
		//bean.setCardNumber(cardNumber);
		bean.setCardNumber(2+"");
		bean.setSysCardTypeId(cardTypeId) ;
		if("add".equals(stype)){
			userService.updatePerson(bean ,"add") ;
			userService.updateDeptPerson(bean ,customerId ,"add") ;
		}
		else{
			userService.updatePerson(bean ,"update") ;
			userService.updateDeptPerson(bean ,customerId ,"update") ;
		}
		return SUCCESS;
	}
	
	public String editper(){
		SYSPERSON bean = new SYSPERSON();
	 	//String customerId = getRequest().getParameter("custId");
	    paramMap = new HashMap<String, Object>();
	    bean.setSysCustomerId(custId) ;
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
		userService.doSearchFlow(bean);
		paramMap.put("searchResult",bean.getSearchResult());
		customerId = getRequest().getParameter("custId");
		//customerId = bean.getSearchResult().get(0).getSysCustomerId();
		deptId = bean.getSearchResult().get(0).getDeptId() ;
		cardTypeId = bean.getSearchResult().get(0).getSysCardTypeId() ;
		sysIdTypeId = bean.getSearchResult().get(0).getSysIdTypeId();
		List<SYSPERSON> deptList = userService.getDeptList() ;
		List<SYSPERSON> cardList = userService.getCardList() ;
		List<SYSPERSON> sysIdTypeIds = userService.getIdTypeList() ;
		paramMap.put("cardList", cardList) ;
		paramMap.put("deptList", deptList) ;
		paramMap.put("sysIdTypeIds", sysIdTypeIds) ;
		paramMap.put("sysIdTypeId", sysIdTypeId) ;
		return SUCCESS;
			
	}
	
	public String addper(){
		    stype = "add" ;
		    msg = ServletActionContext.getRequest().getParameter("message");
		    if(msg != null && msg.trim().equals("error"))
		    {
		    	msg = "员工编号重复，请重新输入！";
		    }
		    paramMap = new HashMap<String, Object>();
		    List<SYSPERSON> deptList = userService.getDeptList() ;
		    List<SYSPERSON> cardList = userService.getCardList() ;
		    List<SYSPERSON> sysIdTypeIds = userService.getIdTypeList() ;
			paramMap.put("cardList", cardList) ;
			paramMap.put("deptList", deptList) ;
			paramMap.put("sysIdTypeIds", sysIdTypeIds) ;
			return SUCCESS;
			
		}
	 
	public String delete(){
		//String customerId = getRequest().getParameter("customerId");
		String [] ids = ServletActionContext.getRequest().getParameterValues("checkbox");
		
		for(int i=0;i<ids.length;i++)
		{
			SYSPERSON bean = new SYSPERSON();
			bean.setDeptpersonid(ids[i]);
			userService.updatePerson(bean ,"delete") ;
		}
		
		//userService.updateDeptPerson(bean ,customerId ,"delete") ;
		return SUCCESS;
	}
	
	public String update(){
		String customerId = getRequest().getParameter("customerid");
		String deptId = ServletActionContext.getRequest().getParameter("deptId");
		String sysIdTypeId = ServletActionContext.getRequest().getParameter("sysIdTypeId");
		SYSPERSON bean = new SYSPERSON();
		bean.setName(username) ;
		bean.setEmail(email);
		bean.setDeptId(deptId);
		bean.setMobile(mobile);
		bean.setMsgCheckCode(msgCheckCode);
//		bean.setPassword(password) ;
		bean.setSysPersonId(sysPersonId) ;
		bean.setCardNumber(cardNumber);
		//bean.setSysCardTypeId(cardTypeId) ;
		bean.setSysIdTypeId(sysIdTypeId);
		if("add".equals(stype)){
			bean.setSysCardTypeId(2+"") ;
			String defaultPasswordMd5 = md5.getMD5ofStr(Constants.defaultPassword);
			bean.setPassword(defaultPasswordMd5) ;
			int t = userService.updatePerson(bean ,"add") ;
			if(t==1)
			{
				return "fail";
			}
			userService.updateDeptPerson(bean ,customerId ,"add") ;
		}
		else{
			userService.updatePerson(bean ,"update") ;

			userService.updateDeptPerson(bean ,customerId ,"update") ;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSysIdTypeId() {
		return sysIdTypeId;
	}

	public void setSysIdTypeId(String sysIdTypeId) {
		this.sysIdTypeId = sysIdTypeId;
	}

	public String getSysIdTypeName() {
		return sysIdTypeName;
	}

	public void setSysIdTypeName(String sysIdTypeName) {
		this.sysIdTypeName = sysIdTypeName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

 
	 
	
	 
}
