package com.kcx.mall.customer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.customer.dao.CusRecordMapper;
import com.kcx.mall.customer.pojo.CusRecord;

/**
 * @date 2020-03-30
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CusRecordService {

	@Autowired
	private CusRecordMapper mapper;
	
	
	
	//添加
	public void addCusRecord(CusRecord cusRecord) {
		mapper.addCusRecord(cusRecord);
	}
	
	
	//根据id查询
	@Transactional(readOnly=true)
	public CusRecord queryById(int crId) {
		return mapper.queryById(crId);
	}
	
	//查询所有用户扩展
	@Transactional(readOnly=true)
	public List<CusRecord> queryAll(Pager pager){
		return mapper.queryAll(pager.getStart(),pager.getPageSize());
	}
	
	//动态查询
	@Transactional(readOnly=true)
	public List<CusRecord> queryByCondition(Pager pager,int crCusId, Date startDate, Date endDate){
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), crCusId, startDate, endDate);
	}
	
	
}
