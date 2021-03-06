package com.kcx.mall.product;
/**
 * @date 2020-04-04
 * @author kcx
 * @description 
 */

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Pager;
import com.kcx.mall.customer.dao.CustomerMapper;
import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.manager.dao.ManagerMapper;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.product.dao.ProTypeMapper;
import com.kcx.mall.product.dao.ProductMapper;
import com.kcx.mall.product.pojo.ProType;
import com.kcx.mall.product.pojo.Product;
import com.kcx.mall.shop.dao.ShopMapper;
import com.kcx.mall.shop.pojo.Shop;

public class ProductDaoTest {
	
	static String pic = "data:image/jpeg;base64,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";
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ProductMapper mapper = context.getBean(ProductMapper.class);
	ManagerMapper managerMapper = context.getBean(ManagerMapper.class);
	CustomerMapper customerMapper = context.getBean(CustomerMapper.class);
	ProTypeMapper proTypeMapper = context.getBean(ProTypeMapper.class);
	ShopMapper shopMapper = context.getBean(ShopMapper.class);
	
	/**
	 * 增加商品
	 */
	@Test
	public void testAdd() {
		Customer proCus = customerMapper.selectById(1);
		ProType proPt = proTypeMapper.queryById(1);
		Manager proMana = managerMapper.queryById(1);
		Product product = new Product("测试产品", 13.0f, "啥也没有的测试产品", proCus, null, proPt, "通过", proMana, pic);
		mapper.addProduct(product);
		
	}
	
	/**
	 * 增加商品
	 */
	@Test
	public void testAddMany() {
		for(int i=0;i<10;i++) {
			Shop shop = shopMapper.queryById(1);
			ProType proPt = proTypeMapper.queryById(13);
			Manager proMana = managerMapper.queryById(1);
			Product product = new Product("测试产品"+i, 13.0f, "啥也没有的测试产品", null, shop, proPt, "审核通过", proMana, pic);
			mapper.addProduct(product);
		}
		
		
	}
	
	/**
	 * 删除商品
	 */
	@Test
	public void testDelete() {
		mapper.deleteProduct(2);
	}
	
	/**
	 * 查询商品总数
	 */
	@Test
	public void testAllCount() {
		System.out.println(mapper.getAllCount());
	}
	
	//动态查询商品
	@Test
	public void testQueryByCondition() {
		int recordCount = mapper.getCountByCondition(null, null, null, "审核通过");
		Pager pager = new Pager(recordCount, 6, 1);
		List<Product> list = mapper.queryByCondition(pager.getStart(), pager.getPageSize(), null, null, null, "审核通过");
		for (Product product : list) {
			System.out.println(product);
		}
	}

}
