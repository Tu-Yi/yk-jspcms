package com.yk.pojo;

import java.util.List;


public class TreeSelect {
	private int id;
	private int parentId;
	private String title;
	private List<TreeSelect> children;
	private String checkArr;
	

	public String getCheckArr() {
		return checkArr;
	}
	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public List<TreeSelect> getChildren() {
		return children;
	}
	public void setChildren(List<TreeSelect> children) {
		this.children = children;
	}
	

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	
	
	
	public TreeSelect(int id, int parentId, String title, String checkArr) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.checkArr = checkArr;
	}
	public TreeSelect(int id, int parentId, String title) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
	}
	public TreeSelect() {
		super();
	}
	
	
	
}
