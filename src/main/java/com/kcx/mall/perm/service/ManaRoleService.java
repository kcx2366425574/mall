package com.kcx.mall.perm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.perm.dao.ManaRoleMapper;
import com.kcx.mall.perm.dao.RoleMapper;
import com.kcx.mall.perm.pojo.ManaRole;
import com.kcx.mall.perm.pojo.Role;

@Service
@Transactional(rollbackFor = Exception.class)
public class ManaRoleService {
	
	@Autowired
	private ManaRoleMapper mapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	//增加用户角色
	public void addManaRole(Integer manaId, Integer[] roleIds) {
		Manager mana = new Manager();
		mana.setManaId(manaId);
		Role role = new Role();
		for (Integer roleId : roleIds) {
			role.setRoleId(roleId);
			ManaRole manaRole = new ManaRole(mana, role);
			mapper.insert(manaRole);
		}
	}
	
	//删除用户角色
	public void deleteManaRole(Integer[] ids) {
		mapper.delete(ids);
	}
	
	//根据用户id删除
	public void deleteManaRoleByMana(Integer manaId) {
		mapper.deleteByMana(manaId);
	}
	
	//条件获取记录数
	public int getManaRoleCount(Integer manaId, Integer roleId) {
		return mapper.getCount(manaId, roleId);
	}
	
	//条件查询用户角色
	public List<ManaRole> queryManaRole(Pager pager, Integer ManaId, Integer roleId) {
		return mapper.query(pager.getStart(), pager.getPageSize(), ManaId, roleId);
	}
	
	//获取每个用户已拥有角色的返回id数组
	@Transactional(readOnly = true)
	public ArrayList<Integer> getMrRoleIds(Integer ManaId) {
		return mapper.getRoleIds(ManaId);
	}
	
	//获取所有用户角色 返回id数组
	@Transactional(readOnly = true)
	public ArrayList<Integer> getMrAllRoleIds() {
		return mapper.getAllRoleIds();
	}
	
	//查询已拥有角色集合
	@Transactional(readOnly = true)
	public List<ManaRole> searchMrRoles(Integer ManaId) {
		return mapper.searchRoles(ManaId);
	}
	
	//获取拥有角色的用户数量
	@Transactional(readOnly = true)
	public int getMrManaCount() {
		return mapper.getManaNum();
	}
	
	//查询拥有角色的用户 去重
	@Transactional(readOnly = true)
	public List<Manager> queryMrDistinct(Integer start, Integer pageSize) {
		return mapper.queryDistinct(start, pageSize);
	}
	
	//获取用户未拥有的角色
	@Transactional(readOnly = true)
	public List<Role> searchRpNot(Integer manaId) {
		ArrayList<Integer> roleIds = mapper.getRoleIds(manaId);
		ArrayList<Integer> allRoleIds = mapper.getAllRoleIds();
		allRoleIds.removeAll(roleIds);
		
		int size = allRoleIds.size();
		Integer[] ids = (Integer[])allRoleIds.toArray(new Integer[size]);
		
		return roleMapper.searchNot(ids);
	}

}
