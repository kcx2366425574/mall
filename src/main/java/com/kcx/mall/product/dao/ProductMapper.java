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
	
	//批量购买
	void buyMany(int[] ids);
	//查询
	Product queryById(int proId);
	
	//修改
	void updateState(int proId);
	
	//查询
	Product queryTotalById(int proId);
	
	//动态查询
	List<Product> queryByCondition(@Param("start") int start, @Param("pageSize") int pageSize,@Param("proCusId") Integer proCusId,
			@Param("proShopId") Integer proShopId,@Param("proPtId") Integer proPtId,@Param("proState")String proState);
	
	//动态查询无分页
	List<Product> queryByConditionNoPager(@Param("proCusId") Integer proCusId,
			@Param("proShopId") Integer proShopId,@Param("proPtId") Integer proPtId,@Param("proState")String proState);
	
	//得到动态查询总数
	int getCountByCondition(@Param("proCusId") Integer proCusId,
			@Param("proShopId") Integer proShopId,@Param("proPtId") Integer proPtId,@Param("proState")String proState);
	
	//查看商品图片
	String queryPhoto(int proId);
	
	//查询发布总数
	int getAllCount();
	
	//查询所有无条件
	List<Product> query(@Param("start") int start, @Param("pageSize") int pageSize);

	int getLastInsertId();
}
