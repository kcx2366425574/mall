package com.kcx.mall.pic.dao;

import java.util.List;

import com.kcx.mall.pic.pojo.Pic;

/**
 * @date 2020-04-12
 * @author kcx
 * @description 
 */
public interface PicMapper {
	
	void addPic(Pic pic);
	
	//通过产品id查询相关的图片
	List<Pic> queryByProId(int picProId);
	
	//删除
	void deleteById(int picId);
	
	//批量删除
	void deleteMany(int[] ids);

}
