package com.smy.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

	@RequestMapping("/login")
	public String login(String username,String password) {
		System.out.println("进入到controller===========");
		System.out.println("username:  "+username);
		System.out.println("password:  "+password);
		Subject subject=SecurityUtils.getSubject();//获取当前subject
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);//封装
		//实现shiro的login操作
		try {
			//1.登录成功，可以跳转到 你想跳转的页面
			//2.登录失败，默认抛出UnknownAccountException异常
			subject.login(token);//触发认证操作
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登录失败");
			return "login";
		}
		return "redirect:/list.jsp";
	}
}
