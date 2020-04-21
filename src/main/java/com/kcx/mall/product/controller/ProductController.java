package com.kcx.mall.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.customer.service.CustomerService;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.product.pojo.Product;
import com.kcx.mall.product.service.ProductService;
import com.kcx.mall.shop.pojo.Shop;
import com.kcx.mall.shop.service.ShopService;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private CustomerService cusService;
	
	@Autowired
	private ShopService shopService;
	// 增加商品
	@RequestMapping("/product/add")
	public void addProduct(HttpServletRequest request, HttpServletResponse response, Product product) {
		HttpSession session = request.getSession();
		String loginType = (String) session.getAttribute("loginType");
		String loginName = (String) session.getAttribute("loginName");
		
		if(loginType.equals("customer")) {
			Customer customer = cusService.queryByLoginName(loginName);
			product.setProCus(customer);
		} else if (loginType.equals("shop")) {
			Shop shop = shopService.queryByName(loginName);
			product.setProShop(shop);
		}
		
		service.addProduct(product);
	}
}
