package com.yk.service.impl;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.yk.dao.UserDao;
import com.yk.dao.impl.UserDaoImpl;
import com.yk.pojo.User;
import com.yk.service.UserService;
import com.yk.util.DigestUtil;

public class UserServiceImpl implements UserService {

	UserDao ud = new UserDaoImpl();
	
	
	@Override
	public User login(String username, String password) throws NoSuchAlgorithmException {
		
		User user = this.ud.findByName(username);
		if(user==null) {
			return null;
		}else {
			if(user.getPassword().equals(DigestUtil.getSHA1((password+user.getSalt()).getBytes()))) {
				return user;
			}else {
				return null;
			}
		}
	}


	@Override
	public List<User> findAll(int start, int end,String username,String deptIds) {
		
		return this.ud.findAll(start,end,username,deptIds);
	}


	@Override
	public int selectCount(String username,String deptIds) {
		
		return this.ud.selectCount(username,deptIds);
	}


	@Override
	public void delete(String id) {
		this.ud.delete(id);
	}




	@Override
	public int enableUser(String id, int enable) {
		
		return this.ud.enableUser(id,enable);
	}


	@Override
	public void deleteById(String ids) {
		this.ud.deleteById(ids);
	}


	@Override
	public List<User> getExportData(String username, String deptIds) {
		
		return this.ud.getExportData(username,deptIds);
	}


	@Override
	public int addUser(User user) {
		
		return this.ud.addUser(user);
	}


	@Override
	public User findById(int id) {
		
		return this.ud.findById(id);
	}


	@Override
	public int editUser(User user) {
		
		return this.ud.editUser(user);
	}


	@Override
	public int initPwd(int id, String password) {
		
		return this.ud.initPwd(id,password);
	}


	


	

}
