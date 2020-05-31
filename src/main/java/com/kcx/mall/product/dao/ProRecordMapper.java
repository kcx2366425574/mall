package com.kcx.mall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.product.pojo.ProRecord;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
public interface ProRecordMapper {

	void addProRec(ProRecord proRecord);
	
	int getCount(@Param("cusId")int cusId);
	
	List<ProRecord> query(@Param("cusId")int cusId,@Param("start") int start, @Param("pageSize") int pageSize);
}
