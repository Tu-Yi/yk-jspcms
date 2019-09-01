package com.yk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		//1.请求到达目标资源之前的预处理操作		
		String uri = httprequest.getRequestURI();
		String qs = httprequest.getQueryString(); // 如果 没有查询字符串，就是null
		int n1 =  uri.indexOf("login.jsp");//>=0 存在
		int n2 =  uri.indexOf("getCode");
		int n3 =  uri.indexOf("lib");
		int n4 =  uri.indexOf("js/");
		int n5 =  uri.indexOf("vendor");
		int n6 =  uri.indexOf("assets");
		int n7 =  uri.indexOf("css");
		int n8 =  uri.indexOf("bodyTab");
		int n9 = -1;
		if(qs != null){
			n9  = qs.indexOf("login");
		}
		
		
		if(n1>=0 || n2>=0 ||n3>=0 || n4>=0 || n5>=0 ||n6>=0 || n7>=0 || n8>=0 ||n9>=0 ){ //需要排除在外的资源
			//放行
			chain.doFilter(request, response);
		}else{
			//判断用户是否登录，如果登录，就允许访问，如果没有登录，就重定向到登录页面
			Object user = httprequest.getSession().getAttribute("user");
			if(user != null){
				//就允许访问,调用下一个过滤器或者目标资源
				chain.doFilter(request, response);
				//3.响应离开目标资源后的后处理操作
				
			}else{
				//就重定向到登录页面
				httpresponse.sendRedirect(httprequest.getContextPath()+"/login.jsp");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
