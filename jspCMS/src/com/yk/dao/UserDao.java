package com.yk.dao;

import java.util.List;

import com.yk.pojo.User;

public interface UserDao {

	User findByName(String username);

	List<User> findAll(int start, int end, String username, String deptIds);

	int selectCount(String username, String deptIds);

	void delete(String id);

	int initPwd(int id, String password);

	int enableUser(String id, int enable);

	void deleteById(String _ids);

	List<User> getExportData(String username, String deptIds);

	int addUser(User user);

	User findById(int id);

	int editUser(User user);

}
