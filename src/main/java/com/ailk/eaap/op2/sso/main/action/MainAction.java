package com.ailk.eaap.op2.sso.main.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ailk.eaap.op2.bo.Tenant;
import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;
import com.ailk.eaap.op2.sso.main.bo.MenuInfo;
import com.ailk.eaap.op2.sso.main.bo.MenuInfoBO;
import com.ailk.eaap.op2.sso.main.service.IMenuServ;
import com.ailk.eaap.op2.sso.main.service.IPersonServ;
import com.ailk.eaap.op2.sso.main.util.CommonUtil;
import com.ailk.eaap.op2.sso.main.util.CookiesUtil;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.integration.o2p.session.web.manage.SSOUserFactory;
import com.asiainfo.integration.o2p.web.bo.LoginUserInfo;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.ui.struts.BaseAction;

/**
 * 主页面action
 * @author zhaobl
 *
 */
public class MainAction extends BaseAction {
	private IMenuServ menuServ;
	private String sysPersonId; //用户ID
	private String parentMenuId; //父菜单ID
	private String businessSystemId; //系统ID
	private String userName; //用户工号
	
	//  private Integer parentMenuId;
    private List<MenuInfo> topMenuInfo;
    private Integer childId;//获取zTree的Id
    private String oldUrl;//重定向前的URL
    private String localSystemId;//各系统标识ID
    private String type;
    private Tenant tenant = new Tenant();
    private String tenantCode;
	private IPersonServ personServ;	

    
	public String indexForm(){
    	HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		Integer tenantId = CommonUtil.getTenantId(session);
		if(tenantId != null){
			Tenant tenantMap = new Tenant(); 
			tenantMap.setTenantId(tenantId);
			tenant =personServ.getTenant(tenantMap);		//获取租对象
		}
		return SUCCESS;
	}
	
	
    /**
     *获取主页面各级菜单项
     * @return
     * @throws IOException 
     */
    public void showMenu() throws IOException {
    	/** 同一浏览器不同用户登录时，再次点击一级菜单判断前后session中用户是否相同 **/
    	HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute("cardNumber");		
		if(!userInfo.getId().equalsIgnoreCase(sysPersonId)) {
			writeString("{\"flag\" : 1}");
			return;
		}
		Integer tenantId = CommonUtil.getTenantId(session);
		String language =getLocale().getLanguage();			
		List<MenuInfoBO> menuInfoBOList = getMenuServ().queryMenuInfoBO(sysPersonId, parentMenuId, businessSystemId,type, language,tenantId);
		JSONArray mainMenuInfoBOJson = JSONArray.fromObject(menuInfoBOList);
		writeString(mainMenuInfoBOJson.toString());
    }
    /**
     * 注销
     * @return
     */
    public String logOut() {
    	HttpServletRequest request = getRequest();
    	HttpServletResponse response = getResponse();
		HttpSession session = request.getSession();
		//删除session中信息
		session.removeAttribute("cardNumber");
		CookiesUtil.clearCookie(request, response);
		UserRoleInfo userRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
		SSOUserFactory.getDefaultSsoUserCache().removeUser(userRoleInfo.getName());
		session.invalidate();
    	return SUCCESS;
    }
    
