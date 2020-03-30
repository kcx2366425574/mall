package com.kcx.mall.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.manager.service.ManagerService;

/**
 * @date 2020-03-15
 * @author kcx
 * @description 
 */

@Controller
public class ManagerController {

	@Autowired 
	private ManagerService service;
	
	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param response
	 * @param mana
	 */
	@RequestMapping("/manager/login")
	@ResponseBody
	public Integer login(HttpServletRequest request, HttpServletResponse response, String manaName, String manaPassword) {

		// 密码加密
		manaPassword = new Sha256Hash(manaPassword, "我有一只小花猫", 10).toBase64();

		// 封装用户名和密码
		UsernamePasswordToken upToken = new UsernamePasswordToken(manaName, manaPassword);

		// Shiro登陆
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
		
		Manager mana = service.queryByLoginName(manaName);
		if(mana.getManaIson()) return 4;
		
		mana.setManaIson(true);
		
		// 如果登录成功，session中记录当前管理员的登录名（管理员id）
		HttpSession session = request.getSession();
		session.setAttribute("manaName", manaName); // 记录管理员登录名

		session.setAttribute("manaId", mana.getManaId()); // 记录管理员id

		return 3;
	}
}
