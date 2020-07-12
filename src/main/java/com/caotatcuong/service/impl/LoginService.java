package com.caotatcuong.service.impl;

import javax.inject.Inject;

import com.caotatcuong.dao.ILoginDao;
import com.caotatcuong.service.ILoginService;

public class LoginService implements ILoginService {
	@Inject
	ILoginDao loginDao;

	@Override
	public Boolean checkLogin(String username, String password) {
		return loginDao.checkLogin(username, password);
	}

}
