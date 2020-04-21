package com.kcx.mall.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.shiroHelper.CustomizedToken;
import com.kcx.mall.common.shiroHelper.LoginType;
import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.customer.service.CustomerService;
import com.kcx.mall.manager.pojo.Manager;

/**
 * @date 2020-04-04
 * @author kcx
 * @description 
 */
@Controller
public class CustomerController {
	

    private static final String CUSTOMER_LOGIN_TYPE = LoginType.CUSTOMER.toString();

	@Autowired
	private CustomerService service;
	
	// 通过登录名查找
	@RequestMapping("/customer/getByName")
	public Customer addManager(HttpServletRequest request, HttpServletResponse response, String loginName) {
		return service.queryByLoginName(loginName);
	}
	
	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param response
	 * @param mana
	 */
	@RequestMapping("/customer/login")
	@ResponseBody
	public Integer login(HttpServletRequest request, HttpServletResponse response, String loginName, String pwd) {
		// 密码加密
		pwd = new Sha256Hash(pwd, "我有一只小花猫", 10).toBase64();

		// 封装用户名和密码
		CustomizedToken upToken = new CustomizedToken(loginName, pwd, CUSTOMER_LOGIN_TYPE);

		// Shiro登陆
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
		
		Customer customer = service.queryByLoginName(loginName);
		if(customer.getCusIson()) return 4;
		
		customer.setCusIson(true);
		
		// 如果登录成功，session中记录当前管理员的登录名（管理员id）
		HttpSession session = request.getSession();
		session.setAttribute("loginName", loginName); // 记录登录名
		session.setAttribute("loginType", "customer");
		session.setAttribute("loginId", customer.getCusId()); // 记录id
		return 3;
	}
}
