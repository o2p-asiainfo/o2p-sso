package com.ailk.eaap.op2.sso.main.bo;

import java.io.Serializable;

/**
 * 
 * @Package com.ailk.eaap.op2.sso.main.bo
 * @ClassName: ClientMemcachedInfo
 * @Description: TODO(memcached缓存服务器存放数据对象)
 * @author sun
 * @date 2013-6-8 上午10:12:46
 */
public class ClientMemcachedInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户信息
	 */
	private ClientUserInfo clientUserInfo;
	/**
	 * 各系统本地lsessionId
	 */
	private String  lSessionId;
	/**
	 * 各系统标识Id
	 */
	private String localSystemId;
	
	public ClientUserInfo getClientUserInfo() {
		return clientUserInfo;
	}
	public void setClientUserInfo(ClientUserInfo clientUserInfo) {
		this.clientUserInfo = clientUserInfo;
	}
	
	public String getlSessionId() {
		return lSessionId;
	}
	public void setlSessionId(String lSessionId) {
		this.lSessionId = lSessionId;
	}
	public String getLocalSystemId() {
		return localSystemId;
	}
	public void setLocalSystemId(String localSystemId) {
		this.localSystemId = localSystemId;
	}
	

}
