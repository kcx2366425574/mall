package com.kcx.mall.perm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.perm.dao.PermissionMapper;
import com.kcx.mall.perm.pojo.Permission;

public class TestPermissionMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	PermissionMapper mapper = context.getBean(PermissionMapper.class);
	
	//增加权限
	@Test
	public void testInsert() {
		Permission permission = new Permission("aaa", "aaa");
		mapper.insert(permission);
	}
	
	//删除权限
	@Test
	public void testDelete() {
		mapper.delete(38);
	}
	
	//修改权限
	@Test
	public void testUpdate() {
		Permission permission = new Permission(37, "qqq", "www");
		mapper.update(permission);
	}
	
	//获取记录数
	@Test
	public void testGetCount() {
		int count = mapper.getCount(null, "部门");
		System.out.println(count);
	}
	
	//根据id查询权限
	@Test
	public void testQueryById() {
		Permission permission = mapper.queryById(6);
		System.out.println(permission);
	}
	
	//分页查询权限
	@Test
	public void testQuery() {
		List<Permission> list = mapper.query(0, 5, null, null);
		for (Permission permission : list) {
			System.out.println(permission);
		}
	}
	
	/**
	 * 通过登录名返回对应的所有权限名称
	 */
	@Test
	public void testQueryPerm() {
		List<Map<String, Object>> list = mapper.queryPerm("zhangsan");
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
        System.out.println(map);
	}

}
