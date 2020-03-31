package com.kcx.mall.perm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.perm.dao.RolePermMapper;
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.pojo.RolePerm;

public class TestRolePermMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	RolePermMapper mapper = context.getBean(RolePermMapper.class);
	
	//增加角色权限
	@Test
	public void testInsert() {
		Permission perm = new Permission();
		perm.setPermId(5);
		Role role = new Role();
		role.setRoleId(5);
		
		RolePerm rolePerm = new RolePerm(role, perm);
		mapper.insert(rolePerm);
		
	}
	
	//删除角色权限
	@Test
	public void testDelete() {
		Integer[] ids = {41, 42};
		mapper.delete(ids);
	}
	
	//条件获取记录数
	@Test
	public void testGetCount() {
		int count = mapper.getCount(null, 4);
		System.out.println(count);
	}
	
	//条件分页查询
	@Test
	public void testQuery() {
		List<RolePerm> list = mapper.query(0, 5, null, null);
		for (RolePerm rolePerm : list) {
			System.out.println(rolePerm);
		}
	}
	
	//查看每个角色已拥有的权限id数组
	@Test
	public void testGetPermIds() {
		ArrayList<Integer> list = mapper.getPermIds(2);
		System.out.println(list);
	}
	
	//获得所有用户权限id数组
	@Test
	public void testGetAllPermIds() {
		ArrayList<Integer> list = mapper.getAllPermIds();
		System.out.println(list);
	}
	
	//查询已拥有权限集合 
	@Test
	public void testsearchPerms() {
		List<RolePerm> list = mapper.searchPerms(2);
		for(RolePerm rolePerm : list) {
			System.out.println(rolePerm);
		}
	}

}
