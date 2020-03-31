package com.kcx.mall.perm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.perm.dao.RoleMapper;
import com.kcx.mall.perm.pojo.Role;

public class TestRoleMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	RoleMapper mapper = context.getBean(RoleMapper.class);
	
	//增加角色
	@Test
	public void testInsert() {
		Role role = new Role("sss", "sdd");
		mapper.insert(role);
	}
	
	//删除角色
	@Test
	public void testDelete() {
		mapper.delete(13);
	}
	
	//修改角色
	@Test
	public void testUpdate() {
		Role role = new Role(12, "aaa", "sss");
		mapper.update(role);
	}
	
	//根据id查询角色
	@Test
	public void testQueryById() {
		Role role = mapper.queryById(5);
		System.out.println(role);
	}
	
	//获取记录数
	@Test
	public void testGetCount() {
		int count = mapper.getCount();
		System.out.println(count);
	}

	//分页查询
	@Test
	public void testQuery() {
		List<Role> list = mapper.query(0, 5);
		for (Role role : list) {
			System.out.println(role);
		}
	}
	
	//根据登录名查询用户角色
	@Test
	public void testQueryRole() {
		List<Map<String, Object>> list = mapper.queryRole("zhangsan");
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
        System.out.println(map);
	}
	
}
