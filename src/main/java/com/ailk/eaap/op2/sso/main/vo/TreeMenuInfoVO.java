package com.ailk.eaap.op2.sso.main.vo;

public class TreeMenuInfoVO {
	private String id;
	private String pid;
	private String name;
	private boolean isParent;
	private String icon;
	private String href;
	private String click;
	
	public String getId() {
		return id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getClick() {
		return click;
	}
	public void setClick() {
		this.click = "addTab("+this.id+",'"+this.name+"','"+this.href+"')";
	}
	
}
