package com.kcx.mall.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.customer.dao.CusExMapper;
import com.kcx.mall.customer.pojo.CusEx;

/**
 * @date 2020-03-30
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CusExService {

	@Autowired
	private CusExMapper mapper;
	
	
	
	//根据id删除
	public void deleteById(int cusExId) {
		mapper.deleteCusExById(cusExId);
	}
	
	//添加
	public void addCusEx(CusEx cusEx) {
		mapper.addCusEx(cusEx);
	}
	
	//修改信息
	public void updateCusEx(CusEx cusEx) {
		mapper.updateByCusId(cusEx);
	}
	
	//根据id查询用户
	@Transactional(readOnly=true)
	public CusEx queryById(int cusId) {
		return mapper.queryByCusId(cusId);
	}
	
	//查询所有用户扩展
	@Transactional(readOnly=true)
	public List<CusEx> queryAll(Pager pager){
		return mapper.queryAll(pager.getStart(),pager.getPageSize());
	}
	
	
}
