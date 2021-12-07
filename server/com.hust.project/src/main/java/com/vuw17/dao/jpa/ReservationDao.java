package com.vuw17.dao.jpa;

import com.vuw17.entities.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao {

    //Hàm lấy danh sách reservation theo param
    List<Reservation> findReservationByParam(String keyword, int status);

    //hàm tìm reservation theo id
    Reservation findReservationById(int id);
}
