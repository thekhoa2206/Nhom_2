package com.vuw17.services;

import com.vuw17.dto.reservation.*;
import com.vuw17.dto.user.UserDTOResponse;

import java.text.ParseException;
import java.util.List;

public interface ReservationService {
    // Hàm lấy danh sách và lọc theo keyword và trạng thái của đơn đặt phòng
    List<ReservationDTOResponse> findAllReservationByKeyword(String keyword);

    // Hàm tìm kiếm reservation bằng id
    ReservationDetailDTOResponse findReservationDTOById(int id);

    //Hàm tạo đơn đặt phòng
    void createReservation(ReservationDTORequest reservationDTORequest) throws ParseException;

    // Hàm sửa thông tin đặt phòng
    void updateReservation(int id, ReservationDTOUpdateRequest reservationDTOUpdateRequest) throws ParseException;

    //Thay đổi trạng thái status
    void changeStatusReservation(int id, int status);

    //Lấy list trạng thái
    List<StatusReservation> listStatusReservation();

    //Hàm đặt phòng cho khách
    void chooseRoom(ReservationRoomDTORequest reservationRoomDTORequest);
}
