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
import java.util.ArrayList;
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
            return new ArrayList<>();
        }
    }

    @Override
    public List<OccupiedRoom> findByRoomId(int roomId) {
        String sql = "SELECT * FROM occupied_room WHERE room_id = ? AND status = 1";
        try{
            return  entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,roomId).getResultList();
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public OccupiedRoom findAvailableRoomById(int id) {
        String sql = "SELECT * FROM occupied_room WHERE id = ? AND status = 2";
        try{
            return (OccupiedRoom) entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,id).getSingleResult();
        }catch (Exception e) {
            return new OccupiedRoom();
        }
    }


    @Override
    public boolean updateStatusAndCheckOutTime(int id,int status, long checkOutTime) {
        String sql = "UPDATE occupied_room SET status = ?,check_out_time = ? WHERE id = ?";
        try{
            entityManager.createNativeQuery(sql).setParameter(1,status).setParameter(2,checkOutTime).setParameter(3,id).executeUpdate();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<OccupiedRoom> findOccupiedRoomsByBillId(int billId) {
        String sql = "SELECT * FROM occupied_room WHERE bill_id = ? ";
        try{
            return entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,billId).getResultList();
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public OccupiedRoom findById(int id) {
        String sql = "SELECT * FROM occupied_room WHERE id = ?";
        try{
            return (OccupiedRoom) entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,id).getSingleResult();
        }catch (Exception e) {
            return new OccupiedRoom();
        }
    }

    @Override
    public OccupiedRoom findByBillId(int billId) {
        String sql = "SELECT * FROM occupied_room WHERE bill_id = ?";
        try{
            return (OccupiedRoom) entityManager.createNativeQuery(sql, OccupiedRoom.class).setParameter(1,billId).getSingleResult();
        }catch (Exception e) {
            return new OccupiedRoom();
        }
    }
}
