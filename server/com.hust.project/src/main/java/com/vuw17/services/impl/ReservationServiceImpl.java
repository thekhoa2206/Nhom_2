package com.vuw17.services.impl;

import com.vuw17.common.Common;
import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.dao.jpa.ReservationDao;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dto.reservation.*;
import com.vuw17.entities.Guest;
import com.vuw17.entities.Reservation;;
import com.vuw17.entities.RoomReservation;
import com.vuw17.repositories.ReservationRepository;
import com.vuw17.repositories.RoomReservationRepository;
import com.vuw17.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final ReservationRepository reservationRepository;
    private final GuestDao guestDao;
    private final RoomDao roomDao;
    private final RoomReservationRepository roomReservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class.toString());

    public ReservationServiceImpl(ReservationDao reservationDao, ReservationRepository reservationRepository, GuestDao guestDao, RoomDao roomDao, RoomReservationRepository roomReservationRepository) {
        this.reservationDao = reservationDao;
        this.reservationRepository = reservationRepository;
        this.guestDao = guestDao;
        this.roomDao = roomDao;
        this.roomReservationRepository = roomReservationRepository;
    }

    // Hàm lấy danh sách và lọc theo keyword và trạng thái của đơn đặt phòng
    @Override
    public List<ReservationDTOResponse> findAllReservationByKeyword(String keyword){
        List<Reservation> reservations = reservationDao.findReservationByParam(keyword);
        List<ReservationDTOResponse> reservationDTOResponses = transferReservationToReservationDTO(reservations);
        return reservationDTOResponses;
    }

    //Hàm chuyển từ List Reservation sang List ReservationDTO
    private List<ReservationDTOResponse> transferReservationToReservationDTO(List<Reservation> reservations){
        List<ReservationDTOResponse> reservationDTOResponses = new ArrayList<>();
        for (Reservation reservation: reservations) {
            ReservationDTOResponse reservationDTOResponse = transferReservationToReservationDTO(reservation);
            reservationDTOResponses.add(reservationDTOResponse);
        }
        return reservationDTOResponses;
    }

    //Hàm chuyển từ  Reservation sang  ReservationDTO
    private ReservationDTOResponse transferReservationToReservationDTO(Reservation reservation){
        Guest guest = guestDao.findById(reservation.getGuestId());
        ReservationGuestDTO reservationGuestDTO = new ReservationGuestDTO(guest.getFirstName()+" "+guest.getLastName(), guest.getPhoneNumber());
        ReservationDTOResponse reservationDTOResponse = new ReservationDTOResponse( reservation.getId(), Common.getDate(reservation.getDateFrom()), Common.getDate(reservation.getDateTo()), ConstantVariableCommon.changeIntToStringReservationStatus(reservation.getStatus()), reservation.getNumberRoom(), reservationGuestDTO);
        return reservationDTOResponse;
    }
    //Hàm tìm kiếm reservation bằng id
    @Override
    public ReservationDetailDTOResponse findReservationDTOById(int id){
        Reservation reservation = reservationDao.findReservationById(id);
        ReservationDetailDTOResponse reservationDetailDTOResponse = transferReservationToReservationDTODetail(reservation);
        return reservationDetailDTOResponse;
    }

    //Hàm chuyển từ  Reservation sang  ReservationDTO
    private ReservationDetailDTOResponse transferReservationToReservationDTODetail(Reservation reservation){
        Guest guest = guestDao.findById(reservation.getGuestId());
        ReservationDetailDTOResponse reservationDetailDTOResponse = new ReservationDetailDTOResponse(reservation.getId(), reservation.getNote(), Common.getDate(reservation.getDateFrom()), Common.getDate(reservation.getDateTo()), ConstantVariableCommon.changeIntToStringReservationStatus(reservation.getStatus()), reservation.getNumberRoom(), guest.getFirstName()+guest.getLastName(), guest.getPhoneNumber());
        return reservationDetailDTOResponse;
    }

    @Override
    public void createReservation(ReservationDTORequest reservationDTORequest) throws ParseException {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(Common.getMilliSeconds(reservationDTORequest.getDateFrom()));
        reservation.setDateTo(Common.getMilliSeconds(reservationDTORequest.getDateFrom()));
        reservation.setGuestId(reservationDTORequest.getGuestId());
        reservation.setNote(reservation.getNote());
        reservation.setNumberRoom(reservation.getNumberRoom());
        reservation.setStatus(ConstantVariableCommon.STATUS_RESERVATION_1);
        saveReservation(reservation);
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveReservation(Reservation reservation){
        try{
            reservationRepository.save(reservation);
        }catch (Exception e){
            LOGGER.error("ERROR | Lỗi không lưu được đơn đặt phòng - ", e.getMessage());
        }
    }

    @Override
    public void updateReservation(int id, ReservationDTOUpdateRequest reservationDTOUpdateRequest) throws ParseException {
        Reservation reservation = reservationDao.findReservationById(id);
        reservation.setNumberRoom(reservationDTOUpdateRequest.getNumberRoom());
        reservation.setDateFrom(Common.getMilliSeconds(reservationDTOUpdateRequest.getDateFrom()));
        reservation.setDateTo(Common.getMilliSeconds(reservationDTOUpdateRequest.getDateTo()));
        reservation.setNote(reservationDTOUpdateRequest.getNote());
        saveReservation(reservation);
    }

    @Override
    public void changeStatusReservation(int id, int status){
        Reservation reservation = reservationDao.findReservationById(id);
        reservation.setStatus(status);
        saveReservation(reservation);
    }

    @Override
    public List<StatusReservation> listStatusReservation(){
        List<StatusReservation> listStatusReservation = new ArrayList<>();
        return listStatusReservation;
    }

    @Override
    public void chooseRoom(ReservationRoomDTORequest reservationRoomDTORequest){
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setReservationId(reservationRoomDTORequest.getReservationId());
        roomReservation.setRoomId(reservationRoomDTORequest.getRoomId());
        roomReservation.setStatus(ConstantVariableCommon.STATUS_ROOM_RESERVATION_1);
        saveRoomReservation(roomReservation);
    }
    @Transactional(rollbackOn = Exception.class)
    public void saveRoomReservation(RoomReservation roomReservation){
        try{
            roomReservationRepository.save(roomReservation);
        }catch (Exception e){
            LOGGER.error("ERROR || Lỗi không lưu được phòng đặt");
        }
    }
}
