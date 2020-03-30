package com.kcx.mall.perm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.pojo.RolePerm;
import com.kcx.mall.perm.service.RolePermService;

@Controller
public class RolePermController {
	
	@Autowired
	private RolePermService service;
	
	//增加角色权限
	@RequestMapping("/rp/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Integer roleId, Integer[] permIds) {
		service.addRolePerm(roleId, permIds);
	}
	
	//删除角色权限
	@RequestMapping("/rp/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		service.deleteRolePerm(ids);
	}
	
	//条件分页查询
	@RequestMapping("/rp/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, Integer roleId, Integer permId) {
		
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		
		Pager pager = new Pager(service.getRolePermCount(roleId, permId), pageSize, pageNum);
		List<RolePerm> list = service.queryRolePerm(pager, roleId, permId);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	//获取用户已拥有的权限
	@RequestMapping("/rp/searchPerms")
	@ResponseBody
	public List<RolePerm> searchPerms(HttpServletRequest request, HttpServletResponse response, Integer roleId) {
		return service.searchRpPerms(roleId);
	}
	
	//获取用户未拥有的权限
	@RequestMapping("/rp/searchNone")
	@ResponseBody
	public List<Permission> searchNone(HttpServletRequest request, HttpServletResponse response, Integer roleId) {
		return service.searchRpNot(roleId);
	}

}
