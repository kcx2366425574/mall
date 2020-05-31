package com.kcx.mall.pic;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kcx.mall.pic.dao.PicMapper;
import com.kcx.mall.pic.pojo.Pic;

/**
 * @date 2020-03-12
 * @author kcx
 * @description 图片功能dao测试类
 */
public class PicDaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	PicMapper mapper = context.getBean(PicMapper.class);
	
	/**
	 * 查询图片集合
	 */
	@Test
	public void testQuery() {
		List<Pic> list = mapper.queryByProId(2);
		for (Pic pic : list) {
			System.out.println(pic);
		}
	}
}
