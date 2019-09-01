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
import com.yk.pojo.TableData;
import com.yk.pojo.TreeSelect;
import com.yk.pojo.User;
import com.yk.service.DepartmentService;
import com.yk.service.UserService;
import com.yk.service.impl.DepartmentServiceImpl;
import com.yk.service.impl.UserServiceImpl;
import java.util.List;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet({ "/DepartmentServlet", "/dept" })
public class DepartmentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    public void getDepartmentTree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	
    	DepartmentService ds = new DepartmentServiceImpl();
    	List<TreeSelect> list = ds.getDepartmentTreeSelect();
    	
    	TableData<TreeSelect> data = new TableData<TreeSelect>();
		data.setCode(0);
		data.setMsg("");
		data.setData(list);
		
		response.getWriter().write(new Gson().toJson(data));
		
	}
    

}
