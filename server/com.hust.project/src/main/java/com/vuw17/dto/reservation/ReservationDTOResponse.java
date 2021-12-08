package com.vuw17.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ReservationDTOResponse {
    private int id;
    private String fromDate;
    private String toDate;
    private String status;
    private int numberRoom;
    ReservationGuestDTO reservationGuestDTO;
}
