package com.ailk.eaap.op2.sso.main.model;

import com.ailk.eaap.op2.sso.framework.model.BaseModel;

public class User  extends BaseModel {
    private Integer userId;

    private String sysPkName = "userId";

    private Integer roleId;

    private String loginName;

    private String zhName;

    private String phone;

    private String email;

    private String mobile;

    private String address;

    private Integer cityId;
    
    private String cityName;
    
    private String roleName;

    private String password;
    
    private String passwordOld;
    
    private String childCount;
    
    private Integer status ;
    
    private String sheetId;
    
    private String flag ;

    private String sysTableName = "CS_USER";
    
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSysPkName() {
        return sysPkName;
    }

    public void setSysPkName(String sysPkName) {
        this.sysPkName = sysPkName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSysTableName() {
        return sysTableName;
    }

    public void setSysTableName(String sysTableName) {
        this.sysTableName = sysTableName;
    }

	public String getChildCount() {
		return childCount;
	}

	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}