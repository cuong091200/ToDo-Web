package com.caotatcuong.dao.impl;

import java.util.List;

import com.caotatcuong.dao.IUserDao;
import com.caotatcuong.mapper.UserMapper;
import com.caotatcuong.model.User;

public class UserDao extends Common<User> implements IUserDao {

	@Override
	public User findOne(Long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		List<User> users = query(sql, new UserMapper(), id);
		return users.isEmpty() ? null : users.get(0);
	}
	
	@Override
	public User findUsernamePassworrd(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? and password = ?";
		List<User> users = query(sql, new UserMapper(), username, password);
		return users.isEmpty() ? null : users.get(0);
	}
	
	@Override
	public Long insert(User user) {
		String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES(?, ?, ?, ?);";
		return insert(sql, user.getFirstName(), user.getLastName(), user.getUsername(),
				user.getPassword());
	}
	
}
