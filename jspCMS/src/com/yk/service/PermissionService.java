package com.yk.service;

import java.util.List;

import com.yk.pojo.TreeSelect;

public interface PermissionService {

	List<TreeSelect> getPermissionTreeSelect();

	int assignPermission(int rid, String permissions);

}
