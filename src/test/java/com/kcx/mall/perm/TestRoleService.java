package com.kcx.mall.perm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.service.RoleService;

public class TestRoleService {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	RoleService service = context.getBean(RoleService.class);
	
	//增加角色
	@Test
	public void testAddRole() {
		Role role = new Role("333", "dfd");
		service.addRole(role);
	}
	
	//删除角色
	@Test
	public void testDeleteRole() {
		service.deleteRole(14);
	}
	
	//修改角色
	@Test
	public void testUpdateRole() {
		Role role = new Role(12, "ppp", "dsf");
		service.updateRole(role);
	}
	
	//根据id查询角色
	@Test
	public void testQueryRoleById() {
		Role role = service.queryRoleById(6);
		System.out.println(role);
	}
	
	//分页查询
	@Test
	public void testQueryRole() {
		Pager pager = new Pager(service.getRoleCount(), 5, 0);
		List<Role> list = service.queryAllRole(pager.getStart(), pager.getPageSize());
		for (Role role : list) {
			System.out.println(role);
		}
	}
	
	//根据登录名查询用户权限
	@Test
	public void testQueryEmpRole() {
		List<Map<String, Object>> list = service.queryRole("zhangsan");
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		map.put("list", list);
		System.out.println(map);
	}
	
	//查询用户未拥有的角色集合
	@Test
	public void testSearchRoleNot() {
		Integer[] ids = {2, 3, 4};
		List<Role> list = service.searchRoleNot(ids);
		for(Role role : list) {
			System.out.println(role);
		}
	}

}
