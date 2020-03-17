package com.kcx.mall.customer.dao;
/**
 * @date 2020-03-10
 * @author kcx
 * @description 
 */

import com.kcx.mall.customer.pojo.Customer;

public interface CustomerMapper {

	//按id查询
	Customer selectById(int cusId);
	
	//增加新用户
	void insert(Customer customer);
	
	//修改
	void update(Customer customer);
	
	//删除
	void deleteById(int cusId);
	
	//批量删除用户
	void deleteMany(int[] ids); 
		
	
}
