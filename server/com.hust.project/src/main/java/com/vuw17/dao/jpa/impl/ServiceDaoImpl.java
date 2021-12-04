package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.ServiceDao;
import com.vuw17.entities.Room;
import com.vuw17.entities.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ServiceDaoImpl implements ServiceDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDaoImpl.class.toString());

    @Override
    public Service findById(int id) {
        String sql = "SELECT * FROM service WHERE id = ?";
        try {
            return (Service) entityManager.createNativeQuery(sql,Service.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Service findByName(String name) {
        String sql = "SELECT * FROM service WHERE name = ?";
        try {
            return (Service) entityManager.createNativeQuery(sql,Service.class).setParameter(1,name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Service> findAll() {
        String sql = "SELECT * FROM service WHERE status = 1";
        try {
            return entityManager.createNativeQuery(sql,Service.class).getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
