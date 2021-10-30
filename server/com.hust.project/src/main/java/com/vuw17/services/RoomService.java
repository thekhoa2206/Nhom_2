package com.vuw17.services;

import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.dto.room.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    //Them moi 1 room
    int insertOne(RoomDTO roomDTO);
    //Lay tat ca cac rooms
    List<RoomDTO> findAll();
    //Sua thong tin cua room
    boolean updateOne(RoomDTO roomDTO);
    //Xoa room
    boolean deleteOne(int id);
    //Lay room theo id
    RoomDTO findById(int id);
    //Lay room theo name
    RoomDTO findByName(String name);
    //Lay room theo hotel_id
    List<RoomDTO> findByHotelId(int hotelId);
    //Lay room theo type_room id
    List<RoomDTO> findByTypeRoomId(int typeRoomId);
}
