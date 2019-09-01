package com.yk.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yk.dao.DepartmentDao;
import com.yk.dao.impl.DepartmentDaoImpl;
import com.yk.pojo.TreeSelect;
import com.yk.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
	DepartmentDao dd = new DepartmentDaoImpl();

	
	
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
	public List<TreeSelect> getDepartmentTreeSelect() {
		
		List<TreeSelect> deptList = this.dd.findAll();
		List<TreeSelect> resList = new ArrayList<TreeSelect>();
		for(TreeSelect treeNode : deptList) {
			if(treeNode.getParentId()==0) {
				resList.add(findChildren(treeNode,deptList));
			}
		}
		return resList;
	}

	
	
	
}
