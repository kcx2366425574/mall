package com.kcx.mall.perm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Pager;
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.service.PermissionService;

public class TestPermissionService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	PermissionService service = context.getBean(PermissionService.class);
	
	//增加权限
	@Test
	public void testAddPerm() {
		Permission permission = new Permission("fff", "ppp");
		service.addPerm(permission);
	}
	
	//删除权限
	@Test
	public void testDeletePerm() {
		service.deletePerm(39);
	}
	
	//修改权限
	@Test
	public void testUpdatePerm() {
		Permission permission = new Permission(37, "rrr", "hhh");
		service.updatePerm(permission);
	}
	
	//根据id查询权限
	@Test
	public void testQueryPermById() {
		Permission permission = service.queryPermById(6);
		System.out.println(permission);
	}
	
	//分页查询
	@Test
	public void testQueryPerm() {
		Pager pager = new Pager(service.getPermCount(null, null), 5, 2);
		List<Permission> list = service.queryAllPerm(pager.getStart(), pager.getPageSize(), null, null);
		for (Permission permission : list) {
			System.out.println(permission);
		}
	}
	
	//根据登录名查询用户权限
	@Test
	public void testQueryEmpPerm() {
		List<Map<String, Object>> list = service.queryPerm("zhangsan");
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		map.put("list", list);
		System.out.println(map);
	}
	
	//查询用户未拥有的权限集合
	@Test
	public void testSearchPermNot() {
		Integer[] ids = {2, 3, 4};
		List<Permission> list = service.searchPermNot(ids);
		for(Permission permission : list) {
			System.out.println(permission);
		}
	}
	
}
