package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.entities.Hotel;
import com.vuw17.entities.Room;
import com.vuw17.services.GenericService;
import com.vuw17.services.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService, GenericService<RoomDTO> {
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
    public String insertOne(RoomDTO roomDTO) {
        String message = checkInput(roomDTO);
        if (message == null) {
            roomDao.insertOne(toEntity(roomDTO));
            return ConstantVariableCommon.CREATE_SUCCESSFUL;
        }
        return message;
    }

    @Override
    public List<RoomDTO> findAll() {
        return convertList(roomDao.findAll());
    }

    @Override
    public String updateOne(RoomDTO roomDTO) {
        int id = roomDTO.getId();
        if (id <= 0) {
            return ConstantVariableCommon.INVALID_ID;
        } else if (findById(id) == null) {
            return ConstantVariableCommon.NOT_EXIST_ID;
        } else {
            String message = checkInput(roomDTO);
            if (message == null) {
                roomDao.updateOne(toEntity(updateData(findById(id), roomDTO)));
                return ConstantVariableCommon.UPDATE_SUCCESSFUL;
            }
            return message;
        }
    }

    @Override
    public String deleteOne(int id) {
        if (findById(id) != null) {
            RoomDTO room = findById(id);
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
    public RoomDTO findById(int id) {
        Room room = roomDao.findById(id);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }

    @Override
    public RoomDTO findByName(String name) {
        Room room = roomDao.findByName(name);
        if (room != null) {
            return toDTO(room);
        }
        return null;
    }

    @Override
    public List<RoomDTO> findByHotelId(int hotelId) {
        return convertList(roomDao.findByHotelId(hotelId));
    }

    @Override
    public List<RoomDTO> findByTypeRoomId(int typeRoomId) {
        return convertList(roomDao.findByTypeRoomId(typeRoomId));
    }
    public RoomDTO updateData(RoomDTO oldData, RoomDTO newData) {

        if (StringUtils.hasText(newData.getName())) {
            oldData.setName(newData.getName());
        }
        if (StringUtils.hasText(newData.getNote())) {
            oldData.setNote(newData.getNote());
        }
        if(typeRoomDao.findById(newData.getTypeRoomId()) != null && newData.getTypeRoomId() != oldData.getTypeRoomId()){
            oldData.setTypeRoomId(newData.getTypeRoomId());
        }
        if(hotelDao.findById(newData.getHotelId()) != null && newData.getHotelId() != oldData.getHotelId()){
            oldData.setHotelId(newData.getHotelId());
        }
        return oldData;
    }
    public Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setName(roomDTO.getName());
        room.setNote(roomDTO.getNote());
        room.setStatus(roomDTO.getStatus());
        room.setHotelId(roomDTO.getHotelId());
        room.setTypeRoomId(roomDTO.getTypeRoomId());
        return room;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = commonTransferData(room);
        roomDTO.setId(room.getId());
        return roomDTO;
    }

//    public RoomDTO toDTOWhenInsert(Room room) {
//        return commonTransferData(room);
//    }

    public RoomDTO commonTransferData(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName(room.getName());
        roomDTO.setNote(room.getNote());
        roomDTO.setHotelId(room.getHotelId());
        roomDTO.setTypeRoomId(room.getTypeRoomId());
        roomDTO.setStatus(room.getStatus());
        return roomDTO;
    }

    @Override
    public String checkInput(RoomDTO roomDTO) {
        String message = null;
        RoomDTO dto = findByName(roomDTO.getName());
        if(hotelDao.findById(roomDTO.getHotelId()) == null){
            message = NOT_EXIST_HOTEL_ID;
        }else if(typeRoomDao.findById(roomDTO.getTypeRoomId()) == null){
            message = NOT_EXIST_TYPE_ROOM_ID;
        }else if (dto != null && dto.getHotelId() == roomDTO.getHotelId()) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        }
        return message;
    }
    public List<RoomDTO> convertList(List<Room> rooms){
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOs.add(toDTO(room));
        }
        return roomDTOs;
    }
}
