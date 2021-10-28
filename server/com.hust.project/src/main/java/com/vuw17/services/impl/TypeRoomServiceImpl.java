package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dto.typeroom.TypeRoomDTORequest;
import com.vuw17.entities.TypeRoom;
import com.vuw17.services.TypeRoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    private final TypeRoomDao typeRoomDao;

    public TypeRoomServiceImpl(TypeRoomDao typeRoomDao) {
        this.typeRoomDao = typeRoomDao;
    }

    @Override
    public String insertOne(TypeRoomDTORequest typeRoom) {
        String message = checkInput(typeRoom);
        if (message == null) {
            typeRoomDao.insertOne(toEntity(typeRoom));
            return ConstantVariableCommon.CREATE_SUCCESSFUL;
        }
        return message;
    }

    @Override
    public List<TypeRoomDTORequest> findAll() {
        List<TypeRoomDTORequest> typeRoomDTORequests = new ArrayList<>();
        List<TypeRoom> typeRooms = typeRoomDao.findAll();
        for (TypeRoom typeRoom : typeRooms) {
            typeRoomDTORequests.add(toDTO(typeRoom));
        }
        return typeRoomDTORequests;
    }

    @Override
    public String updateOne(TypeRoomDTORequest typeRoom) {
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
            TypeRoomDTORequest typeRoom = findById(id);
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
    public TypeRoomDTORequest findById(int id) {
        TypeRoom typeRoom = typeRoomDao.findById(id);
        if (typeRoom != null) {
            return toDTO(typeRoom);
        }
        return null;
    }
    

    @Override
    public TypeRoomDTORequest findByName(String name) {
        TypeRoom typeRoom = typeRoomDao.findByName(name);
        if (typeRoom != null) {
            return toDTO(typeRoom);
        }
        return null;
    }
    

    public TypeRoomDTORequest updateData(TypeRoomDTORequest oldData, TypeRoomDTORequest newData) {

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

    public TypeRoom toEntity(TypeRoomDTORequest typeRoomDTORequest) {
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setId(typeRoomDTORequest.getId());
        typeRoom.setName(typeRoomDTORequest.getName());
        typeRoom.setNote(typeRoomDTORequest.getNote());
        typeRoom.setNumberAdult(typeRoomDTORequest.getNumberAdult());
        typeRoom.setNumberChildren(typeRoomDTORequest.getNumberChildren());
        typeRoom.setStatus(typeRoomDTORequest.getStatus());

        return typeRoom;
    }
    //Vi luc insert id = null nen se ko set truong Id

    public TypeRoomDTORequest toDTO(TypeRoom typeRoom) {
        TypeRoomDTORequest typeRoomDTORequest = commonTransferData(typeRoom);
        typeRoomDTORequest.setId(typeRoom.getId());
        return typeRoomDTORequest;
    }

    public TypeRoomDTORequest toDTOWhenInsert(TypeRoom typeRoom) {
        return commonTransferData(typeRoom);
    }

    public TypeRoomDTORequest commonTransferData(TypeRoom typeRoom) {
        TypeRoomDTORequest typeRoomDTORequest = new TypeRoomDTORequest();
        typeRoomDTORequest.setName(typeRoom.getName());
        typeRoomDTORequest.setNote(typeRoom.getNote());
        typeRoomDTORequest.setNumberAdult(typeRoom.getNumberAdult());
        typeRoomDTORequest.setNumberChildren(typeRoom.getNumberChildren());
        typeRoomDTORequest.setStatus(typeRoom.getStatus());
        return typeRoomDTORequest;
    }

    public String checkInput(TypeRoomDTORequest typeRoom) {
        String message = null;
        if (findByName(typeRoom.getName()) != null) {
            message = ConstantVariableCommon.DUPLICATED_NAME;
        }
        return message;
    }
}
