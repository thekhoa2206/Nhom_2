package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.entities.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class HotelDaoImpl implements HotelDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelDaoImpl.class.toString());

    @Override
    public List<Hotel> findAll() {
        String sql = "SELECT * FROM hotel";
        return entityManager.createNativeQuery(sql,Hotel.class).getResultList();
    }

    @Override
    public boolean updateOne(Hotel hotel) {
        String sql = "UPDATE hotel SET name = ?,address = ?,note = ?,phone_number = ?,status = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1,hotel.getName()).setParameter(2,hotel.getAddress())
                    .setParameter(3,hotel.getNote()).setParameter(4,hotel.getPhoneNumber())
                    .setParameter(5,hotel.getStatus()).setParameter(6,hotel.getId()).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteOne(int id) {
        String sql = "UPDATE hotel SET status = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1,ConstantVariableCommon.STATUS_HOTEL_3).setParameter(2,id).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Hotel findById(int id) {
        String sql = "SELECT * FROM hotel WHERE id = ?";
        try {
            return (Hotel) entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Hotel findByAddress(String address) {
        String sql = "SELECT * FROM hotel WHERE address = ?";
        try {
            return (Hotel) entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,address).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Hotel findByName(String name) {
        String sql = "SELECT * FROM hotel WHERE name = ?";
        try {
            return (Hotel) entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Hotel findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM hotel WHERE phone_number = ?";
        try {
            return (Hotel) entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,phoneNumber).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Hotel> findByStatus(int status) {
        String sql = "SELECT * FROM hotel WHERE status = ?";
        return entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,status).getResultList();
    }
}
