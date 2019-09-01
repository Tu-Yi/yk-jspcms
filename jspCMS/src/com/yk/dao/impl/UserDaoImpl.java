package com.yk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yk.util.Constants;
import com.yk.util.DBUtil;
import com.yk.dao.UserDao;
import com.yk.pojo.Department;
import com.yk.pojo.Role;
import com.yk.pojo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByName(String username) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user =  null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			pstmt = conn.prepareStatement("select u.*,p.permissions as permissions from c_user u left join c_role_permission p on u.role_id=p.rid where u.username= ? and u.enabled=1");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				//String empId = rs.getString("empId");//??
				int id = rs.getInt("id");
				String uname = rs.getString("username");//??
				String password = rs.getString("password");
				String realname = rs.getString("realname");
				String imgpath = rs.getString("imgpath");
				int enabled = rs.getInt("enabled");
				int department_id = rs.getInt("department_id");
				int ordernum = rs.getInt("ordernum");
				
				Department dept = new Department();
				dept.setId(department_id);
				
				int role_id = rs.getInt("role_id");	
				Role role = new Role();
				role.setId(role_id);
				
				String permissions = rs.getString("permissions");
				String salt = rs.getString("salt");
				user = new User(id, username, password, realname, imgpath, enabled, department_id, ordernum, dept, role,permissions,salt);					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return user;
	}

	@Override
	public List<User> findAll(int start,int end,String username,String deptIds) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT u.*,d.name as dept,r.name as role FROM c_user u inner JOIN c_department d on u.department_id=d.id join c_role r on u.role_id=r.id ");
			if(username!=null && !"".equals(username)) {
				sb.append(" where u.username=? ");
			}
			if(deptIds!=null && !"".equals(deptIds)) {
				sb.append(" and d.id in ( ");
				sb.append(deptIds);
				sb.append(" ) ");
			}
			sb.append(" LIMIT ?,?");
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sb.toString());
			if(username!=null && !"".equals(username)) {
				pstmt.setString(1, username);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}
			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				int id = rs.getInt("id");
				String uname = rs.getString("username");//??
				String password = rs.getString("password");
				String realname = rs.getString("realname");
				String imgpath = rs.getString("imgpath");
				int enabled = rs.getInt("enabled");
				int department_id = rs.getInt("department_id");
				int role_id = rs.getInt("role_id");	
				int ordernum = rs.getInt("ordernum");
				String deptName = rs.getString("dept");
				String roleName = rs.getString("role");
				
				
				User user = new User(id, uname, password, realname, imgpath, enabled, department_id, ordernum, deptName, roleName);
				
				//3.将user放入userList
				list.add(user);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return list;
	}

	@Override
	public int selectCount(String username, String deptIds) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();			
			//3.创建SQL命令发送器（手枪）
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*) as count from (SELECT u.*,d.name as dept,r.name as role FROM c_user u inner JOIN c_department d on u.department_id=d.id join c_role r on u.role_id=r.id");
			if(username!=null && !"".equals(username)) {
				sb.append(" where u.username=? ");
			}
			if(deptIds!=null && !"".equals(deptIds)) {
				sb.append(" and d.id in ( ");
				sb.append(deptIds);
				sb.append(" ) ");
			}
			sb.append(" ) as temp");
			pstmt = conn.prepareStatement(sb.toString());
			if(username!=null && !"".equals(username)) {
				pstmt.setString(1, username);
			}
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			rs.next();
			count = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}		
		//7.返回数据		
		return count;
	}

	@Override
	public void delete(String id) {
		String sql = "delete from c_user where id = ?";
		Object [] params = {id};
		DBUtil.executeUpdate(sql, params);
	}


	@Override
	public int enableUser(String id, int enable) {
		String sql = "update c_user set enabled=? where id=?"; 
		
		Object [] params ={			
			enable,
			id
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public void deleteById(String ids) {
		
		String sql = "delete from c_user where id in ("+ids+")";
		DBUtil.executeUpdate(sql);
	}

	@Override
	public List<User> getExportData(String username, String deptIds) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT u.*,d.name as dept,r.name as role FROM c_user u inner JOIN c_department d on u.department_id=d.id join c_role r on u.role_id=r.id ");
			if(username!=null && !"".equals(username)) {
				sb.append(" where u.username=? ");
			}
			if(deptIds!=null && !"".equals(deptIds)) {
				sb.append(" and d.id in ( ");
				sb.append(deptIds);
				sb.append(" ) ");
			}
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sb.toString());
			if(username!=null && !"".equals(username)) {
				pstmt.setString(1, username);
			}
			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				String uname = rs.getString("username");
				String realname = rs.getString("realname");
				int enabled = rs.getInt("enabled");
				String deptName = rs.getString("dept");
				String roleName = rs.getString("role");
				
				
				User user = new User(uname, realname, enabled, deptName, roleName);
				
				//3.将user放入userList
				list.add(user);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return list;
	}

	@Override
	public int addUser(User user) {
		System.out.println(user);
		String sql = "insert into c_user values(default,?,?,?,'',?,?,?,0,?)";
		
		Object [] params ={
			user.getUsername(),
			user.getPassword(),
			user.getRealname(),
			user.getEnabled(),
			user.getDepartment_id(),
			user.getRole_id(),
			user.getSalt()
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public User findById(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user =  null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			pstmt = conn.prepareStatement("select u.*,d.name as deptName from c_user u inner join c_department d on u.department_id=d.id where u.id=?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String deptName = rs.getString("deptName");
				String username = rs.getString("username");
				String realname = rs.getString("realname");
				int enabled = rs.getInt("enabled");
				int department_id = rs.getInt("department_id");
				int role_id = rs.getInt("role_id");	
				String salt = rs.getString("salt");
				
				user = new User(username, realname, enabled, department_id, deptName, role_id,salt);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return user;
	}

	@Override
	public int editUser(User user) {
		
		String sql = "update c_user set username=?,realname=?,enabled=?,department_id=?,role_id=? where id=?";
		
		Object [] params ={
			user.getUsername(),
			user.getRealname(),
			user.getEnabled(),
			user.getDepartment_id(),
			user.getRole_id(),
			user.getId()
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int initPwd(int id, String password) {
		
		String sql = "update c_user set password=? where id=?"; 
		
		Object [] params ={			
			password,
			id
		};
		return DBUtil.executeUpdate(sql, params);
	}

	
}
