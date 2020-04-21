package com.kcx.mall.shop.controller;
/**
 * @date 2020-04-17
 * @author kcx
 * @description 
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.shiroHelper.CustomizedToken;
import com.kcx.mall.common.shiroHelper.LoginType;
import com.kcx.mall.shop.pojo.Shop;
import com.kcx.mall.shop.service.ShopService;

public class ShopController {

	private static final String SHOP_LOGIN_TYPE = LoginType.SHOP.toString();

	@Autowired
	private ShopService service;
	
	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param response
	 * @param shop
	 */
	@RequestMapping("/shop/login")
	@ResponseBody
	public Integer login(HttpServletRequest request, HttpServletResponse response, String loginName, String pwd) {
		// 密码加密
		pwd = new Sha256Hash(pwd, "我有一只小花猫", 10).toBase64();

		// 封装用户名和密码
		CustomizedToken upToken = new CustomizedToken(loginName, pwd, SHOP_LOGIN_TYPE);

		// Shiro登陆
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
		Shop shop = service.queryByName(loginName);		
		
		// 如果登录成功，session中记录当前管理员的登录名（管理员id）
		HttpSession session = request.getSession();
		session.setAttribute("loginName", loginName); // 记录登录名
		session.setAttribute("loginType", "shop");
		session.setAttribute("loginId", shop.getShopId()); // 记录id
		return 3;
	}
}
