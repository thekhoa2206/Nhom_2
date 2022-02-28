package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.TypePriceDao;
import com.vuw17.entities.RoomPrice;
import com.vuw17.entities.TypePrice;
import com.vuw17.entities.TypeRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TypePriceDaoImpl implements TypePriceDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(TypePriceDaoImpl.class.toString());
    @Override
    public TypePrice findByName(String name) {
        String sql = "SELECT * FROM type_price WHERE name = ?";
        try{
            return (TypePrice) entityManager.createNativeQuery(sql,TypePrice.class).setParameter(1,name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public TypePrice findById(int id) {
        String sql = "SELECT * FROM type_price WHERE id = ?";
        try{
            return (TypePrice) entityManager.createNativeQuery(sql,TypePrice.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public RoomPrice findByIdRoom(int id){
        String sql = "SELECT * FROM room_price WHERE type_room_id = " +id;
        try{
            return (RoomPrice) entityManager.createNativeQuery(sql,RoomPrice.class).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
