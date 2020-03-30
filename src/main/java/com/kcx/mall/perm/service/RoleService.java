package com.kcx.mall.perm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.perm.dao.RoleMapper;
import com.kcx.mall.perm.pojo.Role;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService {
	
	@Autowired
	private RoleMapper mapper;
	
	//增加角色
	public void addRole(Role role) {
		mapper.insert(role);
	}
	
	//删除角色
	public void deleteRole(Integer roleId) {
		mapper.delete(roleId);
	}
	
	//修改角色
	public void updateRole(Role role) {
		mapper.update(role);
	}
	
	//根据id查询角色
	@Transactional(readOnly = true)
	public Role queryRoleById(Integer roleId) {
		return mapper.queryById(roleId);
	}
	
	//获取记录数
	@Transactional(readOnly = true)
	public int getRoleCount() {
		return mapper.getCount();
	}
	
	//分页查询
	@Transactional(readOnly = true)
	public List<Role> queryAllRole(Integer start, Integer pageSize) {
		return mapper.query(start, pageSize);
	}
	
	//查询用户未拥有的角色
	@Transactional(readOnly = true)
	public List<Role> searchRoleNot(Integer[] ids) {
		return mapper.searchNot(ids);
	}
	
	/**
	 * 查询指定用户所有的角色
	 * 
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryRole(String empLoginName) {
		return mapper.queryRole(empLoginName);
	}

}
