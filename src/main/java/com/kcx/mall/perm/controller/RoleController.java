package com.kcx.mall.perm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Pager;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	//增加角色
	@RequestMapping("/role/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Role role) {
		service.addRole(role);
	} 
	
	//删除角色
	@RequestMapping("/role/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer roleId) {
		service.deleteRole(roleId);
	}
	
	//修改权限
	@RequestMapping("/role/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Role role) {
		service.updateRole(role);
	}
	
	//根据id查询角色
	@RequestMapping("/role/get")
	@ResponseBody
	public Role get(HttpServletRequest request, HttpServletResponse response, Integer roleId) {
		return service.queryRoleById(roleId);
	}
	
	//分页查询角色
	@RequestMapping("/role/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		
		Pager pager = new Pager(service.getRoleCount(), pageSize, pageNum);
		List<Role> list = service.queryAllRole(pager.getStart(), pager.getPageSize());
		
		// 在Map集合中存储分页数据和库存数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	//查询用户未拥有的角色
	@RequestMapping("/role/searchNot")
	@ResponseBody
	public List<Role> searchNot(Integer[] ids) {
		return service.searchRoleNot(ids);
	}
	
	//根据登录名查询用户角色
	@RequestMapping("/role/queryByEmp")
	@ResponseBody
	public HashMap<String, Object> queryByEmp(HttpServletRequest request, HttpServletResponse response, Integer pageSize) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		List<Map<String, Object>> list = service.queryRole(empLoginName);
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", empLoginName);
		map.put("list", list);
		return map;
	}
}
