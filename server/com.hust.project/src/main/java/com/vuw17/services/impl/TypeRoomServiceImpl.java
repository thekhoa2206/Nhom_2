package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.PriceDAO;
import com.vuw17.dao.jdbc.RoomPriceDAO;
import com.vuw17.dao.jdbc.TypeRoomDAO;
import com.vuw17.dao.jpa.PriceDao;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.entities.Price;
import com.vuw17.entities.RoomPrice;
import com.vuw17.entities.TypeRoom;
import com.vuw17.services.TypeRoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    private final TypeRoomDao typeRoomDao;
    private final PriceDAO priceDAO;
    private final PriceDao priceDao;
    private final TypeRoomDAO typeRoomDAO;
    private final RoomPriceDAO roomPriceDAO;

    public TypeRoomServiceImpl(TypeRoomDao typeRoomDao, PriceDAO priceDAO, PriceDao priceDao, TypeRoomDAO typeRoomDAO, RoomPriceDAO roomPriceDAO) {
        this.typeRoomDao = typeRoomDao;
        this.priceDAO = priceDAO;
        this.priceDao = priceDao;
        this.typeRoomDAO = typeRoomDAO;
        this.roomPriceDAO = roomPriceDAO;
    }

    @Override
    public int insertOne(TypeRoomDTO typeRoom) {
        String message = checkInput(typeRoom);
        if (message == null) {
//            typeRoomDao.insertOne(toEntity(typeRoom));
//            return ConstantVariableCommon.CREATE_SUCCESSFUL;
            Price price = priceDao.findByPrice(typeRoom.getPrice());
            int priceId = 0;
            if(price != null){
                priceId = price.getId();
            }else{
                priceId = priceDAO.insertOne(new Price(typeRoom.getPrice().toString(),typeRoom.getPrice()));
            }
            int typeRoomId = typeRoomDAO.insertOne(toEntity(typeRoom));
            roomPriceDAO.insertOne(new RoomPrice(priceId,typeRoomId));
            return typeRoomId;
        }
        return 0;
    }

    @Override
    public List<TypeRoomDTO> findAll() {
        List<TypeRoomDTO> typeRoomDTOs = new ArrayList<>();
        List<TypeRoom> typeRooms = typeRoomDao.findAll();
        for (TypeRoom typeRoom : typeRooms) {
            typeRoomDTOs.add(toDTO(typeRoom));
        }
        return typeRoomDTOs;
    }

    @Override
    public String updateOne(TypeRoomDTO typeRoom) {
        if (typeRoom.getId() <= 0) {
            return ConstantVariableCommon.INVALID_ID;
        } else if (findById(typeRoom.getId()) == null) {
            return ConstantVariableCommon.NOT_EXIST_ID;
        } else {
            String message = checkInput(typeRoom);
            if (message == null) {
                typeRoomDao.updateOne(toEntity(updateData(findById(typeRoom.getId()), typeRoom)));
                return ConstantVariableCommon.UPDATE_SUCCESSFUL;
            }
            return message;
        }

    }


    @Override
    public String deleteOne(int id) {
        if (findById(id) != null) {
            TypeRoomDTO typeRoom = findById(id);
            if (typeRoom.getStatus() == ConstantVariableCommon.STATUS_TYPE_ROOM_3) {
                return ConstantVariableCommon.DELETED_ID;
            } else {
                typeRoomDao.deleteOne(id);
                return ConstantVariableCommon.DELETE_SUCCESSFUL;
            }
        }
        return ConstantVariableCommon.NOT_EXIST_ID;
    }

    @Override
    public TypeRoomDTO findById(int id) {
        TypeRoom typeRoom = typeRoomDao.findById(id);
        if (typeRoom != null) {
            return toDTO(typeRoom);
        }
        return null;
    }
    

    @Override
    public TypeRoomDTO findByName(String name) {
        TypeRoom typeRoom = typeRoomDao.findByName(name);
        if (typeRoom != null) {
            return toDTO(typeRoom);
        }
        return null;
    }
    

    public TypeRoomDTO updateData(TypeRoomDTO oldData, TypeRoomDTO newData) {

        if (StringUtils.hasText(newData.getName())) {
            oldData.setName(newData.getName());
        }
        if (StringUtils.hasText(newData.getNote())) {
            oldData.setNote(newData.getNote());
        }
        if(newData.getNumberAdult() != oldData.getNumberAdult()){
            oldData.setNumberAdult(newData.getNumberAdult());
        }
        if(newData.getNumberChildren() != oldData.getNumberChildren()){
            oldData.setNumberChildren(newData.getNumberChildren());
        }
        return oldData;
    }

    public TypeRoom toEntity(TypeRoomDTO typeRoomDTO) {
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setId(typeRoomDTO.getId());
        typeRoom.setName(typeRoomDTO.getName());
        typeRoom.setNote(typeRoomDTO.getNote());
        typeRoom.setNumberAdult(typeRoomDTO.getNumberAdult());
        typeRoom.setNumberChildren(typeRoomDTO.getNumberChildren());
        typeRoom.setStatus(typeRoomDTO.getStatus());

        return typeRoom;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public TypeRoomDTO toDTO(TypeRoom typeRoom) {
        TypeRoomDTO typeRoomDTO = commonTransferData(typeRoom);
        typeRoomDTO.setId(typeRoom.getId());
        return typeRoomDTO;
    }

//    public TypeRoomDTO toDTOWhenInsert(TypeRoom typeRoom) {
//        return commonTransferData(typeRoom);
//    }

    public TypeRoomDTO commonTransferData(TypeRoom typeRoom) {
        TypeRoomDTO typeRoomDTO = new TypeRoomDTO();
        typeRoomDTO.setName(typeRoom.getName());
        typeRoomDTO.setNote(typeRoom.getNote());
        typeRoomDTO.setNumberAdult(typeRoom.getNumberAdult());
        typeRoomDTO.setNumberChildren(typeRoom.getNumberChildren());
        typeRoomDTO.setStatus(typeRoom.getStatus());
        return typeRoomDTO;
    }

    public String checkInput(TypeRoomDTO typeRoom) {
        String message = null;
        if (findByName(typeRoom.getName()) != null) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        }
        return message;
    }
}
