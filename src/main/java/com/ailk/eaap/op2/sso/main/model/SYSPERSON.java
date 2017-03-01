package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.model.BaseModel;

public class SYSPERSON   extends BaseModel {

	static final long serialVersionUID = -3126998878902358585L;
	
    private String sysPersonId;

    private String deptName ;
    
    private String sysPkName = "sysPersonId";

    private String name;

    private String mobile;

    private String deptId;
    
    private String isauth ;
    //密码
    private String password;
    //上次密码
    private String lastPassword;
    private String sysStatusId;

    private String email;

    private String sysCardTypeId;

    private String sysCardTypeName;
    
    private String deptpersonid;
    
    private String cardNumber;

    private String sysUserType;
    
    private String sysIdTypeId;
    
    private String sysIdTypeName;
    
    private String customerName;
    //3-8
    private String sysCustomerId;
    private String userRight ;
    private String sysTableName = "SYS_PERSON";
    private String  parentDeptId ;
	private PageBean pageBean;
	private List<SYSPERSON> searchResult;
	//密码最后一次修改时间
	private String pwdLastUpTime;
	//密码从最后一次修改到现在的时间，精确到分钟
	private String pwdLastTime;
	//登录时是否需要短信验证码
	private String msgCheckCode;
	//同步时间
	private String synStateTime;
    public String getSynStateTime() {
		return synStateTime;
	}

	public String getMsgCheckCode() {
		return msgCheckCode;
	}

	public void setMsgCheckCode(String msgCheckCode) {
		this.msgCheckCode = msgCheckCode;
	}

	public String getPwdLastTime() {
		return pwdLastTime;
	}

	public void setPwdLastTime(String pwdLastTime) {
		this.pwdLastTime = pwdLastTime;
	}

	public String getPwdLastUpTime() {
		return pwdLastUpTime;
	}

	public void setPwdLastUpTime(String pwdLastUpTime) {
		this.pwdLastUpTime = pwdLastUpTime;
	}

	public void setSynStateTime(String synStateTime) {
		this.synStateTime = synStateTime;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<SYSPERSON> getSearchResult() {
		return searchResult;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public void setSearchResult(List<SYSPERSON> searchResult) {
		this.searchResult = searchResult;
	}

	public String getSysPersonId() {
        return sysPersonId;
    }

    public void setSysPersonId(String sysPersonId) {
        this.sysPersonId = sysPersonId;
    }

    public String getSysPkName() {
        return sysPkName;
    }

    public void setSysPkName(String sysPkName) {
        this.sysPkName = sysPkName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSysStatusId() {
        return sysStatusId;
    }

    public void setSysStatusId(String sysStatusId) {
        this.sysStatusId = sysStatusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSysCardTypeId() {
        return sysCardTypeId;
    }

    public void setSysCardTypeId(String sysCardTypeId) {
        this.sysCardTypeId = sysCardTypeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSysUserType() {
        return sysUserType;
    }

    public void setSysUserType(String sysUserType) {
        this.sysUserType = sysUserType;
    }

    public String getSysTableName() {
        return sysTableName;
    }

    public void setSysTableName(String sysTableName) {
        this.sysTableName = sysTableName;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getUserRight() {
		return userRight;
	}

	public void setUserRight(String userRight) {
		this.userRight = userRight;
	}

	public String getIsauth() {
		return isauth;
	}

	public void setIsauth(String isauth) {
		this.isauth = isauth;
	}

	public String getSysCardTypeName() {
		return sysCardTypeName;
	}

	public void setSysCardTypeName(String sysCardTypeName) {
		this.sysCardTypeName = sysCardTypeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSysCustomerId() {
		return sysCustomerId;
	}

	public void setSysCustomerId(String sysCustomerId) {
		this.sysCustomerId = sysCustomerId;
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

	public String getDeptpersonid() {
		return deptpersonid;
	}

	public void setDeptpersonid(String deptpersonid) {
		this.deptpersonid = deptpersonid;
	}
	
}