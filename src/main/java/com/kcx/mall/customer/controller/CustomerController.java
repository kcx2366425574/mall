package com.kcx.mall.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Constant;
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
	
	// 通过id查找
	@RequestMapping("/customer/getByCusId")
	@ResponseBody
	public Customer addManager(HttpServletRequest request, HttpServletResponse response, int cusId) {
		return service.queryById(cusId);
	}
	
	// 通过登录名查找
	@RequestMapping("/customer/getByName")
	@ResponseBody
	public Customer addManager(HttpServletRequest request, HttpServletResponse response, String loginName) {
		return service.queryByLoginName(loginName);
	}
	
	// 通过Id得到用户头像
	@RequestMapping("/customer/getHeadById")
	@ResponseBody
	public String queryHead(HttpServletRequest request, HttpServletResponse response ) {
		HttpSession session = request.getSession();
		int cusId = (int) session.getAttribute("loginId");
		return service.queryHeadById(cusId);
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
		
		// 如果登录成功，session中记录当前用户的登录名（用户id）
		HttpSession session = request.getSession();
		session.setAttribute("loginName", loginName); // 记录登录名
		session.setAttribute("loginType", "customer");
		session.setAttribute("loginId", customer.getCusId()); // 记录id
		return 3;
	}
	
	//获取登录名
	@RequestMapping("/customer/getName")
	@ResponseBody
	public String getName(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("loginName");
	}
	
	//获取登录Id
	@RequestMapping("/customer/getId")
	@ResponseBody
	public int getId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		return (int) session.getAttribute("loginId");
	}
	
	//更新头像
	@RequestMapping("/customer/updateHead")
	@ResponseBody
	public void updateHead(HttpServletRequest request, HttpServletResponse response,String cusPhoto) {
		HttpSession session = request.getSession();
		int cusId = (int) session.getAttribute("loginId");
		service.updateHead(cusId, cusPhoto);
	}
	
	//获取头像
	@RequestMapping("/customer/updatePwd")
	@ResponseBody
	public boolean updatePwd(HttpServletRequest request, HttpServletResponse response,String newCusPwd,String oldCusPwd) {
		String cusPwd = new Sha256Hash(oldCusPwd, "我有一只小花猫", 10).toBase64();
		HttpSession session = request.getSession();
		int cusId = (int) session.getAttribute("loginId");
		Customer customer = service.queryById(cusId);
		if(!customer.getCusPassword().equals(cusPwd)) {
			return false;
		}
		String newPwd = new Sha256Hash(newCusPwd, "我有一只小花猫", 10).toBase64();
		service.updatePwd(cusId, newPwd);
		return true;
	}
	
	// 修改员工信息
	@RequestMapping("/customer/updateMoney")
	public void updateEmp(HttpServletRequest request, HttpServletResponse response, float cusAccount) {
		HttpSession session = request.getSession();
		int cusId = (int)session.getAttribute("loginId");
		service.updateMoney(cusId, cusAccount);
	}
	
	//检测登录名
	@RequestMapping("/customer/checkLoginName")
	@ResponseBody
	public Boolean checkLoginName(HttpServletRequest request, HttpServletResponse response,String loginName) {
		Customer customer = service.queryByLoginName(loginName);
		if (customer == null)
			return true;
		return false;
	}
	
	// 增加用户
	@RequestMapping("/customer/add")
	public void addManager(HttpServletRequest request, HttpServletResponse response, Customer customer) {
		customer.setCusIson(false);
		customer.setCusAccount(0f);
		// 密码加密
		String cusPassword = new Sha256Hash(customer.getCusPassword(), "我有一只小花猫", 10).toBase64();
		customer.setCusPassword(cusPassword);
		customer.setCusPhoto(Constant.PHOTO_STRING);
		service.addCus(customer);
	}
}
