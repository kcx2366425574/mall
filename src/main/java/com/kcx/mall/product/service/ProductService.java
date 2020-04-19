package com.kcx.mall.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.pic.pojo.Pic;
import com.kcx.mall.product.dao.ProductMapper;
import com.kcx.mall.product.pojo.Product;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ProductService {

	@Autowired
	private ProductMapper mapper;
	
	//增加产品
	public void addProduct(Product product) {
		mapper.addProduct(product);
	}
	
	//删除产品
	public void deleteById(int proId) {
		mapper.deleteProduct(proId);
	}
	
	//批量删除
	public void deleteMany(int[] ids) {
		mapper.deleteMany(ids);
	}
	
	//批量审核通过
	public void updateMany(int[] ids,int proManaId) {
		mapper.updateMany(ids, proManaId);
	}
	
	//查询商品
	@Transactional(readOnly=true)
	public Product queryPro(int proId) {
		return mapper.queryById(proId);
	}
	
	//修改商品信息
	public void updatePro(Product product) {
		mapper.updateProduct(product);
	}
	
	//得到条件查询员工数量
	@Transactional(readOnly = true)
	public int getCountByCondition(int proCusId, int proShopId, int proPtId) {
		return mapper.getCountByCondition(proCusId, proShopId, proPtId);
	}
	
	//动态查询
	@Transactional(readOnly = true)
	public List<Product> queryByCondition(Pager pager,int proCusId, int proShopId, int proPtId){
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), proCusId, proShopId, proPtId);
	}
}
