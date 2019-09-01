package com.yk.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.yk.pojo.User;

public interface UserService {

	User login(String username, String password) throws NoSuchAlgorithmException;

	List<User> findAll(int start, int end, String username, String deptIds);

	int selectCount(String username, String deptIds);

	void delete(String id);

	int initPwd(int id, String password);

	int enableUser(String id, int enable);

	void deleteById(String ids);

	List<User> getExportData(String username, String deptIds);

	int addUser(User user);

	User findById(int id);

	int editUser(User user);


}
