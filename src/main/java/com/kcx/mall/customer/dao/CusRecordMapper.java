package com.kcx.mall.customer.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.customer.pojo.CusRecord;

/**
 * @date 2020-03-25
 * @author kcx
 * @description 
 */
public interface CusRecordMapper {

	//增加
	void addCusRecord(CusRecord cusRecord);
	
	//通过主键id查询
	CusRecord queryById(int crId);
	
	//动态查询
	List<CusRecord> queryByCondition(@Param("start") int start, @Param("pageSize") int pageSize,
			@Param("crCusId") int crCusId,@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	//查询所有的记录
	List<CusRecord> queryAll(@Param("start") int start, @Param("pageSize") int pageSize);
	
	
}
