package com.vuw17.dto.hotel;


import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HotelDTO extends BaseDTO {
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;
    @NotBlank(message = "Dia chi khong duoc de trong")
    private String address;
    @Size(min = 0, max = 255, message = "Note không dài quá 255 ký tự")
    private String note;
    @NotBlank(message = "So dien thoai khong duoc de trong")
    private String phoneNumber;

    private int status;
}
