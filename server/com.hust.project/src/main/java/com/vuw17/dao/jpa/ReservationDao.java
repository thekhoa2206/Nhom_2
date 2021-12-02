package com.vuw17.dao.jpa;

import com.vuw17.entities.Reservation;
import com.vuw17.entities.ReservationRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao {

    //Hàm lấy danh sách reservation theo param
    List<Reservation> findReservationByParam(String keyword, int status);

    //hàm tìm reservation theo id
    Reservation findReservationById(int id);

    //Hàm tìm reservation_room theo id reservation_id;
    List<ReservationRoom> findReservationRoomByReservationId(int id);
}
