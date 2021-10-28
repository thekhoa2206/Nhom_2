package com.vuw17.services;

import com.vuw17.dto.room.RoomDTORequest;
import com.vuw17.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    //Them moi 1 room
    String insertOne(RoomDTORequest roomDTORequest);
    //Lay tat ca cac rooms
    List<RoomDTORequest> findAll();
    //Sua thong tin cua room
    String updateOne(RoomDTORequest roomDTORequest);
    //Xoa room
    String deleteOne(int id);
    //Lay room theo id
    RoomDTORequest findById(int id);
    //Lay room theo name
    RoomDTORequest findByName(String name);
    //Lay room theo hotel_id
    RoomDTORequest findByHotelId(int hotelId);
    //Lay room theo type_room id
    RoomDTORequest findByTypeRoomId(int typeRoomId);
}
