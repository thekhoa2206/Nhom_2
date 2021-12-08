package com.vuw17.services.impl;

import com.vuw17.common.Common;
import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.dao.jpa.ReservationDao;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dto.reservation.ReservationDTOResponse;
import com.vuw17.dto.reservation.ReservationDetailDTOResponse;
import com.vuw17.dto.reservation.ReservationGuestDTO;
import com.vuw17.entities.Guest;
import com.vuw17.entities.Reservation;
import com.vuw17.entities.Room;
import com.vuw17.repositories.ReservationRepository;
import com.vuw17.services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final ReservationRepository reservationRepository;
    private final GuestDao guestDao;
    private final RoomDao roomDao;


    public ReservationServiceImpl(ReservationDao reservationDao, ReservationRepository reservationRepository, GuestDao guestDao, RoomDao roomDao) {
        this.reservationDao = reservationDao;
        this.reservationRepository = reservationRepository;
        this.guestDao = guestDao;
        this.roomDao = roomDao;
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
}
