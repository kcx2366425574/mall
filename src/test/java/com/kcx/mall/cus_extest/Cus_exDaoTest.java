package com.kcx.mall.cus_extest;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.customer.dao.CusExMapper;
import com.kcx.mall.customer.dao.CustomerMapper;
import com.kcx.mall.customer.pojo.CusEx;
import com.kcx.mall.customer.pojo.Customer;

/**
 * @date 2020-03-11
 * @author kcx
 * @description dao层测试类
 */
public class Cus_exDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	CusExMapper mapper = context.getBean(CusExMapper.class);
	CustomerMapper cusMapper = context.getBean(CustomerMapper.class);
	
	/**
	 * 增加
	 */
	@Test
	public void testInsert() {
		Customer customer = cusMapper.selectById(1);
		
		CusEx cusEx = new CusEx(customer, "张三丰", "北京市海淀区", "123456789009876543");
		
		mapper.addCusEx(cusEx);
	}
	
	/**
	 * 通过用户外键id修改
	 */
	@Test
	public void testUpdateByCusId() {
		Customer customer = cusMapper.selectById(1);
		CusEx cusEx = new CusEx(customer, "张三丰", "辽宁省盛阳师", "123456789009876543");
		mapper.updateByCusId(cusEx);
	}
	
	/**
	 * 通过用户外键id查询
	 */
	@Test
	public void testQueryByCusId() {
		CusEx cusEx = mapper.queryByCusId(1);
		System.out.println(cusEx);
	}
}
