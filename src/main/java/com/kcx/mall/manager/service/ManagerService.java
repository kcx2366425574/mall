package com.kcx.mall.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.manager.dao.ManagerMapper;
import com.kcx.mall.manager.pojo.Manager;

/**
 * @date 2020-03-15
 * @author kcx
 * @description 
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class ManagerService {
	
	@Autowired
	private ManagerMapper mapper;
	
	/**
	 * 登录验证
	 * 返回1 用户名不存在 2 密码错误 3 登录成功 4账号已在别处登录
	 */
	public int checkLogin(String manaName,String manaPassword) {
		
		Manager mana = mapper.queryByLoginName(manaName);
		
		if (mana == null)
			return 1;
		else if ( !manaPassword.equals(mana.getManaPassword()))
			return 2;
		else{
			/**
			 * 如果不在线，需要更新登录状态，并返回登录成功
			 */
			if(!mana.getManaIson()) {
				mana.setManaIson(true);
				mapper.update(mana);
				return 3;
			}else {
				return 4;
			}
		}
	}
	
	//批量删除
	public void deleteMany(int[] ids){
		
		mapper.deleteMany(ids);
	}
	
	//根据id删除管理员
	public void deleteById(int manaId) {
		mapper.deleteByManaId(manaId);
	}
	
	//添加管理员
	public void insertManager(Manager manager) {
		mapper.addMana(manager);
	}
	
	//修改管理员信息
	public void updateManager(Manager manager) {
		mapper.update(manager);
	}
	
	//根据id查询管理员
	@Transactional(readOnly=true)
	public Manager queryById(int manaId) {
		return mapper.queryById(manaId);
	}
	
	//获取管理员头像
	@Transactional(readOnly=true)
	public String getHead(int manaId) {
		return mapper.getHeadById(manaId);
	}
	
	//查询所有管理员
	@Transactional(readOnly=true)
	public List<Manager> queryAll(Pager pager){
		return mapper.queryAll(pager.getStart(),pager.getPageSize());
	}
	
	//根据登录名查询管理员
	@Transactional(readOnly=true)
	public Manager queryByLoginName(String manaName) {
		return mapper.queryByLoginName(manaName);
	}
	
	//得到manager总数
	@Transactional(readOnly=true)
	public int getAllAcount() {
		return mapper.getAllAcount();
	}
	
	//更新头像
	public void updateHead(int manaId,String manaPhoto) {
		mapper.updateHead(manaId, manaPhoto);
	}
	
	//更新密码
	public void updatePwd(int manaId,String manaPassword) {
		mapper.updatePwd(manaId, manaPassword);
	}
}
