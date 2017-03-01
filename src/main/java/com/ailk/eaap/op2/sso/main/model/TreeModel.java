package com.ailk.eaap.op2.sso.main.model;

import java.util.List;

public class TreeModel {
	private String id;
	private String text;
	private boolean leaf;
	private String qtitle;//url
	private List<TreeModel> children;
	public TreeModel(){}
	public TreeModel(String id,String text,boolean leaf,String href){
		this.id = id;
		this.text =text;
		this.leaf = leaf;
		this.qtitle = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<TreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	
}	
