package com.yk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yk.dao.PermissionDao;
import com.yk.dao.impl.PermissionDaoImpl;
import com.yk.pojo.TreeSelect;
import com.yk.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	PermissionDao pd = new PermissionDaoImpl();
	
	@Override
	public List<TreeSelect> getPermissionTreeSelect() {
		
		List<TreeSelect> deptList = this.pd.findTree();
		List<TreeSelect> resList = new ArrayList<TreeSelect>();
		for(TreeSelect treeNode : deptList) {
			if(treeNode.getParentId()==0) {
				resList.add(findChildren(treeNode,deptList));
			}
		}
		return resList;
	}
	
	public static TreeSelect findChildren(TreeSelect treeNode, List<TreeSelect> deptList) {
		for(TreeSelect item:deptList) {
			if(treeNode.getId()==item.getParentId()) {
				if(treeNode.getChildren()==null) {
					treeNode.setChildren(new ArrayList<TreeSelect>());
				}
				treeNode.getChildren().add(findChildren(item, deptList));
			}
		}
		return treeNode;
	}

	@Override
	public int assignPermission(int rid, String permissions) {
		
		int n;
		int count = this.pd.selectCount(rid);
		if(count>0) {
			n = this.pd.updateRP(rid,permissions);
		}else {
			n = this.pd.insertRP(rid,permissions);
		}
		
		return n;
	}

}
