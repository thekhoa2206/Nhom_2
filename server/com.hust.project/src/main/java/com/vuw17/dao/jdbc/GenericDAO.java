package com.vuw17.dao.jdbc;
public interface GenericDAO<T> {
    int insertOne(String sql, Object... parameters);
}
