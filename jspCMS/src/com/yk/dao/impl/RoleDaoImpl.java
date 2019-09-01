package com.yk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yk.dao.RoleDao;
import com.yk.pojo.Role;
import com.yk.pojo.User;
import com.yk.util.DBUtil;

public class RoleDaoImpl implements RoleDao {

	@Override
	public List<Role> findAll(int start, int end) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Role> list = new ArrayList<Role>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select * from c_role LIMIT ?,?");
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");//??
				String remark = rs.getString("remark");
				int enabled = rs.getInt("enabled");
				
				
				Role role = new Role(id, name, remark, enabled);
				
				//3.将user放入userList
				list.add(role);
				
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
	public int selectCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();			
			//3.创建SQL命令发送器（手枪）
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*) from c_role");
			pstmt = conn.prepareStatement(sb.toString());

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

	

}
