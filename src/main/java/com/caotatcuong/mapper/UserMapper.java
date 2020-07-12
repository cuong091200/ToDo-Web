package com.caotatcuong.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.caotatcuong.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet) {
		User user = new User();
		try {
			user.setFirstName(resultSet.getNString("first_name"));
			user.setLastName(resultSet.getNString("last_name"));
			user.setPassword(resultSet.getString("password"));
			user.setUsername(resultSet.getString("username"));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
