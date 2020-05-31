package com.kcx.mall.product;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.common.Pager;
import com.kcx.mall.product.index.ProductIndexDao;
import com.kcx.mall.product.pojo.Product;
import com.kcx.mall.product.service.ProductService;

/**
 * @date 2020-05-13
 * @author kcx
 * @description 
 */
public class ProductServiceTest {

	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	ProductService service = context.getBean(ProductService.class);
	
	ProductIndexDao indexDao = context.getBean(ProductIndexDao.class);
	/**
	 * 创建员工的索引
	 */
	@Test
	public void testCreateIndex(){
		int recordCount = service.getAllCount();
		Pager pager = new Pager(recordCount, recordCount,1);
		List<Product> list = service.query(pager);
		for (Product pro : list) {
			try {
				/********** 生成索引 *************/
				// 创建索引文档
				Document document = new Document();
				document.add(new TextField("proId", String.valueOf(pro.getProId())  , Store.YES));
				document.add(new TextField("proName", pro.getProName(), Store.YES));
				document.add(new TextField("proInfo", pro.getProInfo(), Store.YES));
				// 调用索引dao
				indexDao.create(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
	}
}
