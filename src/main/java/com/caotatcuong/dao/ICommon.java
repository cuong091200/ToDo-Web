package com.caotatcuong.dao;

import java.util.List;

import com.caotatcuong.mapper.RowMapper;

public interface ICommon<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	Long insert(String sql, Object... parameters);
}
