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
    void insertOne(Room room);
    //Lay tat ca cac rooms
    List<Room> findAll();
    //Sua thong tin cua room
    void updateOne(Room room);
    //Xoa room
    void deleteOne(int id);
    //Lay room theo id
    Room findById(int id);
    //Lay room theo name
    Room findByName(String name);
    //Lay room theo hotel_id
    Room findByHotelId(int hotelId);
    //Lay room theo type_room id
    Room findByTypeRoomId(int typeRoomId);
}
