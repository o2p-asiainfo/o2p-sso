package com.ailk.eaap.op2.sso.main.bo;

import java.util.List;


public class MenuInfo {
    
    private Integer personId;
    
    private Integer menuId;
    
    private String menuName;
    
    private Integer parentMenuId;
    
    //链接地址
    private String href;
    
    //子菜单
    private List<MenuInfo> childrenMenus;
    
    /**
     * @return the personId
     */
    public Integer getPersonId() {
        return personId;
    }
    /**
     * @param personId the personId to set
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
    /**
     * @return the menuId
     */
    public Integer getMenuId() {
        return menuId;
    }
    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * @return the parentMenuId
     */
    public Integer getParentMenuId() {
        return parentMenuId;
    }
    /**
     * @param parentMenuId the parentMenuId to set
     */
    public void setParentMenuId(Integer parentMenuId) {
    	this.parentMenuId = parentMenuId;
    }
	public List<MenuInfo> getChildrenMenus() {
		return childrenMenus;
	}
	public void setChildrenMenus(List<MenuInfo> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
    
}
