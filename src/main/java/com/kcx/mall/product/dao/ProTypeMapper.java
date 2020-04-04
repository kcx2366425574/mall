package com.kcx.mall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.product.pojo.ProType;

/**
 * @date 2020-04-01
 * @author kcx
 * @description 
 */
public interface ProTypeMapper {

	//增加
	void add(ProType proType);
	
	//删除
	void deleteById(int ptId);
	
	//批量删除
	void deleteMany(int[] ids);
	
	//查询
	ProType queryById(int ptId);
	
	//修改
	void update(ProType proType);
	
	//得到所有记录数
	int getAllAcount();
	
	//查询所有
	List<ProType> queryAll(@Param("start") int start, @Param("pageSize") int pageSize);
}
