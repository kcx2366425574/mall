package com.kcx.mall.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.product.dao.ProTypeMapper;
import com.kcx.mall.product.pojo.ProType;

/**
 * @date 2020-04-01
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ProTypeService {

	@Autowired
	private ProTypeMapper mapper;
	
	//添加
	public void addProType(ProType proType) {
		mapper.add(proType);
	}
	
	//删除
	public void deleteById(int ptId) {
		mapper.deleteById(ptId);
	}
	
	//批量删除
	public void deleteMany(int[] ids){
			
		mapper.deleteMany(ids);
	}
	
	//查询
	@Transactional(readOnly=true)
	public ProType queryById(int ptId) {
		return mapper.queryById(ptId);
	}
	
	//修改
	public void updateProType(ProType proType) {
		mapper.update(proType);
	}
	
	//查询所有
	@Transactional(readOnly=true)
	public List<ProType> queryAll(Pager pager){
		return mapper.queryAll(pager.getStart(), pager.getPageSize());
	}
	
	//得到所有记录数
	@Transactional(readOnly=true)
	public int getAllAcount() {
		return mapper.getAllAcount();
	}
}
