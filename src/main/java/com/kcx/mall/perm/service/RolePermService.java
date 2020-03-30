package com.kcx.mall.perm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.commom.Pager;
import com.kcx.mall.perm.dao.PermissionMapper;
import com.kcx.mall.perm.dao.RolePermMapper;
import com.kcx.mall.perm.pojo.Permission;
import com.kcx.mall.perm.pojo.Role;
import com.kcx.mall.perm.pojo.RolePerm;

@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermService {
	
	@Autowired
	private RolePermMapper mapper;
	
	@Autowired
	private PermissionMapper permMapper;
	
	//增加角色权限
	public void addRolePerm(Integer roleId, Integer[] permIds) {
		Role role = new Role();
		role.setRoleId(roleId);
		Permission perm = new Permission();
		for (Integer permId : permIds) {
			perm.setPermId(permId);
			RolePerm rolePerm = new RolePerm(role, perm);
			mapper.insert(rolePerm);
		}
	}
	
	//删除角色权限
	public void deleteRolePerm(Integer[] ids) {
		mapper.delete(ids);
	}
	
	//条件获取记录数
	@Transactional(readOnly = true)
	public int getRolePermCount(Integer roleId, Integer permId) {
		return mapper.getCount(roleId, permId);
	}
	
	//条件查询
	@Transactional(readOnly = true)
	public List<RolePerm> queryRolePerm(Pager pager, Integer roleId, Integer permId) {
		return mapper.query(pager.getStart(), pager.getPageSize(), roleId, permId);
	}
	
	//获取每个用户已拥有的权限返回id数组
	@Transactional(readOnly = true)
	public ArrayList<Integer> getRpPermIds(Integer roleId) {
		return mapper.getPermIds(roleId);
	}
	
	//获取所有用户权限 返回id数组
	@Transactional(readOnly = true)
	public ArrayList<Integer> getRpAllPermIds() {
		return mapper.getAllPermIds();
	}
	
	//查询已拥有权限集合
	@Transactional(readOnly = true)
	public List<RolePerm> searchRpPerms(Integer roleId) {
		return mapper.searchPerms(roleId);
	}
	
	//获取用户未拥有的权限
	@Transactional(readOnly = true)
	public List<Permission> searchRpNot(Integer roleId) {
		ArrayList<Integer> permIds = mapper.getPermIds(roleId);
		ArrayList<Integer> allPermIds = mapper.getAllPermIds();
		allPermIds.removeAll(permIds);
		
		int size = allPermIds.size();
		Integer[] ids = (Integer[])allPermIds.toArray(new Integer[size]);
		
		return permMapper.searchNot(ids);
	}
	
}
