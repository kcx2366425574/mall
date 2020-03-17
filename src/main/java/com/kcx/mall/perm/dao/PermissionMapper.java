package com.kcx.mall.perm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.perm.pojo.Permission;

public interface PermissionMapper {
    
	void insert(Permission permission);
	
	void delete(Integer permId);
	
	void update(Permission permission);
	
	Permission queryById(Integer permId);
	
	List<Permission> query(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("permName") String permName, @Param("permInfo") String permInfo);
	
	int getCount(@Param("permName") String permName, @Param("permInfo") String permInfo);

	List<Permission> searchNot(@Param("ids") Integer[] ids);
	
	/**
	 * 通过登录名返回对应的所有权限名称
	 */
	List<Map<String, Object>> queryPerm(String empLoginName);
	
}