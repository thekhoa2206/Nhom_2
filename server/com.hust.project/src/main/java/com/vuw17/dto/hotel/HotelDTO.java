package com.vuw17.dto.hotel;


import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class HotelDTO extends BaseDTO {
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;
    @NotBlank(message = "Dia chi khong duoc de trong")
    private String address;

    private String note;
    @NotBlank(message = "So dien thoai khong duoc de trong")
    private String phoneNumber;

    private int status;
}
