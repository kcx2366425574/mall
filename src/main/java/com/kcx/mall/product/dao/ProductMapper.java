package com.kcx.mall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.product.pojo.Product;

/**
 * @date 2020-04-02
 * @author kcx
 * @description 
 */
public interface ProductMapper {

	//增加商品
	void addProduct(Product product);
	
	//删除商品
	void deleteProduct(int proId);
	
	//批量删除
	void deleteMany(int[] ids);
	
	//修改
	void updateProduct(Product product);
	
	//批量审核通过
	void updateMany(int[] ids,int proManaId);
	
	//查询
	Product queryById(int proId);
	
	//动态查询
	List<Product> queryByCondition(@Param("start") int start, @Param("pageSize") int pageSize,@Param("proCusId") int proCusId,
			@Param("proShopId") int proShopId,@Param("proPtId") int proPtId);
	
	//得到动态查询总数
	int getCountByCondition(@Param("proCusId") int proCusId,
			@Param("proShopId") int proShopId,@Param("proPtId") int proPtId);
}
