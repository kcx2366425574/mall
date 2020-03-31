package com.kcx.mall.perm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.pojo.RolePerm;
import com.kcx.mall.perm.service.RolePermService;

public class TestRolePermService {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	RolePermService service = context.getBean(RolePermService.class);
	
	//增加角色权限
	@Test
	public void testAddRolePerm() {
		Integer[] permIds = {6, 7, 8};
		service.addRolePerm(6, permIds);
	}
	
	//删除角色权限
	@Test
	public void testDeleteRolePerm() {
		Integer[] ids = {52, 53};
		service.deleteRolePerm(ids);
	}
	
	//条件获取记录数
	@Test
	public void testGetRolePermCount() {
		int count = service.getRolePermCount(1, 1);
		System.out.println(count);
	}
	
	//条件分页查询
	@Test
	public void testQueryRolePerm() {
		Pager pager = new Pager(service.getRolePermCount(null, 2), 5, 0);
		List<RolePerm> list = service.queryRolePerm(pager, null, 2);
		for (RolePerm rolePerm : list) {
			System.out.println(rolePerm);
		}
	}
	
	//获取用户已拥有的权限
	@Test
	public void testGetRpPermIds() {
		ArrayList<Integer> list = service.getRpPermIds(2);
		System.out.println(list);
	}
	
	//获取所有用户权限
	@Test
	public void testGetRpAllPermIds() {
		ArrayList<Integer> list = service.getRpAllPermIds();
		System.out.println(list);
	}
	
	//获取已有权限集合
	@Test
	public void testSearchrpPerms() {
		List<RolePerm> list = service.searchRpPerms(2);
		for(RolePerm rolePerm : list) {
			System.out.println(rolePerm);
		}
	}
	
	//获取用户未拥有的权限
	@Test
	public void testSearchRpNot() {
		List<Permission> list = service.searchRpNot(2);
		for (Permission permission : list) {
			System.out.println(permission);
		}
	}

}
