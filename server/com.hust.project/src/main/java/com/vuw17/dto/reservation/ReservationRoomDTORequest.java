package com.vuw17.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservationRoomDTORequest {
    private int roomId;
    private int reservationId;
}
