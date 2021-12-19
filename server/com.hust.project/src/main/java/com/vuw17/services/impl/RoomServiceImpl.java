package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.RoomDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.dto.room.RoomUpdateDTO;
import com.vuw17.dto.service.ServiceUsedDTOResponse;
import com.vuw17.dto.typeprice.PriceDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.*;
import com.vuw17.services.CommonService;
import com.vuw17.services.GenericService;
import com.vuw17.services.GuestService;
import com.vuw17.services.RoomService;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl extends CommonService implements RoomService, GenericService<RoomDTO> {
    private final RoomDao roomDao;
    private final RoomDAO roomDAO;
    private final ServiceUsedDao serviceUsedDao;
    private final OccupiedRoomDao occupiedRoomDao;
    private final ServiceDao serviceDao;
    private final TypeRoomDao typeRoomDao;
    private final BaseServiceImpl baseService;
    private final GuestService guestService;
    private final HostedAtDao hostedAtDao;
    private final RoomPriceDao roomPriceDao;
    private final TypePriceDao typePriceDao;
    private static final String NOT_EXIST_TYPE_ROOM_ID = "Type Room ID does not exist";

    public RoomServiceImpl(RoomDao roomDao, RoomDAO roomDAO, TypeRoomDao typeRoomDao, TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, ServiceUsedDao serviceUsedDao, OccupiedRoomDao occupiedRoomDao, ServiceDao serviceDao, BaseServiceImpl baseService, GuestService guestService, HostedAtDao hostedAtDao, RoomPriceDao roomPriceDao, TypePriceDao typePriceDao) {
        super(tableDiaryDAO,typeActionDAO,typeActionDao,tableDiaryDao, baseService);
        this.roomDao = roomDao;
        this.roomDAO = roomDAO;
        this.typeRoomDao = typeRoomDao;
        this.serviceUsedDao = serviceUsedDao;
        this.occupiedRoomDao = occupiedRoomDao;
        this.serviceDao = serviceDao;
        this.baseService = baseService;
        this.guestService = guestService;
        this.hostedAtDao = hostedAtDao;
        this.roomPriceDao = roomPriceDao;
        this.typePriceDao = typePriceDao;
    }

    @Override
    public int insertOne(RoomDTO roomDTO, UserDTOResponse userDTOResponse) {
        String message = checkInput(roomDTO);
        if (message == null) {
            int id = roomDAO.insertOne(toEntity(roomDTO));
            if(id > 0){
                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,id,ConstantVariableCommon.table_room,userDTOResponse.getId());
                return id;
            }
        }
        return 0;
    }

    @Override
    public List<RoomDTOResponse> findAll() {
        List<RoomDTOResponse> roomDTOResponses = new ArrayList<>();
        for (Room room : roomDao.findAll()) {
            RoomDTOResponse roomDTOResponse = convertToRoomDTOResponse(room);
            // insert into list
            roomDTOResponses.add(roomDTOResponse);


        }
        return roomDTOResponses;
    }

    @Override
    public RoomDTOResponse updateOne(RoomUpdateDTO roomUpdateDTO, UserDTOResponse userDTOResponse) {
//        RoomDTOResponse roomDTOResponse = findById(roomUpdateDTO.getId());
//        if(roomDTOResponse != null){
//            boolean checkUpdated = roomDao.updateOne(toEntity(updateData(findById(id), roomDTO)));
//            if(checkUpdated){
//                DiaryDTO diaryDTO = checkDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE,id,ConstantVariableCommon.table_room);
//                diaryDTO.setUserId(userDTOResponse.getId());
//                baseService.saveDiary(diaryDTO);
//                return true;
//            }
//        }
        return null;

    }

    @Override
    public boolean deleteOne(int id, UserDTOResponse userDTOResponse) {
        RoomDTOResponse roomDTOResponse = findById(id);
        if (roomDTOResponse != null) {
            if (roomDao.deleteOne(id)) {
                saveDiary(ConstantVariableCommon.TYPE_ACTION_DELETE, id, ConstantVariableCommon.table_room,userDTOResponse.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public RoomDTOResponse findById(int id) {
        Room room = roomDao.findById(id);
        if (room != null) {
            return convertToRoomDTOResponse(room);
        }
        return null;
    }

    @Override
    public RoomDTOResponse findByName(String name) {
        Room room = roomDao.findByName(name);
        if (room != null) {
            return convertToRoomDTOResponse(room);
        }
        return null;
    }


    @Override
    public List<RoomDTOResponse> findByTypeRoomId(int typeRoomId) {
        List<RoomDTOResponse> roomDTOResponses = new ArrayList<>();
        for (Room room : roomDao.findByTypeRoomId(typeRoomId)) {
            roomDTOResponses.add(convertToRoomDTOResponse(room));
        }
        return roomDTOResponses;
    }
    public RoomUpdateDTO updateData(RoomUpdateDTO oldData,RoomUpdateDTO newData){
        if(!oldData.getRoomDTO().getName().equals(newData.getRoomDTO().getName())){
            oldData.getRoomDTO().setName(newData.getRoomDTO().getName());
        }
        if(findByTypeRoomId(newData.getRoomDTO().getTypeRoomId()) != null && oldData.getRoomDTO().getTypeRoomId() != newData.getRoomDTO().getTypeRoomId()){
            oldData.getRoomDTO().setTypeRoomId(newData.getRoomDTO().getTypeRoomId());
        }
        if(!oldData.getRoomDTO().getNote().equals(newData.getRoomDTO().getNote())) {
            oldData.getRoomDTO().setNote(newData.getRoomDTO().getNote());
        }

        //Price
//        List<PriceDTO> priceDTOS = oldData.getPrices();
//        for(int i = 0;i < priceDTOS.size();i++){
//            priceDTOS.get(i).
//        }
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

    public RoomDTO toDTO(RoomDTOResponse roomDTOResponse) {

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName(roomDTOResponse.getRoomName());
        roomDTO.setTypeRoomId(typeRoomDao.findByName(roomDTOResponse.getTypeRoomName()).getId());
        roomDTO.setStatus(roomDTOResponse.getStatus());
        roomDTO.setId(roomDTOResponse.getId());
        return roomDTO;
    }

    @Override
    public String checkInput(RoomDTO roomDTO) {
        String message = null;
       if(typeRoomDao.findById(roomDTO.getTypeRoomId()) == null){
            message = NOT_EXIST_TYPE_ROOM_ID;
        }
        if(roomDao.findByName(roomDTO.getName()) != null){
            message = "Trung ten";
        }
        return message;
    }
    public RoomDTOResponse convertToRoomDTOResponse(Room room){
        RoomDTOResponse roomDTOResponse = new RoomDTOResponse();
        roomDTOResponse.setId(room.getId());
        roomDTOResponse.setRoomName(room.getName());
        roomDTOResponse.setStatus(room.getStatus());
        //lay type room id de set type room name cho object response
        roomDTOResponse.setTypeRoomName(typeRoomDao.findById(room.getTypeRoomId()).getName());
        try {


            //lay check in /check out time/deposit  de set vao object response
            OccupiedRoom occupiedRoom = occupiedRoomDao.findByRoomId(room.getId());
            roomDTOResponse.setCheckInTime(occupiedRoom.getCheckInTime());
            roomDTOResponse.setCheckOutTime(occupiedRoom.getCheckOutTime());
            roomDTOResponse.setDeposit(occupiedRoom.getDeposit());

            //set list services used
            roomDTOResponse.setServicesUsed(getServiceUsedDTOResponses(occupiedRoom.getId()));

        //set list guests
        roomDTOResponse.setGuests(getGuests(occupiedRoom.getId()));

        //set list prices
        roomDTOResponse.setPrices(getPrices(room));

        }catch (NullPointerException e){
            Log.getLog("Null",e.getMessage(),true);
        }
        return roomDTOResponse;
    }
    public ServiceUsedDTOResponse setServiceUsedDTOResponse(ServiceUsed serviceUsed, com.vuw17.entities.Service service){
        ServiceUsedDTOResponse serviceUsedDTOResponse = new ServiceUsedDTOResponse();
        serviceUsedDTOResponse.setId(serviceUsed.getId());
        serviceUsedDTOResponse.setName(service.getName());
        serviceUsedDTOResponse.setPaid(serviceUsed.isPaid());
        serviceUsedDTOResponse.setPrice(service.getPrice());
        serviceUsedDTOResponse.setQuantity(serviceUsed.getQuantity());
        return serviceUsedDTOResponse;
    }
    //lay list Service Used thong qua occupiedRoomId
    public List<ServiceUsedDTOResponse> getServiceUsedDTOResponses(int occupiedRoomId){
        List<ServiceUsedDTOResponse> serviceUsedDTOResponses = new ArrayList<>();
        List<ServiceUsed> servicesUsed = serviceUsedDao.findServicesUsedByOccupiedRoomId(occupiedRoomId);
        for (int i = 0; i < servicesUsed.size(); i++) {
            ServiceUsed serviceUsed = servicesUsed.get(i);
            com.vuw17.entities.Service service = serviceDao.findById(serviceUsed.getServiceId());
            ServiceUsedDTOResponse serviceUsedDTOResponse = setServiceUsedDTOResponse(serviceUsed, service);
            serviceUsedDTOResponses.add(serviceUsedDTOResponse);
        }
        return serviceUsedDTOResponses;
    }
    //lay list guest thong qua occupiedRoomId
    public List<GuestDTO> getGuests(int occupiedRoomId){
        List<GuestDTO> guestDTOS = new ArrayList<>();
        List<HostedAt> hostedAts = hostedAtDao.findByOccupiedRoomId(occupiedRoomId);

        for(int i = 0;i < hostedAts.size();i++){
            guestDTOS.add(guestService.findById(hostedAts.get(i).getGuestId()));
        }
        return guestDTOS;
    }
    //lay list price cua phong nao do,thong qua room object
    public List<PriceDTO> getPrices(Room room){
        List<PriceDTO> prices = new ArrayList<>();
        int typeRoomId = room.getTypeRoomId();
        List<RoomPrice> roomPrices = roomPriceDao.findByTypeRoomId(typeRoomId);
        for(int i = 0;i < roomPrices.size();i++){
            PriceDTO priceDTO = new PriceDTO();
            priceDTO.setPrice(roomPrices.get(i).getPrice());
            priceDTO.setTypePriceId(roomPrices.get(i).getTypePriceId());
            priceDTO.setTypePriceName(typePriceDao.findById(roomPrices.get(i).getTypePriceId()).getName());
            prices.add(priceDTO);
        }
        return prices;
    }
}
