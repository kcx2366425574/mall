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
import org.springframework.boot.web.servlet.server.Session;
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
		return 3;
	}
	
	// 增加管理员
	@RequestMapping("/manager/add")
	public void addManager(HttpServletRequest request, HttpServletResponse response, Manager mana) {
		// 密码加密
		mana.setManaIson(false);
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
	public void updateMana(HttpServletRequest request, HttpServletResponse response, int manaId,String manaInfo) {
		Manager manager = service.queryById(manaId);
		manager.setManaInfo(manaInfo);
		service.updateManager(manager);
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
	public Manager queryByLoginName(HttpServletRequest request, HttpServletResponse response,String manaName) {
		return service.queryByLoginName(manaName);
	}
	
	//检测登录名
	@RequestMapping("/manager/checkLoginName")
	@ResponseBody
	public Boolean checkLoginName(HttpServletRequest request, HttpServletResponse response,String loginName) {
		Manager manager = service.queryByLoginName(loginName);
		if (manager == null)
			return true;
		return false;
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
	
	
	//获取头像
	@RequestMapping("/manager/getHead")
	@ResponseBody
	public String getHead(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int manaId = (int) session.getAttribute("manaId");
		return service.getHead(manaId);
	}
	
	//更新头像
	@RequestMapping("/manager/updateHead")
	@ResponseBody
	public void updateHead(HttpServletRequest request, HttpServletResponse response,String manaPhoto) {
		HttpSession session = request.getSession();
		int manaId = (int) session.getAttribute("manaId");
		service.updateHead(manaId, manaPhoto);
	}
	
	//获取头像
	@RequestMapping("/manager/updatePwd")
	@ResponseBody
	public boolean updatePwd(HttpServletRequest request, HttpServletResponse response,String newManaPwd,String oldManaPwd) {
		String manaPwd = new Sha256Hash(oldManaPwd, "我有一只小花猫", 10).toBase64();
		HttpSession session = request.getSession();
		int manaId = (int) session.getAttribute("manaId");
		Manager mana = service.queryById(manaId);
		if(!mana.getManaPassword().equals(manaPwd)) {
			return false;
		}
		String newPwd = new Sha256Hash(newManaPwd, "我有一只小花猫", 10).toBase64();
		service.updatePwd(manaId, newPwd);
		return true;
	}
	
	//获取id
	@RequestMapping("/manager/getName")
	@ResponseBody
	public String getName(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("loginName");
	}
}
