package com.vuw17.services;

import com.vuw17.dto.reservation.ReservationDTO;
import com.vuw17.dto.user.UserDTOResponse;

public interface ReservationService {
    int insertOne(ReservationDTO reservationDTO, UserDTOResponse userDTOResponse);
}
