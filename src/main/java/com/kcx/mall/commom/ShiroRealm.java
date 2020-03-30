package com.kcx.mall.commom;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.kcx.mall.manager.service.ManagerService;
import com.kcx.mall.perm.service.PermissionService;
import com.kcx.mall.perm.service.RoleService;

/**
 * shiro的核心Realm（验证登录 和 查询读取当前登录的角色和权限）
 * 
 * @author kcx
 *
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private ManagerService manaService;

	@Autowired
	private PermissionService permService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 获得用户的授权信息（查询角色和权限）
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		// 获得当前用户名
		String manaName = (String) this.getAvailablePrincipal(principalCollection);

		System.out.println("进行授权..." + manaName);
		
		//通过用户名去获得用户的所有的授权信息，把授权信息存储到AuthorizationInfo对象中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//设置角色
		HashSet<String> roleSet = new HashSet<>();
		//查询当前用户所有的角色
		List<Map<String, Object>> roleList = roleService.queryRole(manaName);
		
		for (Map map : roleList) {
			roleSet.add( (String) map.get("role_name") );
			System.out.println(map.get("role_name"));
		}
		
		info.setRoles(roleSet);
		
		//设置权限
		HashSet<String> permSet = new HashSet<>();		
		//查询当前用户的所有权限
		List<Map<String, Object>> permList = permService.queryPerm(manaName);
		
		for (Map map : permList) {
			permSet.add( (String) map.get("perm_name") );
			System.out.println(map.get("perm_name"));
		}
		
		info.setStringPermissions(permSet);
		
		return info;
	}

	/**
	 * 获得用户认证信息（登陆）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {

		// 获得用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

		String manaName = upToken.getUsername();
		String manaPassword = String.valueOf(upToken.getPassword());

		System.out.println("进行登陆验证..." + manaName);
		
		// 验证登陆
		int result = manaService.checkLogin(manaName, manaPassword);

		if (result == 1) {
			// 用户名不存在
			throw new UnknownAccountException();
		} else if (result == 2) {
			// 密码错误
			throw new IncorrectCredentialsException();
		} else {
			// 登陆成功，返回info对象
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(manaName, manaPassword.toCharArray(), getName());
			;
			return info;
		}

	}

}