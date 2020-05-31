package com.kcx.mall.managerTest;

import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Pager;
import com.kcx.mall.manager.dao.ManagerMapper;
import com.kcx.mall.manager.pojo.Manager;

/**
 * @date 2020-03-12
 * @author kcx
 * @description 管理员功能dao测试类
 */
public class ManagerDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ManagerMapper mapper = context.getBean(ManagerMapper.class);
	
	/**
	 * 增加管理员
	 */
	@Test
	public void testadd() {
		String manaPassword = new Sha256Hash("123456", "我有一只小花猫", 10).toBase64();
		for(int i=0;i<10;i++) {
			Manager manager = new Manager("ceshi"+i, manaPassword, false,"啥事也不干"+i);
			mapper.addMana(manager);
		}
		
	}
	
	/**
	 * 通过id删除管理员
	 */
	@Test
	public void testDeleteById() {
		mapper.deleteByManaId(2);
	}
	
	/**
	 * 批量删除
	 */
	@Test
	public void testDeleteMany() {
//		for (int i = 0; i < 3; i++) {
//			Manager manager = new Manager("ceshi"+i, "123456", false);
//			mapper.addMana(manager);
//		}
		mapper.deleteMany(new int[] {3,4,5});
	}
	
	/**
	 * 修改管理员信息
	 */
	@Test
	public void testUpdate() {
		Manager manager = new Manager(1, "super", "12345678", false,"啥事也能干的超级管理员");
		mapper.update(manager);
	}
	
	/**
	 * 通过id查询管理员信息
	 */
	@Test
	public void testQueryById() {
		Manager manager = mapper.queryById(1);
		System.out.println(manager);
	}
	
	/**
	 * 通过登录名查询管理员信息
	 */
	@Test
	public void testQueryByLoginName() {
		Manager manager = mapper.queryByLoginName("super");
		System.out.println(manager);
	}
	
	/**
	 * 查询所有管理员信息
	 */
	@Test
	public void testQueryAll() {
		List<Manager> list = mapper.queryAll(1,10);
		for (Manager manager : list) {
			System.out.println(manager);
		}
	}
	
	/**
	 * 密码加密
	 */
	@Test
	public void testPwd() {
		Pager pager = new Pager(mapper.getAllAcount(), 10, 1);
		List<Manager> list = mapper.queryAll(0, pager.getPageSize());
		for (Manager manager : list) {
			manager.setManaPassword(new Sha256Hash(manager.getManaPassword(), "我有一只小花猫", 10).toBase64());
			mapper.update(manager);
		}
	}
}
