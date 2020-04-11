package com.kcx.mall.perm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Pager;
import com.kcx.mall.perm.pojo.ManaRole;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.service.ManaRoleService;

@Controller
public class ManaRoleController {
	
	@Autowired
	private ManaRoleService service;
	
	//增加用户角色
	@RequestMapping("/mr/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Integer ManaId, Integer[] roleIds) {
		service.addManaRole(ManaId, roleIds);
	}
	
	//删除用户角色
	@RequestMapping("/mr/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		service.deleteManaRole(ids);
	}
	
	//根据用户id删除
	@RequestMapping("/mr/deleteByMana")
	public void deleteByMana(HttpServletRequest request, HttpServletResponse response, Integer ManaId) {
		service.deleteManaRoleByMana(ManaId);
	}
	
	//条件分页查询
	@RequestMapping("/mr/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, Integer ManaId, Integer roleId) {
		
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		
		Pager pager = new Pager(service.getManaRoleCount(ManaId, roleId), pageSize, pageNum);
		List<ManaRole> list = service.queryManaRole(pager, ManaId, roleId);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}
	
	//获取用户已拥有的角色
	@RequestMapping("/mr/searchRoles")
	@ResponseBody
	public List<ManaRole> searchPerms(HttpServletRequest request, HttpServletResponse response, Integer ManaId) {
		return service.searchMrRoles(ManaId);
	}
	
	//获取用户未拥有的角色
	@RequestMapping("/mr/searchNone")
	@ResponseBody
	public List<Role> searchNone(HttpServletRequest request, HttpServletResponse response, Integer ManaId) {
		return service.searchRpNot(ManaId);
	}


}
