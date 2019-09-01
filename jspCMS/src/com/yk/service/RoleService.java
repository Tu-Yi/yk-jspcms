package com.yk.service;

import java.util.List;

import com.yk.pojo.Role;
import com.yk.pojo.TreeSelect;

public interface RoleService {

	List<Role> findAll(int start, int end);

	int selectCount();


}
