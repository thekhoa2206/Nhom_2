package com.vuw17.dto.room;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoomDTO extends BaseDTO {
    @Min(value = 1,message = "Ma khach san phai lon hon 0")
    private int hotelId;

    @NotBlank(message = "Ten phong khong duoc de trong")
    private String name;

    @Min(value = 1,message = "Ma loai phong phai lon hon 0")
    private int typeRoomId;

    private String note;

    private int status;
}
