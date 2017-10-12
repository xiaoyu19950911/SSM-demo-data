package com.shxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shxy.model.User;
import com.shxy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(User loginUser, HttpServletRequest request) {
		// 通过用户名查找User对象
		User user = userService.getUserByUserName(loginUser.getuUsername());
		String password = "";
		if (user != null) {
			password = userService.getPasswordByName(user.getuUsername());
		}
		// 判断登录信息是否正确
		if (user != null && loginUser.getuPassword().equals(password)) {

			// 登陆成功，跳转到主页
			request.getSession().setAttribute("username", user.getuUsername());
			return "redirect:/person/listall";
		}
		// 登录失败，跳转页面
		request.setAttribute("Msg", "登录失败");
		return "company";
	}

}
