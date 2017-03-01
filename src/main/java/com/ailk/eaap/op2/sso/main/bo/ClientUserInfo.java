package com.ailk.eaap.op2.sso.main.bo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @Package com.ailk.eaap.op2.sso.main.bo
 * @ClassName: ClientUserInfo
 * @Description: TODO(客户端用户信息)
 * @author sun
 * @date 2013-6-6 上午10:27:45
 */
public class ClientUserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private String userName;
	private ArrayList<ClientUserAttr> list;
	private String status;
	private String email;
	private String telphone;
	private String role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<ClientUserAttr> getList() {
		return list;
	}
	public void setList(ArrayList<ClientUserAttr> list) {
		this.list = list;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
