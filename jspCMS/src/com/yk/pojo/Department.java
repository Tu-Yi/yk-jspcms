package com.yk.pojo;

/**
 * 部门实体类
 * @author Administrator
 * @Date 2019-08-15 10:35:52
 * @Description 
 *
 */
public class Department {
	
	private int id;
	private String name;
	private String remark;
	private int pid;
	private int enabled;
	private int orderNum;
	
	public Department(int id, String name, int pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	
	public Department(int id, String name, int pid, int enabled) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.enabled = enabled;
	}

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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Department(int id, String name, String remark, int pid, int enabled,
			int orderNum) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.pid = pid;
		this.enabled = enabled;
		this.orderNum = orderNum;
	}
	public Department() {
		super();
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", remark=" + remark + ", pid=" + pid + ", enabled="
				+ enabled + ", orderNum=" + orderNum + "]";
	}
	

}
