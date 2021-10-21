package com.vuw17.dao.jdbc;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("UserDaoJDBC")
@Transactional(rollbackOn = Exception.class)
public interface UserDaoJDBC {

}
