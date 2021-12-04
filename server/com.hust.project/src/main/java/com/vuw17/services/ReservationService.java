package com.vuw17.services;

import com.vuw17.dto.reservation.ReservationDTO;
import com.vuw17.dto.reservation.ReservationDTOResponse;
import com.vuw17.dto.reservation.ReservationDetailDTOResponse;
import com.vuw17.dto.user.UserDTOResponse;

import java.util.List;

public interface ReservationService {
    // Hàm lấy danh sách và lọc theo keyword và trạng thái của đơn đặt phòng
    List<ReservationDTOResponse> findAllReservationByKeyword(String keyword, int status);

    // Hàm tìm kiếm reservation bằng id
    ReservationDetailDTOResponse findReservationDTOById(int id);
}
