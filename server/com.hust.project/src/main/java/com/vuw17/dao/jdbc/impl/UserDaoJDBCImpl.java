package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.UserDaoJDBC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;

@Repository("UserDaoJDBCImpl")
@Transactional(rollbackOn = Exception.class)
public class UserDaoJDBCImpl extends JdbcDaoSupport implements UserDaoJDBC {
    private final JdbcTemplate jdbcTemplateObject;

    public UserDaoJDBCImpl(JdbcTemplate jdbcTemplateObject, DataSource dataSource) {
        this.jdbcTemplateObject = jdbcTemplateObject;
        this.setDataSource(dataSource);
    }
}
