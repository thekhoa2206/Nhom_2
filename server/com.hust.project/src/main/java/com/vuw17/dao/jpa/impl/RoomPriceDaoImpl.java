package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.RoomPriceDao;
import com.vuw17.entities.RoomPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoomPriceDaoImpl implements RoomPriceDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomPriceDaoImpl.class.toString());

    @Override
    public boolean updateOne(RoomPrice roomPrice) {
        String sql = "UPDATE room_price SET type_price_id = ?,type_room_id = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1,roomPrice.getTypePriceId())
                    .setParameter(2, roomPrice.getTypeRoomId()).setParameter(3,roomPrice.getId()).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<RoomPrice> findByTypeRoomId(int typeRoomId) {
        String sql = "SELECT * FROM room_price WHERE type_room_id = ?";
        try {
            return entityManager.createNativeQuery(sql,RoomPrice.class).setParameter(1,typeRoomId).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public RoomPrice findByTypeRoomIdAndTypePriceId(int typeRoomId, int typePriceId) {
        String sql = "SELECT * FROM room_price WHERE type_room_id = ? AND type_price_id = ?";
        try {
            return (RoomPrice) entityManager.createNativeQuery(sql,RoomPrice.class).setParameter(1,typeRoomId).setParameter(2,typePriceId).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
