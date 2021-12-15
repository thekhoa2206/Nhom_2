package com.vuw17.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservationRoomDTORequest {
    @NotBlank(message = "Ngày dự kiến đến không được để trống")
    @NotNull(message = "Ngày dự kiến đến không được để trống")
    private String dateFrom;
    @NotBlank(message = "Ngày dự kiến đi không được để trống")
    @NotNull(message = "Ngày dự kiến đi không được để trống")
    private String dateTo;
    @NotNull(message = "Khách đặt phòng không được để trống")
    private int guestId;
    @NotNull(message = "Id Phòng không được để trống")
    private int roomId;
    private int reservationId;
}
