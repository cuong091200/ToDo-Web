package com.caotatcuong.dao;

import com.caotatcuong.model.User;

public interface IUserDao extends ICommon<User>{
	User findOne(Long id);
	User findUsernamePassworrd(String username, String password);
	Long insert(User user);
}
