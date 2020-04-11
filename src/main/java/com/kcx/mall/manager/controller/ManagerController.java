package com.kcx.mall.manager.controller;

import java.util.HashMap;
import java.util.List;

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

import com.kcx.mall.common.Pager;
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
	public Integer login(HttpServletRequest request, HttpServletResponse response, String loginName, String pwd) {

		System.out.println("管理员在登陆");
		// 密码加密
		pwd = new Sha256Hash(pwd, "我有一只小花猫", 10).toBase64();

		// 封装用户名和密码
		UsernamePasswordToken upToken = new UsernamePasswordToken(loginName, pwd);

		// Shiro登陆
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
		
		Manager mana = service.queryByLoginName(loginName);
		if(mana.getManaIson()) return 4;
		
		mana.setManaIson(true);
		
		// 如果登录成功，session中记录当前管理员的登录名（管理员id）
		HttpSession session = request.getSession();
		session.setAttribute("loginName", loginName); // 记录管理员登录名
		session.setAttribute("loginType", "manager");
		session.setAttribute("manaId", mana.getManaId()); // 记录管理员id
		System.out.println("这是session中的"+loginName+"还有id:"+mana.getManaId());
		return 3;
	}
	
	// 增加管理员
	@RequestMapping("/manager/add")
	public void addManager(HttpServletRequest request, HttpServletResponse response, Manager mana) {
		// 密码加密
		String manaPassword = new Sha256Hash(mana.getManaPassword(), "我有一只小花猫", 10).toBase64();
		mana.setManaPassword(manaPassword);
		service.insertManager(mana);
	}
	
	// 根据id删除管理员
	@RequestMapping("/manager/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer manaId) {
		service.deleteById(manaId);
	}
	
	// 根据id批量删除管理员
	@RequestMapping("/manager/deleteMany")
	public void deleteMany(HttpServletRequest request, HttpServletResponse response, int[] ids) {
		service.deleteMany(ids);
	}
	
	// 修改管理员信息
	@RequestMapping("/manager/update")
	public void updateMana(HttpServletRequest request, HttpServletResponse response, Manager mana) {
		service.updateManager(mana);
	}
	
	// 根据id查询
	@RequestMapping("/manager/get")
	@ResponseBody
	public Manager getManaById(HttpServletRequest request, HttpServletResponse response, int manaId) {
		return service.queryById(manaId);
	}
	
	//根据登录名查询
	@RequestMapping("/manager/queryByName")
	@ResponseBody
	public Manager queryByLoginName(String manaName) {
		return service.queryByLoginName(manaName);
	}
	
	//得到所有管理员,提供分页功能
	@RequestMapping("/manager/getAll")
	@ResponseBody
	public HashMap<String, Object> getAll(HttpServletRequest request, HttpServletResponse response,Integer pageNum,
			Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		int count = service.getAllAcount();
		Pager pager = new Pager(count, pageSize, pageNum);
		List<Manager> list = service.queryAll(pager);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
}
