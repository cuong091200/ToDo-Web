package com.caotatcuong.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.caotatcuong.dao.ICommon;
import com.caotatcuong.mapper.RowMapper;


public class Common<T> implements ICommon<T> {
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/todoapp";
			String user = "root";
			String password = "123456";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				connection.close();
			} catch (SQLException e) {
				return null;
			}
		}
	}

	private void setParameters(PreparedStatement statement, Object... parameters) {
		for (int i = 0; i < parameters.length; i++) {
			try {
				if (parameters[i] instanceof String) {
					statement.setNString(i + 1, (String) parameters[i]);
				}
				if (parameters[i] instanceof Long) {
					statement.setLong(i + 1, (Long) parameters[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				connection.close();
			} catch (SQLException e) {
				return null;
			}
		}
		return id;
	}
}
