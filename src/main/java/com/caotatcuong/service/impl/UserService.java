package com.caotatcuong.service.impl;

import javax.inject.Inject;

import com.caotatcuong.dao.IUserDao;
import com.caotatcuong.model.User;
import com.caotatcuong.service.IUserService;

public class UserService implements IUserService{
	@Inject IUserDao userDao;
	@Override
	public User insert(User user) {
		Long id = userDao.insert(user);
		return userDao.findOne(id);
	}

}
