package com.kcx.mall.pic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.pic.dao.PicMapper;
import com.kcx.mall.pic.pojo.Pic;

/**
 * @date 2020-04-15
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PicService {

	@Autowired
	private PicMapper mapper;
	
	//增加图片
	public void addPic(Pic pic) {
		mapper.addPic(pic);
	}
	
	//根据商品id查询图片
	@Transactional(readOnly=true)
	public List<Pic> queryByProId(int picProId){
		return mapper.queryByProId(picProId);
	}
	
	//删除图片
	public void deleteById(int picId) {
		mapper.deleteById(picId);
	}
	
	//批量删除
	public void deleteMany(int[] ids) {
		mapper.deleteMany(ids);
	}
}