    /**
     * 验证用户的有效性
     * @throws IOException 
     */
    public void verifyUser() throws IOException{
    	JSONObject jsonObject = new JSONObject();
    	HttpServletRequest request = getRequest();
    	HttpSession session = request.getSession();
    	UserRoleInfo userRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
    	if(userRoleInfo == null){
    		jsonObject.put("result", 2);
    	}else{
	    	if(!StringUtil.isEmpty(WebPropertyUtil.getPropertyValue("is_sso_verify")) && !Boolean.valueOf(WebPropertyUtil.getPropertyValue("is_sso_verify"))){
	    		jsonObject.put("result", 0);
	    	}else{
		    	String sessionId = session.getId();
		    	LoginUserInfo loginUserInfo = SSOUserFactory.getDefaultSsoUserCache().getUser(userRoleInfo.getName());
		    	if(loginUserInfo != null){
		    		String loginSessionId = loginUserInfo.getSessionId();
		    		//重复登录
		    		if(!sessionId.equals(loginSessionId)){
		    			jsonObject.put("result", 1);
		    		//成功
		    		}else{
		    			jsonObject.put("result", 0);
		    		}
		    	//超时
		    	}else{
		    		jsonObject.put("result", 2);
		    	}
	    	}
    	}
    	writeString(jsonObject.toString());
    }
    
    
//    /**
//     *获取顶部导航栏数据
//     * @return
//     * @throws IOException 
//     */
//    public void showMain() throws IOException {
//		Integer personId = 1;
//		Integer parentMenuId = 1;
//		
//		topMenuInfo = getMenuServ().queryMenuInfoByPersonId(personId, parentMenuId);
//		List<MainMenuInfoVO> mainMenuInfoVOs = new ArrayList<MainMenuInfoVO>();
//		for(MenuInfo mi:topMenuInfo){
//			mainMenuInfoVOs.add(BOToVOUtils.getMainMenuInfoVO(mi));
//		}
//		JSONArray mainMenuInfoJson = JSONArray.fromObject(mainMenuInfoVOs);
//		writeString(mainMenuInfoJson.toString());
//    }
//    /**
//     * 
//     * @Title: showLeftMain
//     * @Description: TODO(根据上栏主菜单查询左侧主菜单)
//     * @throws IOException void
//     */
//    
//    public void showLeftMain() throws IOException {
//		Integer personId = 1;
//		topMenuInfo = getMenuServ().queryMenuInfoByPersonId(personId, parentMenuId);
//		List<MainMenuInfoVO> mainMenuInfoVOs = new ArrayList<MainMenuInfoVO>();
//		for(MenuInfo mi:topMenuInfo){
//			MainMenuInfoVO mm = BOToVOUtils.getMainMenuInfoVO(mi);
//			mainMenuInfoVOs.add(mm);
//		}
//		JSONArray mainMenuInfoJson = JSONArray.fromObject(mainMenuInfoVOs);
//		writeString(mainMenuInfoJson.toString());
//    }
//    /**
//     * 根据上栏主菜单查询左侧ZTree
//     * @return
//     * @throws IOException 
//     */
//    public void showMenuAndChildrenMenus() throws IOException {
//	//
//    	Integer personId = 1;
//		topMenuInfo = getMenuServ().queryMenuInfoAndChildrenByPersonId(personId, childId);
//		List<TreeMenuInfoVO> treeMenuInfoVOs = new ArrayList<TreeMenuInfoVO>();
//		for(MenuInfo mi:topMenuInfo){
//			treeMenuInfoVOs.add(BOToVOUtils.getTreeMenuInfoVO(mi));
//		}
//		JSONArray treeMenuInfoVOJson = JSONArray.fromObject(treeMenuInfoVOs);
//		writeString(treeMenuInfoVOJson.toString());
//    }
    /**
     * 
     * @Title: redirectSendTicketToLocalSystem
     * @Description: TODO(生成ticket，将ticket和qSessionId传递给各系统平台)
     * @throws IOException void
     */
//    public void redirectSendTicketToLocalSystem() throws IOException{
//    	StringBuffer buffer = new StringBuffer();
//    	String qSessionId = getRequest().getSession().getId();
////    	oldUrl = "http://10.1.6.15:9083/sso_test?";
//    	oldUrl = "http://localhost:9999/sso_test?";
//    	buffer.append(oldUrl);
//    	
//    	buffer.append("&qSessionId=" + qSessionId);
//    	buffer.append("&ticketId=");
//    	String ticketId = qSessionId +System.currentTimeMillis();
//    	buffer.append(ticketId);
//    	buffer.append("&localSystemId=");
//    	buffer.append(localSystemId);
//    	
//    	
//    	ArrayList<ClientMemcachedInfo> arrayList = (ArrayList<ClientMemcachedInfo>)MemOperator.gets(qSessionId);
//    	ClientUserInfo clientUserInfo = arrayList.get(0).getClientUserInfo();
//    	ClientMemcachedInfo clientMemInfo = new ClientMemcachedInfo();
//    	clientMemInfo.setClientUserInfo(clientUserInfo);
//    	clientMemInfo.setLocalSystemId(localSystemId);
//    	
//    	/**
//    	 * 这里要根据clientUserInfo生成xml
//    	 */
//    	String userInfoXml = "";
//    	try {
//    		MemOperator.add(qSessionId, 1000*60*30, clientMemInfo);//登录过的系统
//			MemOperator.add(ticketId, 1000*60*30, userInfoXml);
//		} catch (EAAPException e) {
//			e.printStackTrace();
//		}
//    	writeString(buffer.toString());
//    }
//    
//    
//    
//    public String getTickeId() throws IOException{
//    	String qSessionId = getSession().getId();//全局sessionId
//    	return null;
//    }
    
//    /**
//     * @return the parentMenuId
//     */
//    public Integer getParentMenuId() {
//        return parentMenuId;
//    }
//
//    /**
//     * @param parentMenuId the parentMenuId to set
//     */
//    public void setParentMenuId(Integer parentMenuId) {
//        this.parentMenuId = parentMenuId;
//    }

    /**
     * @return the topMenuInfo
     */
    public List<MenuInfo> getTopMenuInfo() {
        return topMenuInfo;
    }

    /**
     * @param topMenuInfo the topMenuInfo to set
     */
    public void setTopMenuInfo(List<MenuInfo> topMenuInfo) {
        this.topMenuInfo = topMenuInfo;
    }


    /**
     * @param menuServ the menuServ to set
     */
    public void setMenuServ(IMenuServ menuServ) {
        this.menuServ = menuServ;
    }
    
    public Integer getChildId() {
		return childId;
	}
	public void setChildId(Integer childId) {
		this.childId = childId;
	}
	
	public String getOldUrl() {
		return oldUrl;
	}
	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}
	
	public String getLocalSystemId() {
		return localSystemId;
	}
	public void setLocalSystemId(String localSystemId) {
		this.localSystemId = localSystemId;
	}
	public IMenuServ getMenuServ() {
		if (menuServ == null) {
		    WebApplicationContext ctx = WebApplicationContextUtils
			    .getWebApplicationContext(this.getSession()
				    .getServletContext());
		    menuServ = (IMenuServ) ctx.getBean("eaap-op2-sso-main-menuServ");
		}
		return menuServ;
    }
	
	public String getSysPersonId() {
		return sysPersonId;
	}
	public void setSysPersonId(String sysPersonId) {
		this.sysPersonId = sysPersonId;
	}
	public String getBusinessSystemId() {
		return businessSystemId;
	}
	public void setBusinessSystemId(String businessSystemId) {
		this.businessSystemId = businessSystemId;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public IPersonServ getPersonServ() {
		return personServ;
	}

	public void setPersonServ(IPersonServ personServ) {
		this.personServ = personServ;
	}

	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
}
