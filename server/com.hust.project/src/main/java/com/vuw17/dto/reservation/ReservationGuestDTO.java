package com.vuw17.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservationGuestDTO {
    private String nameCustomer;
    private String phoneCustomer;
}
