package com.kcx.mall.customer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.customer.pojo.CusEx;

/**
 * @date 2020-03-11
 * @author kcx
 * @description 
 */
public interface CusExMapper {

	//增加
	void addCusEx(CusEx cusEx);
	
	//通过主键删除
	void deleteCusExById(int ceId);
	
	//通过外键用户id删除
	void deleteCusExByCus(int ceCusId);
	
	//通过外键用户id修改
	void updateByCusId(CusEx cusEx);
	
	//通过外键用户id查询
	CusEx queryByCusId(int ceCusId);
	
	//查询所有用户扩展
	List<CusEx> queryAll(@Param("start") int start, @Param("pageSize") int pageSize);
}
