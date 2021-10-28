package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dto.room.RoomDTORequest;
import com.vuw17.entities.Room;
import com.vuw17.services.GenericService;
import com.vuw17.services.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService, GenericService<RoomDTORequest> {
    private final RoomDao roomDao;
    private final TypeRoomDao typeRoomDao;
    private final HotelDao hotelDao;
    private static final String NOT_EXIST_HOTEL_ID = "Hotel ID does not exist";
    private static final String NOT_EXIST_TYPE_ROOM_ID = "Type Room ID does not exist";

    public RoomServiceImpl(RoomDao roomDao, TypeRoomDao typeRoomDao, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.typeRoomDao = typeRoomDao;
        this.hotelDao = hotelDao;
    }

    @Override
    public String insertOne(RoomDTORequest roomDTORequest) {
        String message = checkInput(roomDTORequest);
        if (message == null) {
            roomDao.insertOne(toEntity(roomDTORequest));
            return ConstantVariableCommon.CREATE_SUCCESSFUL;
        }
        return message;
    }

    @Override
    public List<RoomDTORequest> findAll() {
        List<RoomDTORequest> roomDTORequests = new ArrayList<>();
        List<Room> rooms = roomDao.findAll();
        for(Room room : rooms){
            roomDTORequests.add(toDTO(room));
        }
        return roomDTORequests;
    }

    @Override
    public String updateOne(RoomDTORequest roomDTORequest) {
        int id = roomDTORequest.getId();
        if (id <= 0) {
            return ConstantVariableCommon.INVALID_ID;
        } else if (findById(id) == null) {
            return ConstantVariableCommon.NOT_EXIST_ID;
        } else {
            String message = checkInput(roomDTORequest);
            if (message == null) {
                roomDao.updateOne(toEntity(updateData(findById(id), roomDTORequest)));
                return ConstantVariableCommon.UPDATE_SUCCESSFUL;
            }
            return message;
        }
    }

    @Override
    public String deleteOne(int id) {
        if (findById(id) != null) {
            RoomDTORequest room = findById(id);
            if (room.getStatus() == ConstantVariableCommon.STATUS_ROOM_4) {
                return ConstantVariableCommon.DELETED_ID;
            } else {
                roomDao.deleteOne(id);
                return ConstantVariableCommon.DELETE_SUCCESSFUL;
            }
        }
        return ConstantVariableCommon.NOT_EXIST_ID;
    }

    @Override
    public RoomDTORequest findById(int id) {
        Room room = roomDao.findById(id);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }

    @Override
    public RoomDTORequest findByName(String name) {
        Room room = roomDao.findByName(name);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }

    @Override
    public RoomDTORequest findByHotelId(int hotelId) {
        Room room = roomDao.findByHotelId(hotelId);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }

    @Override
    public RoomDTORequest findByTypeRoomId(int typeRoomId) {
        Room room = roomDao.findByTypeRoomId(typeRoomId);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }
    public RoomDTORequest updateData(RoomDTORequest oldData, RoomDTORequest newData) {

        if (StringUtils.hasText(newData.getName())) {
            oldData.setName(newData.getName());
        }
        if (StringUtils.hasText(newData.getNote())) {
            oldData.setNote(newData.getNote());
        }
        if(findByTypeRoomId(newData.getTypeRoomId()) != null && newData.getTypeRoomId() != oldData.getTypeRoomId()){
            oldData.setTypeRoomId(newData.getTypeRoomId());
        }
        if(findByHotelId(newData.getHotelId()) != null && newData.getHotelId() != oldData.getHotelId()){
            oldData.setHotelId(newData.getHotelId());
        }
        return oldData;
    }
    public Room toEntity(RoomDTORequest roomDTORequest) {
        Room room = new Room();
        room.setId(roomDTORequest.getId());
        room.setName(roomDTORequest.getName());
        room.setNote(roomDTORequest.getNote());
        room.setStatus(roomDTORequest.getStatus());
        room.setHotelId(roomDTORequest.getHotelId());
        room.setTypeRoomId(roomDTORequest.getTypeRoomId());
        return room;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public RoomDTORequest toDTO(Room room) {
        RoomDTORequest roomDTORequest = commonTransferData(room);
        roomDTORequest.setId(room.getId());
        return roomDTORequest;
    }

    public RoomDTORequest toDTOWhenInsert(Room room) {
        return commonTransferData(room);
    }

    public RoomDTORequest commonTransferData(Room room) {
        RoomDTORequest roomDTORequest = new RoomDTORequest();
        roomDTORequest.setName(room.getName());
        roomDTORequest.setNote(room.getNote());
        roomDTORequest.setHotelId(room.getHotelId());
        roomDTORequest.setTypeRoomId(room.getTypeRoomId());
        roomDTORequest.setStatus(room.getStatus());
        return roomDTORequest;
    }

    @Override
    public String checkInput(RoomDTORequest roomDTORequest) {
        String message = null;
        if (findByName(roomDTORequest.getName()) != null) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        }else if(hotelDao.findById(roomDTORequest.getHotelId()) == null){
            message = NOT_EXIST_HOTEL_ID;
        }else if(typeRoomDao.findById(roomDTORequest.getTypeRoomId()) == null){
            message = NOT_EXIST_TYPE_ROOM_ID;
        }
        return message;
    }
}
