package com.vuw17.services;

import com.vuw17.dto.typeroom.TypeRoomDTORequest;
import com.vuw17.entities.Hotel;
import com.vuw17.entities.TypeRoom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeRoomService {
    //Them moi 1 TypeRoom
    String insertOne(TypeRoomDTORequest typeRoom);
    //Lay tat ca cac TypeRooms
    List<TypeRoomDTORequest> findAll();
    //Sua thong tin cua TypeRoom
    String updateOne(TypeRoomDTORequest typeRoom);
    //Xoa TypeRoom
    String deleteOne(int id);
    //Lay TypeRoom theo id
    TypeRoomDTORequest findById(int id);
    //Lay TypeRoom theo id
    TypeRoomDTORequest findByName(String name);
}
