package com.caotatcuong.dao.impl;

import javax.inject.Inject;

import com.caotatcuong.dao.ILoginDao;
import com.caotatcuong.dao.IUserDao;
import com.caotatcuong.model.User;

public class LoginDao implements ILoginDao {

	@Inject
	IUserDao userDao;

	@Override
	public Boolean checkLogin(String username, String password) {
		User user = userDao.findUsernamePassworrd(username, password);
		return user != null ? true : false;
	}
}
