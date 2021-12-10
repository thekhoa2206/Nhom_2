package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.*;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkin.InsertServiceRequest;
import com.vuw17.dto.service.ServiceUsedDTORequest;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Bill;
import com.vuw17.entities.HostedAt;
import com.vuw17.entities.OccupiedRoom;
import com.vuw17.entities.ServiceUsed;
import com.vuw17.services.BaseService;
import com.vuw17.services.CheckInService;
import com.vuw17.services.CommonService;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckInServiceImpl extends CommonService implements CheckInService {
    private final OccupiedRoomDAO occupiedRoomDAO;
    private final OccupiedRoomDao occupiedRoomDao;
    private final ServiceUsedDAO serviceUsedDAO;
    private final ServiceUsedDao serviceUsedDao;
    private final HostedAtDAO hostedAtDAO;
    private final GuestDao guestDao;
    private final ServiceDao serviceDao;
    private final RoomDao roomDao;
    private final BillDAO billDAO;
    private final HostedAtDao hostedAtDao;

    public CheckInServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, OccupiedRoomDAO occupiedRoomDAO, OccupiedRoomDao occupiedRoomDao, ServiceUsedDAO serviceUsedDAO, ServiceUsedDao serviceUsedDao, HostedAtDAO hostedAtDAO, GuestDao guestDao, ServiceDao serviceDao, RoomDao roomDao, BillDAO billDAO, HostedAtDao hostedAtDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.occupiedRoomDAO = occupiedRoomDAO;
        this.occupiedRoomDao = occupiedRoomDao;
        this.serviceUsedDAO = serviceUsedDAO;
        this.serviceUsedDao = serviceUsedDao;
        this.hostedAtDAO = hostedAtDAO;
        this.guestDao = guestDao;
        this.serviceDao = serviceDao;
        this.roomDao = roomDao;
        this.billDAO = billDAO;
        this.hostedAtDao = hostedAtDao;
    }

    @Override
    public int checkIn(CheckInRequest checkinRequest, UserDTOResponse userDTOResponse) {
        List<Integer> guestIds = checkinRequest.getGuestIds();

        List<ServiceUsedDTORequest> servicesUsed = checkinRequest.getServicesUsed();
        //Insert occupied_room -> insert service_used -> insert hosted_at
        if (checkinRequest.getCheckOutTime() > System.currentTimeMillis() && roomDao.findById(checkinRequest.getRoomId()) != null && !isOccupied(checkinRequest.getRoomId())) {
            if(checkinRequest.getBillId() <= 0){
                //Create a Bill object
                int billId = billDAO.insertOne(new Bill(new BigDecimal(0),new BigDecimal(0),"",checkinRequest.getDeposit(),false));
               if(billId > 0){
                   checkinRequest.setBillId(billId);
                   saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, billId, ConstantVariableCommon.table_bill, userDTOResponse.getId());
               }
            }

            //create a object OccupiedRoom to insert into table occupied_room
            OccupiedRoom occupiedRoom = new OccupiedRoom();
            occupiedRoom.setCheckOutTime(checkinRequest.getCheckOutTime());
            occupiedRoom.setDeposit(checkinRequest.getDeposit());
            occupiedRoom.setRoomId(checkinRequest.getRoomId());
            occupiedRoom.setBillId(checkinRequest.getBillId());

            //Kiểm tra khách đã check out chưa ?
            for(int i = 0;i < guestIds.size();i++){
                if(!checkGuestCheckedOut(guestIds.get(i))){
                    return 0;
                }
            }

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
        for (int i = 0; i < occupiedRooms.size(); i++) {
            if (occupiedRooms.get(i).getRoomId() == roomId) {
                return true;
            }
        }
        return false;
    }

    //phai tim theo occupied_id va paid va service_id
    @Override
    public boolean insertServicesIntoRoom(InsertServiceRequest insertServiceRequest, UserDTOResponse userDTOResponse) {
        int roomId = insertServiceRequest.getRoomId();
        OccupiedRoom occupiedRoom = occupiedRoomDao.findByRoomId(roomId);
        if (occupiedRoom.getStatus() == 1) {
            int occupiedRoomId = occupiedRoom.getId();
            insertServicesUsed(insertServiceRequest.getServices(), occupiedRoomId, userDTOResponse);
            return true;
        }
        return false;
    }

    //    insert service_used
    public void insertServicesUsed(List<ServiceUsedDTORequest> servicesUsed, int occupiedRoomId, UserDTOResponse userDTOResponse) {
        try {
            for (int i = 0; i < servicesUsed.size(); i++) {
                //lay ra 1 object tu list service used request
                ServiceUsedDTORequest serviceUsedDTORequest = servicesUsed.get(i);
                if (serviceDao.findById(serviceUsedDTORequest.getServiceId()) != null) {
                    //lay ra danh sach object service used request tu db by OccupiedRoomId
                    List<ServiceUsed> serviceUsedEntities = serviceUsedDao.findServicesUsedByOccupiedRoomId(occupiedRoomId);
                    ServiceUsed serviceUsed = checkContains(serviceUsedEntities, serviceUsedDTORequest);
                    if (serviceUsed != null) {
                        //neu trung occupied room id && service id && paid thi update
                        //ko thi insert moi
                        serviceUsed.setQuantity(serviceUsed.getQuantity() + serviceUsedDTORequest.getQuantity());
                        boolean check = serviceUsedDao.update(serviceUsed);
                        if (check) {
                            saveDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, serviceUsed.getId(), ConstantVariableCommon.table_service_used, userDTOResponse.getId());
                        }
                    } else {
                        ServiceUsed newServiceUsed = new ServiceUsed();
                        newServiceUsed.setOccupiedRoomId(occupiedRoomId);
                        newServiceUsed.setServiceId(serviceUsedDTORequest.getServiceId());
                        newServiceUsed.setQuantity(serviceUsedDTORequest.getQuantity());
                        newServiceUsed.setPaid(serviceUsedDTORequest.isPaid());
                        int serviceUsedId = serviceUsedDAO.insertOne(newServiceUsed);
                        if (serviceUsedId > 0) {
                            saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, serviceUsedId, ConstantVariableCommon.table_service_used, userDTOResponse.getId());
                        }
                    }
                }
            }
        }catch (NullPointerException e){
            Log.getLog("NULL",e.getMessage(),false);
        }
    }

    //    insert hosted_at
    public int insertHostedAt(List<Integer> guestIds, int occupiedRoomId, UserDTOResponse userDTOResponse) {
        if (guestIds != null && guestIds.size() > 0) {
            for (int i = 0; i < guestIds.size(); i++) {
                int guestId = guestIds.get(i);
                if (guestDao.findById(guestId) != null && checkGuestCheckedOut(guestId)) {
                    HostedAt hostedAt = new HostedAt();
                    hostedAt.setGuestId(guestId);
                    hostedAt.setOccupiedRoomId(occupiedRoomId);

                    int hostedAtId = hostedAtDAO.insertOne(hostedAt);
                    if (hostedAtId > 0) {
                        saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE, hostedAtId, ConstantVariableCommon.table_hosted_at, userDTOResponse.getId());
                        return hostedAtId;
                    }
                }
            }
        }
        return 0;
    }

    public ServiceUsed checkContains(List<ServiceUsed> servicesUsed, ServiceUsedDTORequest serviceUsedDTORequest) {
        for (int i = 0; i < servicesUsed.size(); i++) {
            ServiceUsed serviceUsed = servicesUsed.get(i);
            if (serviceUsed.getServiceId() == serviceUsedDTORequest.getServiceId() && serviceUsed.isPaid() == serviceUsedDTORequest.isPaid()) {
                return serviceUsed;
            }
        }
        return null;

    }
    //Kiem tra xem khach này đã check out chưa thông qua guest id
    public boolean checkGuestCheckedOut(int guestId){
        //Tìm xem thằng guestId này ở phòng nào ? và phòng đó đã check out chưa
        List<HostedAt> hostedAts = hostedAtDao.findByGuestId(guestId);
        for(int i = 0;i < hostedAts.size();i++){
            OccupiedRoom occupiedRoom = occupiedRoomDao.findByIdAndStatus(hostedAts.get(i).getOccupiedRoomId());
            return occupiedRoom != null ? false : true;
        }
        return true;
    }
}
