package com.kcx.mall.common.shiroHelper;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.customer.service.CustomerService;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.manager.service.ManagerService;

/**
 * @date 2020-04-21
 * @author kcx
 * @description 
 */
public class ManagerRealm extends AuthorizingRealm {

	@Autowired
    ManagerService service;
 
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
 
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        
    	// 1. 把AuthenticationToken转换为CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) token;
        
        // 2. 从CustomizedToken中获取登录名
        String loginName = customizedToken.getUsername();
        String manaPassword = String.valueOf(customizedToken.getPassword());

		System.out.println("进行登陆验证..." + loginName);
		
		// 验证登陆
		int result = service.checkLogin(loginName, manaPassword);

		if (result == 1) {
			// 用户名不存在
			throw new UnknownAccountException();
		} else if (result == 2) {
			// 密码错误
			throw new IncorrectCredentialsException();
		} else if(service.queryByLoginName(loginName).getManaIson()){
			throw new UnknownAccountException();			
		} else {
			// 登陆成功，返回info对象
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginName,manaPassword.toCharArray(), getName());
			;
			return info;
		}
    }

}
