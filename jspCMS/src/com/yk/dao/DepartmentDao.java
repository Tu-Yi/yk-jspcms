package com.yk.dao;

import java.util.List;

import com.yk.pojo.Department;
import com.yk.pojo.TreeSelect;

public interface DepartmentDao {
	List<TreeSelect> findAll();
}
