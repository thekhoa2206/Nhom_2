package com.vuw17.dao.jpa;

import com.vuw17.entities.Hotel;
import com.vuw17.entities.Room;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface RoomDao {
    //Them moi 1 room
//    void insertOne(Room room);
    //Lay tat ca cac rooms
    List<Room> findAll();
    //Sua thong tin cua room
    boolean updateOne(Room room);
    //Xoa room
    boolean deleteOne(int id);
    //Lay room theo id
    Room findById(int id);
    //Lay room theo name
    Room findByName(String name);
    //Lay list room theo hotel_id
    List<Room> findByHotelId(int hotelId);
    //Lay list room theo type_room id
    List<Room> findByTypeRoomId(int typeRoomId);
}
