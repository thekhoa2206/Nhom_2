package com.vuw17.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDTOResponse {
    private String note;
    private String fromDate;
    private String toDate;
    private String status;
    private int numberRoom;
    private String nameCustomer;
    private String phoneCustomer;
    private List<RoomReservationDTOResponse> listRoom;


}
