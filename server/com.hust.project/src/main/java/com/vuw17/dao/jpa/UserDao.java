package com.vuw17.dao.jpa;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface UserDao {

}
