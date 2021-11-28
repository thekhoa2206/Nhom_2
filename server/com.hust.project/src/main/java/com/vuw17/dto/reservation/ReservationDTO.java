package com.vuw17.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {
    private String note;

    @Size(min = 1,message = "Ngay du dinh den phai lon hon 0")
    private long dateFrom;

    @Size(min = 1,message = "Ngay du dinh roi di phai lon hon 0")
    private long dateTo;

    private int status;
    @Size(min = 1,message = "So luong phong du dinh phai lon hon 0")
    private int numberRoom;

    private int guestId;
}
