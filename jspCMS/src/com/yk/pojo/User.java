package com.yk.pojo;

/**
 * 用户实体类
 * @author Administrator
 * @Date 2019-08-15 10:39:25
 * @Description 
 *
 */
public class User {
	
	private int id;
	private String username;
	private String password;
	private String realname;
	private String imgpath;
	private int enabled;
	private int department_id;
	private int ordernum;
	private String deptName;
	private String roleName;
	private int role_id;
	private String permissions;
	private String salt;
	
	public User(int id, String username, String password, String realname, String imgpath, int enabled,
			int department_id, int ordernum, String deptName, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.imgpath = imgpath;
		this.enabled = enabled;
		this.department_id = department_id;
		this.ordernum = ordernum;
		this.deptName = deptName;
		this.roleName = roleName;
	}
	
	public User(String username, String realname, int enabled, String deptName, String roleName) {
		super();
		this.username = username;
		this.realname = realname;
		this.enabled = enabled;
		this.deptName = deptName;
		this.roleName = roleName;
	}
	
	

	public User(String username, String password, String realname, int enabled, int department_id, int role_id, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.enabled = enabled;
		this.department_id = department_id;
		this.role_id = role_id;
		this.salt=salt;
	}
	
	

	public User(String username, String realname, String imgpath, int enabled, int department_id, int ordernum,
			String deptName, String roleName, int role_id, String permissions, Department department, Role role) {
		super();
		this.username = username;
		this.realname = realname;
		this.imgpath = imgpath;
		this.enabled = enabled;
		this.department_id = department_id;
		this.ordernum = ordernum;
		this.deptName = deptName;
		this.roleName = roleName;
		this.role_id = role_id;
		this.permissions = permissions;
		this.department = department;
		this.role = role;
	}

	public User(String username, String realname, int enabled, int department_id, String deptName, int role_id,String salt) {
		super();
		this.username = username;
		this.realname = realname;
		this.enabled = enabled;
		this.department_id = department_id;
		this.deptName = deptName;
		this.role_id = role_id;
		this.salt=salt;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getEnabled() {
		return enabled;
	}
	private Department department;
	private Role role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public int isEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public User(int id, String username, String password, String realname, String imgpath, int enabled,
			int department_id, int ordernum, Department department, Role role,String permissions,String salt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.imgpath = imgpath;
		this.enabled = enabled;
		this.department_id = department_id;
		this.ordernum = ordernum;
		this.department = department;
		this.role = role;
		this.permissions = permissions;
		this.salt=salt;
	}
	public User() {
		super();
	}


	public User(int id, String username, String realname, int enabled, int department_id, int role_id) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.enabled = enabled;
		this.department_id = department_id;
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", imgpath=" + imgpath + ", enabled=" + enabled + ", department_id=" + department_id + ", ordernum="
				+ ordernum + ", deptName=" + deptName + ", roleName=" + roleName + ", role_id=" + role_id
				+ ", department=" + department + ", role=" + role + "]";
	}
	
	
}
