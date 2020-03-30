package com.kcx.mall.perm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.perm.dao.PermissionMapper;
import com.kcx.mall.perm.pojo.Permission;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionService {
	
	@Autowired
	private PermissionMapper mapper;
	
	//增加权限
	public void addPerm(Permission permission) {
		mapper.insert(permission);
	}
	
	//删除权限
	public void deletePerm(Integer permId) {
		mapper.delete(permId);
	}
	
	//修改权限
	public void updatePerm(Permission permission) {
		mapper.update(permission);
	}
	
	//根据id查询权限
	@Transactional(readOnly = true)
	public Permission queryPermById(Integer permId) {
		return mapper.queryById(permId);
	}
	
	//获取记录数
	@Transactional(readOnly = true)
	public int getPermCount(String permName, String permInfo) {
		return mapper.getCount(permName, permInfo);
	}
	
    //分页查询
	@Transactional(readOnly = true)
	public List<Permission> queryAllPerm(Integer start, Integer pageSize, String permName, String permInfo) {
		return mapper.query(start, pageSize, permName, permInfo);
	}
	
	//查询用户未拥有的权限
	@Transactional(readOnly = true)
	public List<Permission> searchPermNot(Integer[] ids) {
		return mapper.searchNot(ids);
	}

	/**
	 * 查询指定用户所有权限名称
	 * 
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryPerm(String empLoginName) {
		return mapper.queryPerm(empLoginName);
	}
	
}
