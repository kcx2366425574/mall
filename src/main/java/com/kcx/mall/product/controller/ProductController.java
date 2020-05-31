package com.kcx.mall.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Pager;
import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.customer.service.CustomerService;
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
		System.out.println(product);
		if(product.getProPt().getPtId()==null) return;
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
		product.setProState("未审核");
		service.addProduct(product);
	}
	
	// 查询所有
	@RequestMapping("/product/getAll")
	@ResponseBody
	public HashMap<String, Object> getAll(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 6;
		int count = service.getAllCount();
		
		Pager pager = new Pager(count, pageSize, pageNum);
		List<Product> list = service.query(pager);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	// 动态查询查询所有
	@RequestMapping("/product/getByCondition")
	@ResponseBody
	public HashMap<String, Object> getByCondition(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize,Integer proCusId,Integer proShopId,Integer proPtId,String proState,boolean needId) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 6;
		if(needId==true) {
			HttpSession session = request.getSession();
			proCusId = (int)session.getAttribute("loginId");
		}
		System.out.println("这是proCusId:   "+proCusId);
		int count = service.getCountByCondition(proCusId, proShopId, proPtId,proState);
		
		Pager pager = new Pager(count, pageSize, pageNum);
		List<Product> list = service.queryByCondition(pager, proCusId, proShopId, proPtId, proState);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	// 动态查询查询所有
	@RequestMapping("/product/getByConditionNoPager")
	@ResponseBody
	public List<Product> getByConditionNoPager(HttpServletRequest request, HttpServletResponse response, 
			Integer proCusId,Integer proShopId,Integer proPtId,String proState,boolean needId) {
		if(needId==true) {
			HttpSession session = request.getSession();
			proCusId = (int)session.getAttribute("loginId");
		}
		
		List<Product> list = service.queryByConditionNoPager(proCusId, proShopId, proPtId, proState);
		
		return list;
	}
	
	// 查询图片
	@RequestMapping("/product/queryPic")
	@ResponseBody
	public String getPic(HttpServletRequest request, HttpServletResponse response, int proId) {
		return service.getPic(proId);
	}
	
	// 查询商品
	@RequestMapping("/product/queryById")
	@ResponseBody
	public Product getProduct(HttpServletRequest request, HttpServletResponse response, int proId) {
		return service.queryPro(proId);
	}
	
	// 查询商品含图片
	@RequestMapping("/product/queryTotalById")
	@ResponseBody
	public Product getTotalProduct(HttpServletRequest request, HttpServletResponse response, int proId) {
		return service.queryTotalPro(proId);
	}
	
	// 修改商品（购买专用）
	@RequestMapping("/product/updateState")
	public void updateProduct(HttpServletRequest request, HttpServletResponse response, int proId) {
		service.UpdateStatement(proId);
	}
	
	/**
	 * 全文检索商品
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidTokenOffsetsException
	 */
	@RequestMapping("/product/queryByIndex")
	@ResponseBody
	public List<Product> queryByIndex(HttpServletRequest request, HttpServletResponse response, String queryStr)
			throws ParseException, IOException, InvalidTokenOffsetsException {

		return service.queryProByIndex(queryStr);
	}
}
