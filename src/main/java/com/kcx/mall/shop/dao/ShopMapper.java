package com.kcx.mall.shop.dao;

import com.kcx.mall.shop.pojo.Shop;

/**
 * @date 2020-03-14
 * @author kcx
 * @description 商家dao层
 */
public interface ShopMapper {

	//增加商店
	void addShop(Shop shop); 
	
	//根据id删除商店
	void deleteShop(int shopId);
	
	//批量删除
	void deleteMany(int[] ids);
	
	//修改商店信息
	void update(Shop shop);
	
	//查询商店信息
	Shop queryById(int shopId);
}
