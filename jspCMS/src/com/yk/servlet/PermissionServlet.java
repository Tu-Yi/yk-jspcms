package com.yk.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yk.dao.impl.DepartmentDaoImpl;
import com.yk.pojo.Message;
import com.yk.pojo.Permission;
import com.yk.pojo.Role;
import com.yk.pojo.TableData;
import com.yk.pojo.TreeSelect;
import com.yk.pojo.User;
import com.yk.service.DepartmentService;
import com.yk.service.PermissionService;
import com.yk.service.RoleService;
import com.yk.service.UserService;
import com.yk.service.impl.DepartmentServiceImpl;
import com.yk.service.impl.PermissionServiceImpl;
import com.yk.service.impl.RoleServiceImpl;
import com.yk.service.impl.UserServiceImpl;
import java.util.List;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet({ "/PermissionServlet", "/permission" })
public class PermissionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PermissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void getPermissionTree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	
    	PermissionService rs = new PermissionServiceImpl();
    	List<TreeSelect> list = rs.getPermissionTreeSelect();
    	
    	TableData<TreeSelect> data = new TableData<TreeSelect>();
		data.setCode(0);
		data.setMsg("");
		data.setData(list);
		
		response.getWriter().write(new Gson().toJson(data));
		
	}
    
    public void assignPermission(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int rid = Integer.parseInt(request.getParameter("rid"));
		String permissions = request.getParameter("permissions");

		PermissionService rs = new PermissionServiceImpl();
		int n = rs.assignPermission(rid,permissions);
		
		

		Message<Permission> message = new Message<Permission>();

		if(n>0) {
			message.setErrno(1);
			message.setMsg("分配权限成功");
		}else {
			message.setErrno(0);
			message.setMsg("分配权限失败");
		}
		
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
   
    

}
