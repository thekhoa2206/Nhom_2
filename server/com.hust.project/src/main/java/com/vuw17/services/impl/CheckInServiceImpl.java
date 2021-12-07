package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.*;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.service.ServiceUsedDTORequest;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.HostedAt;
import com.vuw17.entities.OccupiedRoom;
import com.vuw17.entities.ServiceUsed;
import com.vuw17.services.BaseService;
import com.vuw17.services.CheckInService;
import com.vuw17.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInServiceImpl extends CommonService implements CheckInService {
    private final OccupiedRoomDAO occupiedRoomDAO;
    private final OccupiedRoomDao occupiedRoomDao;
    private final ServiceUsedDAO serviceUsedDAO;
    private final HostedAtDAO hostedAtDAO;
    private final GuestDao guestDao;
    private final ServiceDao serviceDao;
    private final RoomDao roomDao;

    public CheckInServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, OccupiedRoomDAO occupiedRoomDAO, OccupiedRoomDao occupiedRoomDao, ServiceUsedDAO serviceUsedDAO, HostedAtDAO hostedAtDAO, GuestDao guestDao, ServiceDao serviceDao, RoomDao roomDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.occupiedRoomDAO = occupiedRoomDAO;
        this.occupiedRoomDao = occupiedRoomDao;
        this.serviceUsedDAO = serviceUsedDAO;
        this.hostedAtDAO = hostedAtDAO;
        this.guestDao = guestDao;
        this.serviceDao = serviceDao;
        this.roomDao = roomDao;
    }

    @Override
    public int checkIn(CheckInRequest checkinRequest, UserDTOResponse userDTOResponse) {
        List<Integer> guestIds = checkinRequest.getGuestIds();

        List<ServiceUsedDTORequest> servicesUsed = checkinRequest.getServicesUsed();
        //Insert occupied_room -> insert service_used -> insert hosted_at
        if (checkinRequest.getCheckOutTime() > checkinRequest.getCheckInTime() && roomDao.findById(checkinRequest.getRoomId()) != null && !isOccupied(checkinRequest.getRoomId())) {
            //create a object OccupiedRoom to insert into table occupied_room
            OccupiedRoom occupiedRoom = new OccupiedRoom();
            occupiedRoom.setCheckInTime(checkinRequest.getCheckInTime());
            occupiedRoom.setCheckOutTime(checkinRequest.getCheckOutTime());
            occupiedRoom.setDeposit(checkinRequest.getDeposit());
            occupiedRoom.setRoomId(checkinRequest.getRoomId());

            int occupiedRoomId = occupiedRoomDAO.insertOne(occupiedRoom);
            if (occupiedRoomId > 0) {
                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, occupiedRoomId, ConstantVariableCommon.table_occupied_room, userDTOResponse.getId());
                insertServicesUsed(servicesUsed, occupiedRoomId, userDTOResponse);
                insertHostedAt(guestIds, occupiedRoomId, userDTOResponse);
                return occupiedRoomId;
            }

        }
        return 0;
    }

    @Override
    public boolean isOccupied(int roomId) {
        List<OccupiedRoom> occupiedRooms = occupiedRoomDao.findOccupiedRooms();
        for(int i = 0;i < occupiedRooms.size();i++){
            if(occupiedRooms.get(i).getRoomId() == roomId){
                return true;
            }
        }
        return false;
    }

    //    insert service_used
    public void insertServicesUsed(List<ServiceUsedDTORequest> servicesUsed, int occupiedRoomId, UserDTOResponse userDTOResponse) {
        if (servicesUsed != null && servicesUsed.size() > 0) {
            for (int i = 0; i < servicesUsed.size(); i++) {
                ServiceUsedDTORequest serviceUsedDTORequest = servicesUsed.get(i);
                if (serviceDao.findById(serviceUsedDTORequest.getServiceId()) != null) {
                    ServiceUsed serviceUsed = new ServiceUsed();
                    serviceUsed.setOccupiedRoomId(occupiedRoomId);
                    serviceUsed.setServiceId(serviceUsedDTORequest.getServiceId());
                    serviceUsed.setQuantity(serviceUsedDTORequest.getQuantity());
                    serviceUsed.setPaid(serviceUsedDTORequest.isPaid());
                    int serviceUsedId = serviceUsedDAO.insertOne(serviceUsed);
                    if (serviceUsedId > 0) {
                        saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, serviceUsedId, ConstantVariableCommon.table_service_used, userDTOResponse.getId());
                    }
                }
            }
        }
    }

    //    insert hosted_at
    public void insertHostedAt(List<Integer> guestIds, int occupiedRoomId, UserDTOResponse userDTOResponse) {
        if (guestIds != null && guestIds.size() > 0) {
            for (int i = 0; i < guestIds.size(); i++) {
                int guestId = guestIds.get(i);
                if (guestDao.findById(guestId) != null) {
                    HostedAt hostedAt = new HostedAt();
                    hostedAt.setGuestId(guestId);
                    hostedAt.setOccupiedRoomId(occupiedRoomId);

                    int hostedAtId = hostedAtDAO.insertOne(hostedAt);
                    if (hostedAtId > 0) {
                        saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, hostedAtId, ConstantVariableCommon.table_hosted_at, userDTOResponse.getId());
                    }
                }
            }
        }
    }
}
