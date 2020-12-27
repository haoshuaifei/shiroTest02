package com.smy.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//自定义 realm
public class ShiroRealm extends AuthorizingRealm{

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("进入到授权============");
		//1.获取用户名
		Object username=principals.getPrimaryPrincipal(); // 用户名
		System.out.println("username: "+username);
		//2.根据username 从数据库中查找此用户的所有角色(权限)
		//3.把所有的角色(权限)封装到SimpleAuthorizationInfo 中
		//4.返回SimpleAuthorizationInfo 对象
		//5. shiro会自动去对比权限
		Set<String> roles=new HashSet<>();
		roles.add("user");//添加user权限/角色
		if(username.equals("tom")) {
			roles.add("admin");//添加user权限/角色
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("进入到认证============");
		UsernamePasswordToken tk=(UsernamePasswordToken) token;
		String username=tk.getUsername(); //获取用户名
		System.out.println("username:"+username);
		//根据用户名去数据库查询密码
		String pwd="fc1709d0a95a6be30bc5926fdb7f22f4";//从数据库查询出来的  fc1709d0a95a6be30bc5926fdb7f22f4
		String realmName="shiroRealm";
		//封装   之后shiro会自动对比
		AuthenticationInfo info=new SimpleAuthenticationInfo(username, pwd, realmName);
		return info;
	}
}
