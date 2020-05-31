package com.kcx.mall.cus_extest;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.customer.dao.CusRecordMapper;
import com.kcx.mall.customer.dao.CustomerMapper;
import com.kcx.mall.customer.pojo.CusRecord;
import com.kcx.mall.customer.pojo.Customer;

/**
 * @date 2020-03-25
 * @author kcx
 * @description 
 */
public class Cus_recordDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CusRecordMapper mapper = context.getBean(CusRecordMapper.class);
	CustomerMapper cusMapper = context.getBean(CustomerMapper.class);
	
	/**
	 * 增加记录
	 */
	@Test
	public void testAdd() {
		Customer crCus = cusMapper.selectById(1);
		Date date = new Date();
		CusRecord cusRecord = new CusRecord(crCus, 30.0f, 48.0f, date);
		mapper.addCusRecord(cusRecord);
	}
	
	/**
	 * 通过id主键查询记录
	 */
	@Test
	public void testQueryById() {
		CusRecord record = mapper.queryById(1);
		System.out.println(record);
	}
	
	/**
	 * 查询所有记录
	 */
	@Test
	public void testQueryAll() {
		List<CusRecord> list = mapper.queryAll(0,5);
		for (CusRecord cusRecord : list) {
			System.out.println(cusRecord);
		}
	}
	
	/**
	 * 动态查询
	 */
	@Test
	public void testQueryByCondition() {
		List<CusRecord> list = mapper.queryByCondition(0, 5,0,null,new Date());
		for (CusRecord cusRecord : list) {
			System.out.println(cusRecord);
		}
	}

}
