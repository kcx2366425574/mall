package com.kcx.mall.managerTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.manager.service.ManagerService;

/**
 * @date 2020-03-12
 * @author kcx
 * @description 管理员功能dao测试类
 */
public class ManagerServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ManagerService service = context.getBean(ManagerService.class);
	
	/**
	 * 增加管理员
	 */
	@Test
	public void testadd() {
		Manager manager = new Manager("ceshi", "123456", false);
		service.insertManager(manager);
	}
	
	/**
	 * 通过id删除管理员
	 */
	@Test
	public void testDeleteById() {
		service.deleteById(3);
	}
	
	/**
	 * 批量删除
	 */
	@Test
	public void testDeleteMany() {
		for (int i = 0; i < 3; i++) {
			Manager manager = new Manager("ceshi"+i, "123456", false);
			service.insertManager(manager);
		}
		service.deleteMany(new int[] {3,4,5});
	}
	
	/**
	 * 修改管理员信息
	 */
	@Test
	public void testUpdate() {
		Manager manager = new Manager(1, "super", "12345678", false);
		service.updateManager(manager);
	}
	
	/**
	 * 通过id查询管理员信息
	 */
	@Test
	public void testQueryById() {
		Manager manager = service.queryById(1);
		System.out.println(manager);
	}
	
	/**
	 * 通过登录名查询管理员信息
	 */
	@Test
	public void testQueryByLoginName() {
		Manager manager = service.queryByLoginName("super");
		System.out.println(manager);
	}
	
	/**
	 * 查询所有管理员信息
	 */
	@Test
	public void testQueryAll() {
		List<Manager> list = service.queryAll();
		for (Manager manager : list) {
			System.out.println(manager);
		}
	}
}
