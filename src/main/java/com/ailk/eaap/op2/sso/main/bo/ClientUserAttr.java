package com.ailk.eaap.op2.sso.main.bo;
/**
 * 
 * @Package com.ailk.eaap.op2.sso.main.bo
 * @ClassName: ClientUserAttr
 * @Description: TODO(用户权限属性)
 * @author sun
 * @date 2013-6-6 上午10:15:35
 */
public class ClientUserAttr {
	
	private String attribution;//归属地
	private String localSystemId;//所属系统的ID
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getLocalSystemId() {
		return localSystemId;
	}
	public void setLocalSystemId(String localSystemId) {
		this.localSystemId = localSystemId;
	}
	
	
}
