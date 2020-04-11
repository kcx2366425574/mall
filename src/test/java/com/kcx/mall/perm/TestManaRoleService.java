package com.kcx.mall.perm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Pager;
import com.kcx.mall.perm.pojo.ManaRole;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.service.ManaRoleService;

public class TestManaRoleService {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	ManaRoleService service = context.getBean(ManaRoleService.class);
	
	//增加用户角色
	@Test
	public void testAddManaRole() {
		Integer[] roleIds = {1, 2, 3};
		service.addManaRole(5, roleIds);
	}
	
	//删除用户角色
	@Test
	public void testDeleteManaRole() {
		Integer[] ids = {14};
		service.deleteManaRole(ids);
	}
	
	//根据用户id删除
	@Test
	public void testDeleteManaRoleByMana() {
		service.deleteManaRoleByMana(4);
	}
	
	//条件查询
	@Test
	public void testQueryManaRole() {
		Pager pager = new Pager(service.getManaRoleCount(null, null), 5, 0);
		List<ManaRole> list = service.queryManaRole(pager, null, null);
		for(ManaRole ManaRole : list) {
			System.out.println(ManaRole);
		}
	}
	
	//获取用户已拥有的角色
	@Test
	public void testGetUrRoleIds() {
		ArrayList<Integer> list = service.getMrRoleIds(2);
		System.out.println(list);
	}
	
	//获取所有用户角色
	@Test
	public void testGetUrAllRoleIds() {
		ArrayList<Integer> list = service.getMrAllRoleIds();
		System.out.println(list);
	}
	
	//获取已有角色集合
	@Test
	public void testSearchUrRoles() {
		List<ManaRole> list = service.searchMrRoles(2);
		for(ManaRole ManaRole : list) {
			System.out.println(ManaRole);
		}
	}
	
	//获取用户未拥有的角色
	@Test
	public void testSearchUrNot() {
		List<Role> list = service.searchRpNot(2);
		for (Role role : list) {
			System.out.println(role);
		}
	}

}
