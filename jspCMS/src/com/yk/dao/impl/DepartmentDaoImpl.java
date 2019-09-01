package com.yk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yk.dao.DepartmentDao;
import com.yk.pojo.Department;
import com.yk.pojo.TreeSelect;
import com.yk.util.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<TreeSelect> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TreeSelect> list = new ArrayList<TreeSelect>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from c_department");
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parentId = rs.getInt("pid");
				int enabled = rs.getInt("enabled");
				
				Department department = new Department(id,name,parentId,enabled);

				TreeSelect  ts = new TreeSelect(department.getId(), department.getPid(), department.getName());
				list.add(ts);
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

	

}
