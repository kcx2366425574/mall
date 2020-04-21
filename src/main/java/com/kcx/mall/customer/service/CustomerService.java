package com.kcx.mall.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.customer.dao.CustomerMapper;
import com.kcx.mall.customer.pojo.Customer;

/**
 * @date 2020-03-30
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService {

	@Autowired
	private CustomerMapper mapper;
	
	/**
	 * 登录验证
	 * 返回1 用户名不存在 2 密码错误 3 登录成功 4账号已在别处登录
	 */
	public int checkLogin(String cusLoginName,String cusPassword) {
		
		Customer cus = mapper.selectByLoginName(cusLoginName);
		
		if (cus == null)
			return 1;
		else if ( !cusPassword.equals(cus.getCusPassword()))
			return 2;
		else{
			/**
			 * 如果不在线，需要更新登录状态，并返回登录成功
			 */
			if(!cus.getCusIson()) {
				cus.setCusIson(true);
				mapper.update(cus);
				return 3;
			}else {
				return 4;
			}
		}
	}
	//批量删除用户
	public void deleteMany(int[] ids){
		
		mapper.deleteMany(ids);
	}
	
	//根据id删除用户
	public void deleteById(int cusId) {
		mapper.deleteById(cusId);
	}
	
	//添加用户
	public void addCus(Customer customer) {
		mapper.insert(customer);
	}
	
	//修改用户信息
	public void updateCus(Customer customer) {
		mapper.update(customer);
	}
	
	//根据id查询用户
	@Transactional(readOnly=true)
	public Customer queryById(int cusId) {
		return mapper.selectById(cusId);
	}
	
	//查询所有用户
	@Transactional(readOnly=true)
	public List<Customer> queryAll(Pager pager){
		return mapper.queryAll(pager.getStart(),pager.getPageSize());
	}
	
	//根据登录名查询用户
	@Transactional(readOnly=true)
	public Customer queryByLoginName(String cusLoginName) {
		return mapper.selectByLoginName(cusLoginName);
	}
	
}
