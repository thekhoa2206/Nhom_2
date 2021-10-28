package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.GenericDAO;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.Room;
import com.vuw17.entities.TypeRoom;
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
public class RoomDaoImpl implements RoomDao, GenericDAO<Room> {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());

    @Override
    public void insertOne(Room room) {
        String sql = "INSERT INTO room(name,hotel_id,type_room_id,note,status) VALUES (?,?,?,?,?)";
        entityManager.createNativeQuery(sql).setParameter(1,room.getName()).setParameter(2,room.getHotelId())
                .setParameter(3,room.getTypeRoomId()).setParameter(4,room.getNote())
                .setParameter(5, ConstantVariableCommon.STATUS_ROOM_1).executeUpdate();
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        rooms = entityManager.createNativeQuery(sql,Room.class).getResultList();
        return rooms;
    }

    @Override
    public void updateOne(Room room) {
        String sql = "UPDATE room SET name = ?,hotel_id = ?,type_room_id = ?,note = ?,status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,room.getName()).setParameter(2,room.getHotelId())
                .setParameter(3,room.getTypeRoomId()).setParameter(4,room.getNote())
                .setParameter(5, room.getNote()).setParameter(6,room.getId()).executeUpdate();
    }

    @Override
    public void deleteOne(int id) {
        String sql = "UPDATE room SET status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,ConstantVariableCommon.STATUS_ROOM_4).setParameter(2,id).executeUpdate();

    }

    @Override
    public Room findById(int id) {
        String sql = "SELECT * FROM room WHERE id = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Room.class).setParameter(1,id).getResultList());
    }

    @Override
    public Room findByName(String name) {
        String sql = "SELECT * FROM room WHERE name = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Room.class).setParameter(1,name).getResultList());
    }

    @Override
    public Room findByHotelId(int hotelId) {
        String sql = "SELECT * FROM room WHERE hotel_id = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Room.class).setParameter(1,hotelId).getResultList());
    }

    @Override
    public Room findByTypeRoomId(int typeRoomId) {
        String sql = "SELECT * FROM room WHERE type_room_id = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,Room.class).setParameter(1,typeRoomId).getResultList());
    }
    @Override
    public Room getFirstRowData(List<Room> list) {
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
