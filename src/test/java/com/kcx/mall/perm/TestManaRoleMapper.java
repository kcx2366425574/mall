package com.kcx.mall.perm;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.perm.dao.ManaRoleMapper;
import com.kcx.mall.perm.pojo.ManaRole;
import com.kcx.mall.perm.pojo.Role;

public class TestManaRoleMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	ManaRoleMapper mapper = context.getBean(ManaRoleMapper.class);
	
	//增加用户角色
	@Test
	public void testInsert() {
		Manager mana = new Manager();
		mana.setManaId(1);
		
		Role role = new Role();
		role.setRoleId(1);
		
		ManaRole manaRole = new ManaRole(mana, role);
		mapper.insert(manaRole);
	}
	
	//删除用户角色
	@Test
	public void testDelete() {
		Integer[] ids = {4, 5};
		mapper.delete(ids);
	}
	
	//条件获取记录数
	@Test
	public void testGetCount() {
		int count = mapper.getCount(null, 1);
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQuery() {
		List<ManaRole> list = mapper.query(0, 4, null, null);
		for (ManaRole ManaRole : list) {
			System.out.println(ManaRole);
		}
	}

}
