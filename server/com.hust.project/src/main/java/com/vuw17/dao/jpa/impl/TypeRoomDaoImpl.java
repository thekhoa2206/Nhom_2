package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dao.jpa.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TypeRoomDaoImpl implements TypeRoomDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());
}
