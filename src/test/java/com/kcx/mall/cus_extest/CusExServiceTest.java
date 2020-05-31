package com.kcx.mall.cus_extest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.customer.pojo.CusEx;
import com.kcx.mall.customer.service.CusExService;

/**
 * @date 2020-04-10
 * @author kcx
 * @description 
 */
public class CusExServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	CusExService service = context.getBean(CusExService.class);
	
	@Test
	public void testQuery() {
		CusEx cusEx = service.queryById(1);
		System.out.println(cusEx);
	}
}
