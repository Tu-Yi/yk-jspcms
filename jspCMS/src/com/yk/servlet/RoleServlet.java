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
import com.yk.pojo.Role;
import com.yk.pojo.TableData;
import com.yk.pojo.TreeSelect;
import com.yk.pojo.User;
import com.yk.service.DepartmentService;
import com.yk.service.RoleService;
import com.yk.service.UserService;
import com.yk.service.impl.DepartmentServiceImpl;
import com.yk.service.impl.RoleServiceImpl;
import com.yk.service.impl.UserServiceImpl;
import java.util.List;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet({ "/RoleServlet", "/role" })
public class RoleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		int start = (page - 1) * limit;
		int end =  limit;
		
    	
    	RoleService rs = new RoleServiceImpl();
    	List<Role> list = rs.findAll(start,end);
		
		response.getWriter().write(new Gson().toJson(list));
		
	}
    
    public void findRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		int start = (page - 1) * limit;
		int end =  limit;
		
    	
    	RoleService rs = new RoleServiceImpl();
    	int count = rs.selectCount();
    	List<Role> list = rs.findAll(start,end);
    	
    	TableData<Role> data = new TableData<Role>();
		data.setCode(0);
		data.setMsg("");
		data.setCount(count);
		data.setData(list);
		
		response.getWriter().write(new Gson().toJson(data));
		
	}
    
    

}
