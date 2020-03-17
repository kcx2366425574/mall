package com.kcx.mall.perm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.perm.pojo.RolePerm;

public interface RolePermMapper {
    
	void insert(RolePerm rolePerm);
	
	void delete(Integer[] ids);
	
	void update(RolePerm rolePerm);
	
	int getCount(@Param("roleId") Integer roleId, @Param("permId") Integer permId);
	
	List<RolePerm> query(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("roleId") Integer roleId, @Param("permId") Integer permId);
	
	ArrayList<Integer> getPermIds(Integer roleId);
	
	ArrayList<Integer> getAllPermIds();
	
	List<RolePerm> searchPerms(Integer roleId);
	
}