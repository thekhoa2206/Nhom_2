package com.vuw17.services;

import com.vuw17.dto.typeroom.TypeRoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeRoomService {
    //Them moi 1 TypeRoom
    String insertOne(TypeRoomDTO typeRoom);
    //Lay tat ca cac TypeRooms
    List<TypeRoomDTO> findAll();
    //Sua thong tin cua TypeRoom
    String updateOne(TypeRoomDTO typeRoom);
    //Xoa TypeRoom
    String deleteOne(int id);
    //Lay TypeRoom theo id
    TypeRoomDTO findById(int id);
    //Lay TypeRoom theo id
    TypeRoomDTO findByName(String name);
}
