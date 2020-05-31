package com.kcx.mall.customer.dao;
/**
 * @date 2020-03-10
 * @author kcx
 * @description 
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.customer.pojo.Customer;
public interface CustomerMapper {

	//按id查询
	Customer selectById(int cusId);
	
	void updateMoney(@Param("cusId")int cusId,@Param("cusAccount")float cusAccount);
	
	//更新头像
	void updateHead(@Param("cusId")int cusId,@Param("cusPhoto")String cusPhoto);
	
	//修改密码
	void updatePwd(@Param("cusId")int cusId,@Param("cusPassword")String cusPassword);
	
	//通过登录名查询
	Customer selectByLoginName(String cusLoginName);
	
	//查询头像
	String queryHead(int cusId);
	
	//增加新用户
	void insert(Customer customer);
	
	//修改
	void update(Customer customer);
	
	//删除
	void deleteById(int cusId);
	
	//批量删除用户
	void deleteMany(int[] ids); 
		
	//查询所有用户
	List<Customer> queryAll(@Param("start") int start, @Param("pageSize") int pageSize);
}
