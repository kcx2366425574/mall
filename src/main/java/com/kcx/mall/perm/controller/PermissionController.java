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
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.service.PermissionService;

@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService service;
	
	//增加权限
	@RequestMapping("/perm/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Permission permission) {
		service.addPerm(permission);
	} 
	
	//删除权限
	@RequestMapping("/perm/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer permId) {
		service.deletePerm(permId);
	}
	
	//修改权限
	@RequestMapping("/perm/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Permission permission) {
		service.updatePerm(permission);
	}
	
	//根据id查询权限
	@RequestMapping("/perm/get")
	@ResponseBody
	public Permission get(HttpServletRequest request, HttpServletResponse response, Integer permId) {
		return service.queryPermById(permId);
	}
	
	//分页查询权限
	@RequestMapping("/perm/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize, 
			Integer pageNum, String permName, String permInfo) {
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = service.getPermCount(null, null);
		
		Pager pager = new Pager(service.getPermCount(permName, permInfo), pageSize, pageNum);
		List<Permission> list =  service.queryAllPerm(pager.getStart(), pager.getPageSize(), permName, permInfo);
		
		// 在Map集合中存储分页数据和库存数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	//查询用户未拥有的权限
	@RequestMapping("/perm/searchNot")
	@ResponseBody
	public List<Permission> searchNot(Integer[] ids) {
		return service.searchPermNot(ids);
	}
	
	//根据登录名查询用户权限
	@RequestMapping("/perm/queryByEmp")
	@ResponseBody
	public HashMap<String, Object> queryByEmp(HttpServletRequest request, HttpServletResponse response, Integer pageSize) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		List<Map<String, Object>> list = service.queryPerm(empLoginName);
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", empLoginName);
		map.put("list", list);
		return map;
	}

}
