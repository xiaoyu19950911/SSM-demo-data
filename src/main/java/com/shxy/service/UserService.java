package com.shxy.service;

import com.shxy.model.User;

public interface UserService {
	public User checkLogin(String username, String password);

	public User getUserByUserName(String username);

	public String getPasswordByName(String username);
}
