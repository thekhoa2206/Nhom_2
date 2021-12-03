package com.vuw17.services.impl;

import com.vuw17.common.Common;
import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.RoomDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.guest.GuestReservationDTO;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Room;
import com.vuw17.services.CommonService;
import com.vuw17.services.GenericService;
import com.vuw17.services.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl extends CommonService implements RoomService, GenericService<RoomDTO> {
    private final RoomDao roomDao;
    private final RoomDAO roomDAO;
    private final TypeRoomDao typeRoomDao;
    private final BaseServiceImpl baseService;
    private static final String NOT_EXIST_TYPE_ROOM_ID = "Type Room ID does not exist";

    public RoomServiceImpl(RoomDao roomDao, RoomDAO roomDAO, TypeRoomDao typeRoomDao, TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseServiceImpl baseService) {
        super(tableDiaryDAO,typeActionDAO,typeActionDao,tableDiaryDao, baseService);
        this.roomDao = roomDao;
        this.roomDAO = roomDAO;
        this.typeRoomDao = typeRoomDao;
        this.baseService = baseService;
    }

    @Override
    public int insertOne(RoomDTO roomDTO, UserDTOResponse userDTOResponse) {
        String message = checkInput(roomDTO);
        if (message == null) {
            int id = roomDAO.insertOne(toEntity(roomDTO));
            if(id > 0){
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,id,ConstantVariableCommon.table_room);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return id;
            }
        }
        return 0;
    }

    @Override
    public List<RoomDTO> findAll() {
        return convertList(roomDao.findAll());
    }

    @Override
    public boolean updateOne(RoomDTO roomDTO, UserDTOResponse userDTOResponse) {
        int id = roomDTO.getId();
        String message = checkInput(roomDTO);
        if(id > 0 && findById(id) != null && message == null){
            boolean checkUpdated = roomDao.updateOne(toEntity(updateData(findById(id), roomDTO)));
            if(checkUpdated){
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE,id,ConstantVariableCommon.table_room);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean deleteOne(int id, UserDTOResponse userDTOResponse) {
        RoomDTO roomDTO = findById(id);
        if (roomDTO != null) {
            if (roomDao.deleteOne(id)) {
                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_DELETE, id, ConstantVariableCommon.table_room);
                diaryDTO.setUserId(userDTOResponse.getId());
                baseService.saveDiary(diaryDTO);
                return true;
            }
        }
        return false;
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
        return oldData;
    }
    public Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setName(roomDTO.getName());
        room.setStatus(roomDTO.getStatus());
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
        roomDTO.setTypeRoomId(room.getTypeRoomId());
        roomDTO.setStatus(room.getStatus());
//        roomDTO.setGuest(new GuestReservationDTO());
//        roomDTO.setDateFrom(Common.getDateByMilliSeconds(System.currentTimeMillis()));
//        roomDTO.setDateTo(Common.getDateByMilliSeconds(System.currentTimeMillis()));
//       roomDTO.setReservation(reservationDTO);
        return roomDTO;
    }

    @Override
    public String checkInput(RoomDTO roomDTO) {
        String message = null;
        RoomDTO dto = findByName(roomDTO.getName());
       if(typeRoomDao.findById(roomDTO.getTypeRoomId()) == null){
            message = NOT_EXIST_TYPE_ROOM_ID;
        }
        if(typeRoomDao.findByName(roomDTO.getName()) == null){
            message = "Trung ten";
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
