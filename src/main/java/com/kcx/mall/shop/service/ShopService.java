package com.kcx.mall.shop.service;
/**
 * @date 2020-04-17
 * @author kcx
 * @description 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.shop.dao.ShopMapper;
import com.kcx.mall.shop.pojo.Shop;

@Service
public class ShopService {

	@Autowired
	private ShopMapper mapper;
	
	//通过id查询
	@Transactional(readOnly=true)
	public Shop queryById(int shopId) {
		return mapper.queryById(shopId);
	}
	
	//通过商店名查询
	@Transactional(readOnly=true)
	public Shop queryByName(String shopMinName) {
		return mapper.queryByName(shopMinName);
	}

	/**
	 * 登录验证
	 * 返回1 用户名不存在 2 密码错误 3 登录成功
	 */
	public int checkLogin(String shopMinName,String shopPassword) {
		
		Shop shop = mapper.queryByName(shopMinName);
		
		if (shop == null)
			return 1;
		else if ( !shopPassword.equals(shop.getShopPassword()))
			return 2;
		else{
			return 3;
		}
	}
}
