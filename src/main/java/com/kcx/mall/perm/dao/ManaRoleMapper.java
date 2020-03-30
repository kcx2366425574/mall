package com.kcx.mall.perm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.perm.pojo.ManaRole;

public interface ManaRoleMapper {
    
	void insert(ManaRole ManaRole);
	
	void delete(Integer[] ids);
	
	void deleteByMana(Integer ManaId);
	
	int getCount(@Param("ManaId") Integer ManaId, @Param("roleId") Integer roleId);
	
	List<ManaRole> query(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("ManaId") Integer ManaId, @Param("roleId") Integer roleId);
	
	ArrayList<Integer> getRoleIds(Integer ManaId);
	
	ArrayList<Integer> getAllRoleIds();
	
	List<ManaRole> searchRoles(Integer ManaId);
	
	List<Manager> queryDistinct(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
	
	int getManaNum();
	
}