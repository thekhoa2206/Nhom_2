package com.vuw17.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservationDTOUpdateRequest {
    @NotNull(message = "Số phòng không được để trống")
    private int numberRoom;
    @NotNull(message = "Ngày dự kiến đến không được để trống")
    @NotBlank(message = "Ngày dự kiến đến không được để trống")
    private String dateFrom;
    @NotNull(message = "Ngày dự kiến đi không được để trống")
    @NotBlank(message = "Ngày dự kiến đi không được để trống")
    private String dateTo;

    private String note;
}
