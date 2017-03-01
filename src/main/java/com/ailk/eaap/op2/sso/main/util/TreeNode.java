package com.ailk.eaap.op2.sso.main.util;


public class TreeNode {
	private String noteid;

	private String notename;

	private String parentid;

	private String childrennum;

	private int layer;

	public String getNoteid() {
		return noteid;
	}

	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}

	public String getChildrennum() {
		return childrennum;
	}

	public void setChildrennum(String childrennum) {
		this.childrennum = childrennum;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getNotename() {
		return notename;
	}

	public void setNotename(String notename) {
		this.notename = notename;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
}
