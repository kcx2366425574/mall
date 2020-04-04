package com.kcx.mall.product;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.product.dao.ProTypeMapper;
import com.kcx.mall.product.pojo.ProType;

/**
 * @date 2020-04-01
 * @author kcx
 * @description 
 */
public class ProTypeDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ProTypeMapper mapper = context.getBean(ProTypeMapper.class);
	
	/**
	 * 增加商品类型
	 */
	@Test
	public void testAdd() {
		ProType proType = new ProType("鞋靴", "各种各样的鞋靴");
		mapper.add(proType);
	}
	
	/**
	 * 查询
	 */
	@Test
	public void testQueryById() {
		ProType proType = mapper.queryById(1);
		System.out.println(proType);
	}
	
	/**
	 * 修改
	 */
	@Test
	public void testUpdate() {
		ProType proType = mapper.queryById(1);
		proType.setPtInfo("各种各样的女装！");
		mapper.update(proType);
	}
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete() {
		mapper.deleteById(4);
	}
	
	/**
	 * 得到商品类型种数
	 */
	@Test
	public void testGetCount() {
		System.out.println(mapper.getAllAcount());
	}
}
