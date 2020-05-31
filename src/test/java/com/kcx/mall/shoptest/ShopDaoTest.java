package com.kcx.mall.shoptest;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Constant;
import com.kcx.mall.manager.dao.ManagerMapper;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.shop.dao.ShopMapper;
import com.kcx.mall.shop.pojo.Shop;

/**
 * @date 2020-03-14
 * @author kcx
 * @description 
 */
public class ShopDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ShopMapper mapper = context.getBean(ShopMapper.class);
	ManagerMapper manaMapper = context.getBean(ManagerMapper.class);
	/**
	 * 增加商店
	 */
	@Test
	public void testAdd() {
		Shop shop = new Shop("淘宝", "taobao", "123456", "收各种二手商品，尤其是电子产品", "杭州", "马云", "123456788909874257", 1092.7f, "审核中");
		mapper.addShop(shop);
	}
	
	/**
	 * 修改商店信息
	 */
	@Test
	public void testUpdate() {
		Shop shop = mapper.queryById(1);
		shop.setShopMinname("taobaowang");
		mapper.update(shop);
	}
	
	/**
	 * 查询商家信息
	 */
	@Test 
	public void testQuery() {
		Shop shop = mapper.queryById(1);
		System.out.println(shop);
	}
	
	/**
	 * 批量增加图片
	 */
	@Test
	public void testAddMany() {
		Manager manager = manaMapper.queryById(1);
		for(int i=0;i<10;i++) {
			
			Shop shop = new Shop("测试商家"+i, "ceshi"+i, "123456", "测试商家，啥都能收"+i, "在中国就行"+i, "某某某", "36242919991012111"+i, 666.0f, "审核通过", manager,Constant.PHOTO_STRING);
			mapper.addShop(shop);
		}
	}
}
