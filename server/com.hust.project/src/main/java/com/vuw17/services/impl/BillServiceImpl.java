package com.vuw17.services.impl;

import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.BillResponseDTO;
import com.vuw17.dto.bill.BillResponse;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.entities.*;
import com.vuw17.repositories.GuestRepository;
import com.vuw17.services.BaseService;
import com.vuw17.services.BillService;
import com.vuw17.services.CommonService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl extends CommonService implements BillService {
    private final BillDao billDao;
    private final OccupiedRoomDao occupiedRoomDao;
    private final HostedAtDao hostedAtDao;
    private final GuestServiceImpl guestService;
    private final GuestDao guestDao;
    private final RoomDao roomDao;
    private final ServiceDao serviceDao;
    private final ServiceUsedDao serviceUsedDao;
    private final RoomServiceImpl roomService;
    public BillServiceImpl(RoomServiceImpl roomService,ServiceUsedDao serviceUsedDao,ServiceDao serviceDao,RoomDao roomDao,GuestServiceImpl guestService,HostedAtDao hostedAtDao,TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, GuestDao guestDao, GuestDAO guestDAO, GuestRepository guestRepository,BillDao billDao,OccupiedRoomDao occupiedRoomDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.billDao = billDao;
        this.occupiedRoomDao = occupiedRoomDao;
        this.hostedAtDao = hostedAtDao;
        this.guestService = guestService;
        this.guestDao = guestDao;
        this.roomDao = roomDao;
        this.serviceDao = serviceDao;
        this.serviceUsedDao = serviceUsedDao;
        this.roomService = roomService;
    }
    @Override
    public List<BillResponse> findAll() {
        List<BillResponse> billResponses = new ArrayList<>();
        for(int i = 0;i < billDao.findAll().size();i++){
            billResponses.add(toBillResponse(billDao.findAll().get(i)));
        }
        return billResponses;
    }

    //Convert tu Bill -> BillResponseDTO

    public BillResponse toBillResponse(Bill bill){
        BillResponse billResponse = new BillResponse();

        //get check in time,check out time
        OccupiedRoom occupiedRoom = occupiedRoomDao.findByBillId(bill.getId());
        //Get list guest of room
        List<HostedAt> hostedAts = hostedAtDao.findByOccupiedRoomId(occupiedRoom.getId());
        billResponse.setId(bill.getId());
        billResponse.setStatus(bill.getStatus());
        billResponse.setCheckInTime(occupiedRoom.getCheckInTime());
        billResponse.setCheckOutTime(occupiedRoom.getCheckOutTime());
        List<GuestDTO> guests = new ArrayList<>();
        for(int i = 0;i < hostedAts.size();i++){
            GuestDTO guestDTO = guestService.toDTO(guestDao.findById(hostedAts.get(i).getGuestId()));
            guests.add(guestDTO);
        }
        billResponse.setRoomName(roomDao.findById(occupiedRoom.getRoomId()).getName());
        billResponse.setGuests(guests);
        billResponse.setStatus(bill.getStatus());
        billResponse.setTotalPrice(getTotalPrices(occupiedRoom.getId()).add(bill.getAdditionalFee()).subtract(bill.getReducedFee()));

        return billResponse;
    }

    public BigDecimal getTotalPrices(int occupiedRoomId){
        BigDecimal servicesPrice = new BigDecimal(0);
        List<ServiceUsed> serviceUseds = serviceUsedDao.findServicesUsedByOccupiedRoomId(occupiedRoomId);
        for(int i = 0;i < serviceUseds.size();i++){
            com.vuw17.entities.Service service = serviceDao.findById(serviceUseds.get(i).getServiceId());
            servicesPrice = servicesPrice.add(service.getPrice().multiply(new BigDecimal(serviceUseds.get(i).getQuantity())));
        }
        RoomDTOResponse roomDTOResponse = roomService.convertToRoomDTOResponse(roomDao.findById(occupiedRoomDao.findById(occupiedRoomId).getRoomId()));

        BigDecimal totalPrice = roomDTOResponse.getSumOfPrices().add(servicesPrice);
        return totalPrice;

    }
}
