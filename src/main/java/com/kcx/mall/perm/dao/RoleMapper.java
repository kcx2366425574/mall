package com.kcx.mall.perm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kcx.mall.perm.pojo.Role;

public interface RoleMapper {
   
	void insert(Role role);
	
	void delete(Integer roleId);
	
	void update(Role role);
	
	Role queryById(Integer roleId);
	
	int getCount();
	
	List<Role> query(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
	
	List<Role> searchNot(@Param("ids") Integer[] ids);
	
	/**
	 * 根据登录名查询返回对应的角色名称
	 */
	List<Map<String, Object>> queryRole(String empLoginName);
	
}