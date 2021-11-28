package com.vuw17.dao.jdbc;

import com.vuw17.entities.Reservation;

public interface ReservationDAO {
    int insertOne(Reservation reservation);
}
