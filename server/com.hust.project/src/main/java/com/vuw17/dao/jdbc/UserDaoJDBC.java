package com.vuw17.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service("UserDaoJDBC")
public class UserDaoJDBC extends JdbcDaoSupport {
    private final JdbcTemplate jdbcTemplateObject;

    public UserDaoJDBC(JdbcTemplate jdbcTemplateObject, DataSource dataSource) {
        this.jdbcTemplateObject = jdbcTemplateObject;
        this.setDataSource(dataSource);
    }
}
