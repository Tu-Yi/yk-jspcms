package com.yk.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.google.gson.Gson;
import com.yk.pojo.Message;
import com.yk.pojo.TableData;
import com.yk.pojo.User;
import com.yk.service.UserService;
import com.yk.service.impl.UserServiceImpl;
import com.yk.util.Constants;
import com.yk.util.DigestUtil;

import java.util.List;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet({ "/UserServlet", "/user" })
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		String username = request.getParameter("username");

		String realname = request.getParameter("realname");
		int enabled = Integer.parseInt(request.getParameter("enabled"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		String salt = RandomStringUtils.randomAlphanumeric(16);
		
		String password = DigestUtil.getSHA1((Constants.USER_DEFAULT_PASSWORD+salt).getBytes());
		User user = new User(username, password, realname, enabled, department_id, role_id,salt);
		System.out.println(user);

		UserService  uService = new UserServiceImpl();
		int n = uService.addUser(user);
		
		
		Message<User> message = new Message<User>();

		if(n>0) {
			message.setErrno(1);
			message.setMsg("添加用户成功");
		}else {
			message.setErrno(0);
			message.setMsg("添加用户失败");
		}
		
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    	int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		int enabled = Integer.parseInt(request.getParameter("enabled"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		
		User user = new User(id,username, realname, enabled, department_id, role_id);
		System.out.println(user);

		UserService  uService = new UserServiceImpl();
		int n = uService.editUser(user);
		
		
		Message<User> message = new Message<User>();

		if(n>0) {
			message.setErrno(1);
			message.setMsg("更新用户成功");
		}else {
			message.setErrno(0);
			message.setMsg("更新用户失败");
		}
		
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void initPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		int id = Integer.parseInt(request.getParameter("id"));		
		UserService  uService = new UserServiceImpl();
		User user = uService.findById(id);
		String password = DigestUtil.getSHA1((Constants.USER_DEFAULT_PASSWORD+user.getSalt()).getBytes());

		
		int n = uService.initPwd(id,password);
		Message<User> message = new Message<User>();

		if(n>0) {
			message.setErrno(1);
			message.setMsg("重置密码成功");
		}else {
			message.setErrno(0);
			message.setMsg("重置密码失败");
		}
		
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void enableUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");		
		int enable = Integer.parseInt(request.getParameter("enable"));
		
		UserService  uService = new UserServiceImpl();
		int n = uService.enableUser(id,enable);
		Message<User> message = new Message<User>();
		String msg = "";
		String fail = "";
		if(enable==1) {
			msg = "用户已启用";
			fail = "启用用户失败";
		}else {
			msg = "用户已禁用";
			fail = "禁用用户失败";
		}

		if(n>0) {
			message.setErrno(1);
			message.setMsg(msg);
		}else {
			message.setErrno(0);
			message.setMsg(fail);
		}
		
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");		

		UserService  uService = new UserServiceImpl();
		uService.delete(id);
		
		Message<User> message = new Message<User>();
		message.setErrno(1);
		message.setMsg("删除成功");
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    	
		String ids = request.getParameter("ids");

		UserService  uService = new UserServiceImpl();
		uService.deleteById(ids);
		
		Message<User> message = new Message<User>();
		message.setErrno(1);
		message.setMsg("删除成功");
		message.setT(null);
		response.getWriter().write(new Gson().toJson(message));
		
	}
    
    public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String username = request.getParameter("username");
		String deptIds = request.getParameter("deptIds");
		
		System.out.println(username);
		System.out.println(deptIds);
		
		int start = (page - 1) * limit;
		int end =  limit;
    	
    	
    	UserService  uService = new UserServiceImpl();
    	int count = uService.selectCount(username,deptIds);
		List<User> userList = uService.findAll(start,end,username,deptIds);
		
		TableData<User> data = new TableData<User>();
		data.setCode(0);
		data.setMsg("");
		data.setCount(count);
		data.setData(userList);
		
		response.getWriter().write(new Gson().toJson(data));
		
	}
    
    public void exportData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
		String username = request.getParameter("username");
		String deptIds = request.getParameter("deptIds");
		
		System.out.println(username);
		System.out.println(deptIds);
    	
    	UserService  uService = new UserServiceImpl();
		List<User> userList = uService.getExportData(username,deptIds);
		
		createExcel(userList,response);
		
	}
    
    private static void createExcel(List<User> list,HttpServletResponse response) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("用户信息");
        
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                4 // last column
        );
        sheet.addMergedRegion(region);
        
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户信息");
        
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headCell.setCellStyle(cellStyle);
        
        
        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("用户状态");
        headCell.setCellStyle(cellStyle);
        
        
        headCell = hssfRow.createCell(3);
        headCell.setCellValue("所属部门");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("角色");
        headCell.setCellStyle(cellStyle);


        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            User user= list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(user.getUsername());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(user.getRealname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(user.getEnabled());
            cell.setCellStyle(cellStyle);
            
            
            cell = hssfRow.createCell(3);
            cell.setCellValue(user.getDeptName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(user.getRoleName());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
        	response.setContentType("application/vnd.ms-excel");
        	response.setHeader("Content-disposition", "attachment;filename=user.xls");//附件形式下载，文件名叫duty.xls
        	//OutputStream outputStream = new FileOutputStream("D:/duty.xls");//保存到本地（服务器端）
        	OutputStream outputStream = response.getOutputStream();	 //写到客户端       	
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * 注销操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//结束当前的session
		request.getSession().invalidate();
		
		//跳转到登录页面:注销之后建议使用重定向跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
	
	
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {
		//获取登录信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		byte[] b = Base64.decodeBase64(password.getBytes());
		password = new String(b);
		System.err.println(password);
		
		Message<User> message = new Message<User>();
		
        // 验证验证码
		Boolean blCode = false;
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
            	blCode = true;
            } else {
                blCode = false;
            }
        } else {
            blCode = false;
        }
        
        if(blCode==false) {
        	message.setErrno(0);
			message.setMsg("验证码错误");
			message.setT(null);
			response.getWriter().write(new Gson().toJson(message));
			return;
        }

		//调用业务层完成登录操作
		UserService uService = new UserServiceImpl();
		User user = uService.login(username,password);
		
		//页面跳转
		if(user != null){
			//将员工信息保存在session中
			request.getSession().setAttribute("user", user);
			message.setErrno(1);
			user.setPassword(null);
			user.setSalt(null);
			message.setT(user);
			message.setMsg("登录成功");
		}else{
			message.setErrno(0);
			message.setMsg("用户名或密码错误");
			message.setT(null);
		}
		response.getWriter().write(new Gson().toJson(message));
	}
	
	public void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取登录信息
		int id = Integer.parseInt(request.getParameter("id"));
		
		Message<User> message = new Message<User>();

		//调用业务层完成登录操作
		UserService uService = new UserServiceImpl();
		User user = uService.findById(id);
		
		response.getWriter().write(new Gson().toJson(user));
	}
    

}
