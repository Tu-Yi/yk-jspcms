package com.yk.pojo;
/**
 * 角色实体类
 * @author Administrator
 * @Date 2019-08-15 10:38:21
 * @Description 
 *
 */
public class Role {
	
	private int id;
	private String name;
	private String remark;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Role(int id, String name, String remark, int enabled) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.enabled = enabled;
	}
	public Role() {
		super();
	}
	
	
}
