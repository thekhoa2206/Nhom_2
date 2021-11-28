package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoomDaoImpl implements RoomDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());

//    @Override
//    public void insertOne(Room room) {
//        String sql = "INSERT INTO room(name,hotel_id,type_room_id,note,status) VALUES (?,?,?,?,?)";
//        entityManager.createNativeQuery(sql).setParameter(1,room.getName()).setParameter(2,room.getHotelId())
//                .setParameter(3,room.getTypeRoomId()).setParameter(4,room.getNote())
//                .setParameter(5, ConstantVariableCommon.STATUS_ROOM_1).executeUpdate();
//    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM room";
        return entityManager.createNativeQuery(sql, Room.class).getResultList();
    }

    @Override
    public boolean updateOne(Room room) {
        String sql = "UPDATE room SET name = ?,type_room_id = ?,status = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1,room.getName())
                    .setParameter(2,room.getTypeRoomId()).setParameter(3,room.getStatus())
                   .setParameter(4,room.getId()).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOne(int id) {
        String sql = "UPDATE room SET status = ? WHERE id = ?";
        try{
            entityManager.createNativeQuery(sql).setParameter(1,ConstantVariableCommon.STATUS_ROOM_4).setParameter(2,id).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Room findById(int id) {
        String sql = "SELECT * FROM room WHERE id = ?";
        try {
            return (Room) entityManager.createNativeQuery(sql,Room.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Room findByName(String name) {
        String sql = "SELECT * FROM room WHERE name = ?";
        try {
            return (Room) entityManager.createNativeQuery(sql,Room.class).setParameter(1,name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<Room> findByTypeRoomId(int typeRoomId) {
        String sql = "SELECT * FROM room WHERE type_room_id = ?";
        return entityManager.createNativeQuery(sql,Room.class).setParameter(1,typeRoomId).getResultList();
    }
}
