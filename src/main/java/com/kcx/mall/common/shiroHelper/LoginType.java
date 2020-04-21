package com.kcx.mall.common.shiroHelper;
/**
 * @date 2020-04-20
 * @author kcx
 * @description 解决shiro处理三种角色用户
 */
public enum LoginType {

	SHOP("Shop"),MANAGER("Manager"),CUSTOMER("Customer");
	
	private String loginType;
	
	private LoginType(String loginType) {
		this.loginType = loginType;
	}
	
	@Override
	public String toString() {
		return this.loginType.toString();
	}
	
}
