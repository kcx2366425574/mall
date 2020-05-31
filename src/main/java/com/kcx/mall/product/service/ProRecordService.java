package com.kcx.mall.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.product.dao.ProRecordMapper;
import com.kcx.mall.product.pojo.ProRecord;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Service
public class ProRecordService {

	@Autowired ProRecordMapper mapper;
	
	//添加商品记录
	public void insertProRecord(ProRecord proRecord) {
		mapper.addProRec(proRecord);
	}
	
	//查询商品记录
	@Transactional(readOnly=true)
	public List<ProRecord> queryByCusId(Integer cusId,Pager pager){
		return mapper.query(cusId, pager.getStart(), pager.getPageSize());
	}
	
	//查询总数
	@Transactional(readOnly=true)
	public int getCountByCusId(Integer cusId){
		return mapper.getCount(cusId);
	}
}
