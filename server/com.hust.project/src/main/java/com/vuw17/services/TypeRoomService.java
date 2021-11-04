package com.vuw17.services;

import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.dto.user.UserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeRoomService {
    //Them moi 1 TypeRoom
    int insertOne(TypeRoomDTO typeRoom, UserDTOResponse userDTOResponse);
    //Lay tat ca cac TypeRooms
    List<TypeRoomDTO> findAll();
    //Sua thong tin cua TypeRoom
    boolean updateOne(TypeRoomDTO typeRoom, UserDTOResponse userDTOResponse);
    //Xoa TypeRoom
    boolean deleteOne(int id, UserDTOResponse userDTOResponse);
    //Lay TypeRoom theo id
    TypeRoomDTO findById(int id);
    //Lay TypeRoom theo id
    TypeRoomDTO findByName(String name);
}
