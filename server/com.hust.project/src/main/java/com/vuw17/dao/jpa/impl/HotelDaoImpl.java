package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.GenericDAO;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dto.hotel.HotelDTORequest;
import com.vuw17.entities.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class HotelDaoImpl implements HotelDao, GenericDAO<Hotel> {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelDaoImpl.class.toString());

    @Override
    public void insertOne(Hotel hotel) {
        String sql = "INSERT INTO hotel(name,address,note,phone_number,status) VALUES (?,?,?,?,?)";
        entityManager.createNativeQuery(sql).setParameter(1,hotel.getName()).setParameter(2,hotel.getAddress())
                .setParameter(3,hotel.getNote()).setParameter(4,hotel.getPhoneNumber())
                .setParameter(5, ConstantVariableCommon.STATUS_HOTEL_1).executeUpdate();
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        hotels = entityManager.createNativeQuery(sql,Hotel.class).getResultList();
        return hotels;
    }

    @Override
    public void updateOne(Hotel hotel) {
        String sql = "UPDATE hotel SET name = ?,address = ?,note = ?,phone_number = ?,status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,hotel.getName()).setParameter(2,hotel.getAddress())
                .setParameter(3,hotel.getNote()).setParameter(4,hotel.getPhoneNumber())
                .setParameter(5,hotel.getStatus()).setParameter(6,hotel.getId()).executeUpdate();

    }

    @Override
    public void deleteOne(int id) {
        String sql = "UPDATE hotel SET status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,ConstantVariableCommon.STATUS_HOTEL_3).setParameter(2,id).executeUpdate();
    }

    @Override
    public Hotel findById(int id) {
        String sql = "SELECT * FROM hotel WHERE id = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,id).getResultList());
    }

    @Override
    public Hotel findByAddress(String address) {
        String sql = "SELECT * FROM hotel WHERE address = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,address).getResultList());
    }

    @Override
    public Hotel findByName(String name) {
        String sql = "SELECT * FROM hotel WHERE name = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,name).getResultList());
    }

    @Override
    public Hotel findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM hotel WHERE phone_number = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Hotel.class).setParameter(1,phoneNumber).getResultList());
    }

    @Override
    public Hotel getFirstRowData(List<Hotel> list) {
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
