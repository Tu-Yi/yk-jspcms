package com.yk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yk.dao.RoleDao;
import com.yk.dao.impl.RoleDaoImpl;
import com.yk.pojo.Role;
import com.yk.pojo.TreeSelect;
import com.yk.service.RoleService;

public class RoleServiceImpl implements RoleService {

	RoleDao rd = new RoleDaoImpl();

	@Override
	public List<Role> findAll(int start, int end) {
		
		return this.rd.findAll(start,end);
	}

	@Override
	public int selectCount() {
		
		return this.rd.selectCount();
	}

	
	
	

}
