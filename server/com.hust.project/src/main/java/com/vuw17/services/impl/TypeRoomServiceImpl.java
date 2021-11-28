package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.*;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.dto.typeroom.TypeRoomPriceDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.RoomPrice;
import com.vuw17.entities.TypeRoom;
import com.vuw17.services.BaseService;
import com.vuw17.services.CommonService;
import com.vuw17.services.TypeRoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRoomServiceImpl extends CommonService implements TypeRoomService {
    private final TypeRoomDao typeRoomDao;
    private final TypeRoomDAO typeRoomDAO;
    private final RoomPriceDAO roomPriceDAO;
    private final TableDiaryDAO tableDiaryDAO;
    private final TypeActionDAO typeActionDAO;
    private final TypeActionDao typeActionDao;
    private final TableDiaryDao tableDiaryDao;
    private final BaseService baseService;
    private final RoomPriceDao roomPriceDao;

    public TypeRoomServiceImpl(TypeRoomDao typeRoomDao, TypeRoomDAO typeRoomDAO, RoomPriceDAO roomPriceDAO, TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, RoomPriceDao roomPriceDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.typeRoomDao = typeRoomDao;
        this.typeRoomDAO = typeRoomDAO;
        this.roomPriceDAO = roomPriceDAO;
        this.tableDiaryDAO = tableDiaryDAO;
        this.typeActionDAO = typeActionDAO;
        this.typeActionDao = typeActionDao;
        this.tableDiaryDao = tableDiaryDao;
        this.baseService = baseService;
        this.roomPriceDao = roomPriceDao;
    }

    @Override
    public int insertOne(TypeRoomDTO typeRoom, UserDTOResponse userDTOResponse) {
        String message = checkInput(typeRoom);
        if (message == null) {
//            typeRoomDao.insertOne(toEntity(typeRoom));
//            return ConstantVariableCommon.CREATE_SUCCESSFUL;
//            int priceId = getPriceId(typeRoom);
            int typeRoomId = typeRoomDAO.insertOne(toEntity(typeRoom));
            if (typeRoomId > 0) {
                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,typeRoomId,ConstantVariableCommon.table_type_room,userDTOResponse.getId());
                //Get type room price list to insert into room_price table
                List<TypeRoomPriceDTO> list = typeRoom.getTypeRoomPriceList();
                if(list != null){
                    for(int i = 0;i < list.size();i++){
                        TypeRoomPriceDTO typeRoomPriceDTO = list.get(i);
                        RoomPrice roomPrice = new RoomPrice(typeRoomPriceDTO.getTypePriceId(),typeRoomId,typeRoomPriceDTO.getPrice());
                        if(roomPriceDao.findByTypeRoomIdAndTypePriceId(typeRoomPriceDTO.getTypePriceId(),typeRoomId) == null){
                            int roomPriceId = roomPriceDAO.insertOne(roomPrice);
                            saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,roomPriceId,ConstantVariableCommon.table_room_price,userDTOResponse.getId());
                        }
                    }
                }
                return typeRoomId;
            }
        }
        return 0;
    }

    //Check gia,ton tai trong bang price thi lay ra dung,con khong thi them vao
    public int getPriceId(TypeRoomDTO typeRoom) {
//        Price price = priceDao.findByPrice(typeRoom.getPrice());
//        int priceId = 0;
//        if (price != null) {
//            priceId = price.getId();
//        } else {
//            priceId = priceDAO.insertOne(new Price(typeRoom.getPrice().toString(), typeRoom.getPrice()));
//        }
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
    public boolean updateOne(TypeRoomDTO typeRoom, UserDTOResponse userDTOResponse) {
        int id = typeRoom.getId();
        String message = checkInput(typeRoom);
        TypeRoomDTO typeRoomBefore = findById(id);
        if (id > 0 && typeRoomBefore != null && message == null && !checkDataNotChanges(typeRoomBefore, typeRoom)) {
            TypeRoomDTO typeRoomUpdate = updateData(typeRoomBefore, typeRoom);
//            int priceId = getPriceId(typeRoomUpdate);
            boolean checkUpdated = typeRoomDao.updateOne(toEntity(typeRoomUpdate));
            if (checkUpdated) {
//                //Cap nhat bang room_price neu price thay doi
//                if (roomPriceDao.findByTypeRoomId(id).getTypePriceId() != priceId) {
////                    roomPriceDao.updateOne(new RoomPrice(priceId, id));
//                }
                //Update Diary
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, id, ConstantVariableCommon.table_hotel);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteOne(int id, UserDTOResponse userDTOResponse) {
        TypeRoomDTO typeRoomDTO = findById(id);
        if (typeRoomDTO != null && typeRoomDao.deleteOne(id)) {
            DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_DELETE, id, ConstantVariableCommon.table_type_room);
            diaryDTO.setUserId(userDTOResponse.getId());
            baseService.saveDiary(diaryDTO);
            return true;
        }
        return false;
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
        if (newData.getMaxAdult() != oldData.getMaxAdult()) {
            oldData.setMaxAdult(newData.getMaxAdult());
        }
        if (newData.getMaxChild() != oldData.getMaxChild()) {
            oldData.setMaxChild(newData.getMaxChild());
        }
        if (newData.getStatus() > 0 && newData.getStatus() <= ConstantVariableCommon.STATUS_TYPE_ROOM_3 && newData.getStatus() != oldData.getStatus()) {
            oldData.setStatus(newData.getStatus());
        }
        return oldData;
    }

    //Chi can 1 data bi thay doi la cho update,neu khong thay doi cai gi thi khong cho
    public boolean checkDataNotChanges(TypeRoomDTO typeRoomDTOBefore, TypeRoomDTO typeRoomDTOAfter) {
        boolean checkName = typeRoomDTOBefore.getName().equalsIgnoreCase(typeRoomDTOAfter.getName());
        boolean checkNote = typeRoomDTOBefore.getNote().equalsIgnoreCase(typeRoomDTOAfter.getNote());
        boolean checkNumberChildren = (typeRoomDTOBefore.getMaxChild() == typeRoomDTOAfter.getMaxChild());
        boolean checkNumberAdult = (typeRoomDTOBefore.getMaxAdult() == typeRoomDTOAfter.getMaxAdult());
        return checkName && checkNote && checkNumberChildren && checkNumberAdult;
    }

    public TypeRoom toEntity(TypeRoomDTO typeRoomDTO) {
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setId(typeRoomDTO.getId());
        typeRoom.setName(typeRoomDTO.getName());
        typeRoom.setMaxAdult(typeRoomDTO.getMaxAdult());
        typeRoom.setMaxChild(typeRoomDTO.getMaxChild());
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
        typeRoomDTO.setMaxAdult(typeRoom.getMaxAdult());
        typeRoomDTO.setMaxChild(typeRoom.getMaxChild());
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
