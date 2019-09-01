package com.yk.pojo;

public class Permission {
	private int id;
	private String name;
	private String type;
	private int pid;
	private String preCode;
	private String href;
	private int enabled;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPreCode() {
		return preCode;
	}
	public void setPreCode(String preCode) {
		this.preCode = preCode;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public Permission(int id, String name, int pid, String href, int enabled) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.href = href;
		this.enabled = enabled;
	}
	public Permission() {
		super();
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", type=" + type + ", pid=" + pid + ", preCode=" + preCode
				+ ", href=" + href + ", enabled=" + enabled + "]";
	}
	
	
}
