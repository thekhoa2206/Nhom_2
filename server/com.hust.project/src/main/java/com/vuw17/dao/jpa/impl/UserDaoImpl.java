package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.Role;
import com.vuw17.entities.TypeRoom;
import com.vuw17.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserDaoImpl implements  UserDao{
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class.toString());

    @Override
    public List<User> findAllUser(){
        String sql = "SELECT * FROM user ";
        return entityManager.createNativeQuery(sql,User.class).getResultList();
    }

    @Override
    public Role findRoleById(int id){
        String sql = "SELECT * FROM role where id = ?";
        return (Role) entityManager.createNativeQuery(sql,Role.class).setParameter(1,id).getSingleResult();
    }
}
