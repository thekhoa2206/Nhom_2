package com.vuw17.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservationRoomDTORequest {
    @NotNull(message = "Id Phòng không được để trống")
    private int roomId;
    private int reservationId;
}
