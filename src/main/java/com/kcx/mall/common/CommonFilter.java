package com.kcx.mall.common;

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
import javax.servlet.http.HttpSession;

/**
 * 过滤器
 * @author Administrator
 *
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void destroy() {
				
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		//设置允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");	
		
//		//登录验证
		String uri = request.getRequestURI();
		
		//web应用名称
		String app = request.getContextPath();
		
		
		//判断是否是不需要登录验证的uri	
		
		if (!uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/register.html")
				&& !uri.equals(app + "/customer/addCustomer.html")
				&& !uri.equals(app + "/shop/addShop.html")
				&& !uri.equals(app + "/*/checkLoginName*")
				&& !uri.equals(app + "/logout.jsp")
				&& !uri.equals(app + "/customer/login*")
				&& !uri.equals(app + "/manager/login*")
				&& !uri.equals(app + "/shop/login*")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/customer/login")
				&& !uri.startsWith(app + "/shop/login")
				&& !uri.startsWith(app + "/manager/login")
				&& !uri.startsWith(app + "/getVerifiCode")
				&& !uri.startsWith(app + "/auth")
				&& !uri.startsWith(app + "/customer/checkLoginName")
				&& !uri.startsWith(app + "/customer/add")
				&& !uri.startsWith(app + "/shop/add")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/getVerifiCode*")
				&& !uri.startsWith(app + "/auth")
				&& !uri.startsWith(app + "/fonts")
				&& !uri.startsWith(app + "/images")) {
			
//			System.out.println("这是url:   "+uri);
//			System.out.println("这是app:   "+app);
//			System.out.println("这是判断   "+uri.startsWith(app + "/customer/checkLoginName"));
			//登录判断
			HttpSession session = request.getSession();			
			String loginName = (String) session.getAttribute("loginName");
			if (loginName == null) {
				System.out.println("loginName为空");
				//判断是否是ajax请求
				String xhr = request.getHeader("x-requested-with");
								
				if (xhr != null && xhr.equals("XMLHttpRequest")) {					
					response.setHeader("sessionStatus", "timeout");	//响应前端一个自定义报头信息					
				} else {
					System.out.println("这是commonfilter里的重定向");
					response.sendRedirect(app + "/logout.jsp"); //重定向到登录页
				}
				
				return;
			}			
			
		}
		
		//继续向下执行
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
				
	}

}