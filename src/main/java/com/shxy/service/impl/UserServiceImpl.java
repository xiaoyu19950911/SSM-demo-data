package com.shxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shxy.dao.UserMapper;
import com.shxy.model.User;
import com.shxy.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	public User checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		User user = userMapper.findUserByName(username);
		if (user != null && user.getuPassword().equals(password)) {
			return user;
		}
		return null;
	}

	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findUserByName(username);
	}

	public String getPasswordByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.getPasswordByName(username);
	}

}
