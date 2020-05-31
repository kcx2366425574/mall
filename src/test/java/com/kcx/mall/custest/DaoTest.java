package com.kcx.mall.custest;

import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Constant;
import com.kcx.mall.customer.dao.CustomerMapper;
import com.kcx.mall.customer.pojo.Customer;

/**
 * @date 2020-03-10
 * @author kcx
 * @description 
 */
public class DaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	CustomerMapper mapper = context.getBean(CustomerMapper.class);
	
	/**
	 * 根据id查询
	 */
	@Test
	public void testSelectById() {
		Customer customer = mapper.selectById(1);
		System.out.println(customer);
	}
	
	/**
	 * 查询所有用户
	 */
	@Test
	public void testQueryAll() {
		List<Customer> list = mapper.queryAll(0, 5);
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}
	
	/**
	 * 新增用户
	 */
	@Test
	public void testInsert() {
		Customer customer = new Customer("liyi", "123456", false, 99.0f,"测试数据，联系方式没有", Constant.PHOTO_STRING);
		mapper.insert(customer);
	}
	
	/**
	 * 批量新增用户
	 */
	@Test
	public void testAddMany() {
		String cusPassword = new Sha256Hash("123456", "我有一只小花猫", 10).toBase64();
		for (int i = 0;i<20;i++) {
			float account = (float) (Math.random()*1000.0);
			Customer customer = new Customer("ceshi"+i, cusPassword, false, account, "测试数据，联系方式没有", Constant.PHOTO_STRING);
			mapper.insert(customer);
		}
	}
	
	/**
	 * 修改用户信息
	 */
	@Test
	public void testupdate() {
		Customer customer  = mapper.selectById(2);
		
		customer.setCusContanctInfo("怎么可能没有联系方式呢，13112345678");
		
		mapper.update(customer);
	}

	/**
	 * 删除用户
	 */
	@Test
	public void testdeleteById() {
		mapper.deleteById(4);
	}
	
	/**
	 * 查询头像
	 */
	@Test
	public void testGetHead() {
		String head = mapper.queryHead(1);
		System.out.println(head);
	}
	
	
	
	
}
