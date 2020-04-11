package com.kcx.mall.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.manager.pojo.Manager;

/**
 * @date 2020-03-12
 * @author kcx
 * @description 
 */
public interface ManagerMapper {

	//增加管理员
	void addMana(Manager mana);
	
	//通过id删除管理员
	void deleteByManaId(int manaId);
	
	//批量删除
	void deleteMany(int[] ids);
	
	//修改管理员信息
	void update(Manager mana);
	
	//通过id查询管理员信息
	Manager queryById(int manaId);
	
	//通过登录名查询管理员信息
	Manager queryByLoginName(String manaName);
	
	//查询所有的管理员
	List<Manager> queryAll(@Param("start") int start, @Param("pageSize") int pageSize);
	
	//得到manager总数
	int getAllAcount();
	
	//上传头像
	void updateHead(@Param("manaName")String manaName,@Param("manaPhoto")String manaPhoto);
}
