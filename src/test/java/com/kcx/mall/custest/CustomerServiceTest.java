package com.kcx.mall.custest;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Constant;
import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.customer.service.CustomerService;

/**
 * @date 2020-05-13
 * @author kcx
 * @description 
 */
public class CustomerServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	CustomerService service = context.getBean(CustomerService.class);
	
	/**
	 * 批量新增用户
	 */
	@Test
	public void testAddMany() {
		String cusPassword = new Sha256Hash("123456", "我有一只小花猫", 10).toBase64();
		for (int i = 20;i<0;i++) {
			float account = (float) (Math.random()*1000.0);
			Customer customer = new Customer("ceshi"+i, cusPassword, false, account, "测试数据，联系方式没有", Constant.PHOTO_STRING);
			service.addCus(customer);
		}
	}
	
	//测试用户登录
	@Test
	public void testLogin() {			
		int checkNum = service.checkLogin("zhangsan", "123456");
		System.out.println(checkNum);
	}
}
