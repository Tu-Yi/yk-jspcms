package com.yk.dao;

import java.util.List;

import com.yk.pojo.TreeSelect;

public interface PermissionDao {

	List<TreeSelect> findTree();

	int selectCount(int rid);

	int updateRP(int rid, String permissions);

	int insertRP(int rid, String permissions);

}
