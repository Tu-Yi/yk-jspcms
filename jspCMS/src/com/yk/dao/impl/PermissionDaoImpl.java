package com.yk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yk.dao.PermissionDao;
import com.yk.pojo.Permission;
import com.yk.pojo.TreeSelect;
import com.yk.util.DBUtil;

public class PermissionDaoImpl implements PermissionDao {

	@Override
	public List<TreeSelect> findTree() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TreeSelect> list = new ArrayList<TreeSelect>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from c_permission");
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parentId = rs.getInt("pid");
				String href = rs.getString("href");
				int enabled = rs.getInt("enabled");

				Permission p = new Permission(id, name, parentId, href, enabled);

				TreeSelect ts = new TreeSelect(p.getId(), p.getPid(), p.getName(), "0");
				list.add(ts);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return list;
	}

	@Override
	public int selectCount(int rid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*) from c_role_permission where rid=?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, rid);
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			rs.next();
			count = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		// 7.返回数据
		return count;
	}

	@Override
	public int updateRP(int rid, String permissions) {

		String sql = "update c_role_permission set permissions=? where rid=?";

		Object[] params = { permissions, rid };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int insertRP(int rid, String permissions) {
		
		String sql = "insert into c_role_permission values(?,?)";

		Object[] params = { rid,permissions };
		return DBUtil.executeUpdate(sql, params);
	}

}
