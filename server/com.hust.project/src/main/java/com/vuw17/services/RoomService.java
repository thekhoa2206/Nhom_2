package com.vuw17.services;

import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.dto.room.RoomUpdateDTO;
import com.vuw17.dto.user.UserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    //Them moi 1 room
    int insertOne(RoomDTO roomDTO, UserDTOResponse userDTOResponse);
    //Lay tat ca cac rooms
    List<RoomDTOResponse> findAll();
    //Sua thong tin cua room
    RoomDTOResponse updateOne(RoomUpdateDTO roomUpdateDTO, UserDTOResponse userDTOResponse);
    //Xoa room
    boolean deleteOne(int id, UserDTOResponse userDTOResponse);
    //Lay room theo id
    RoomDTOResponse findById(int id);
    //Lay room theo name
    RoomDTOResponse findByName(String name);
    //Lay room theo type_room id
    List<RoomDTOResponse> findByTypeRoomId(int typeRoomId);

    // thay đổi trnajg thái phòng
    void changeStatusRoom(int roomId, String typeAction);
}
