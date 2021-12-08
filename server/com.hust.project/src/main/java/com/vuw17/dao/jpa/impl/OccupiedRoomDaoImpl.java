package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.OccupiedRoomDao;
import com.vuw17.entities.Guest;
import com.vuw17.entities.OccupiedRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class OccupiedRoomDaoImpl implements OccupiedRoomDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(OccupiedRoomDaoImpl.class.toString());
    @Override
    public List<OccupiedRoom> findOccupiedRooms() {
        String sql = "SELECT * FROM occupied_room WHERE status = 1";
        try{
            return entityManager.createNativeQuery(sql, OccupiedRoom.class).getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public OccupiedRoom findByRoomId(int roomId) {
        String sql = "SELECT * FROM occupied_room WHERE room_id = ? AND status = 1";
        try{
            return (OccupiedRoom) entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,roomId).getSingleResult();
        }catch (Exception e) {
            return new OccupiedRoom();
        }
    }
}
