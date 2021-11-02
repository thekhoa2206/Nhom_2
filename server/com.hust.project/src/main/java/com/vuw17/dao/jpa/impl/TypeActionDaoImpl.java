package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.entities.TypeAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TypeActionDaoImpl implements TypeActionDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeActionDaoImpl.class.toString());
    @Override
    public TypeAction findByName(String name) {
        String sql = "SELECT * FROM type_action WHERE name = ?";
        try {
            return (TypeAction) entityManager.createNativeQuery(sql,TypeAction.class).setParameter(1, name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
