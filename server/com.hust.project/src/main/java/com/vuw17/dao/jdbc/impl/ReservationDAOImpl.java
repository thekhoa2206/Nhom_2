package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.ReservationDAO;
import com.vuw17.entities.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationDAOImpl extends BaseDAO<Reservation> implements ReservationDAO {
    @Override
    public int insertOne(Reservation reservation) {
        String sql = "INSERT INTO reservation(note,date_from,date_to,status,number_room,guest_id) VALUES(?,?,?,?,?,?)";
        return insertOne(sql, reservation.getNote(), reservation.getDateFrom(), reservation.getDateTo(), ConstantVariableCommon.STATUS_RESERVATION_1, reservation.getNumberRoom(), reservation.getGuestId());
    }
}
