package com.ailk.eaap.op2.sso.main.bo;

import java.io.Serializable;
/**
 * 主页面菜单显示bean
 * @author zhaobl
 *
 */
public class MenuInfoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String menuId; //菜单Id
	private String menuName; //菜单名称
	private String menuHref; //菜单链接地址
	private String displayMode; //三级菜单的窗口显示模式 0:框架内;1:弹出窗口
	private String subNum;			//子菜单个数
	
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuHref() {
		return menuHref;
	}
	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}
	public String getSubNum() {
		return subNum;
	}
	public void setSubNum(String subNum) {
		this.subNum = subNum;
	}
	
}
