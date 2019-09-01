package com.yk.dao;

import java.util.List;

import com.yk.pojo.Role;

public interface RoleDao {

	List<Role> findAll(int start, int end);

	int selectCount();

}
