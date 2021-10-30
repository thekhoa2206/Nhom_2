package com.vuw17.dto.room;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RoomDTO extends BaseDTO {
    @Min(value = 1,message = "Ma khach san phai lon hon 0")
    private int hotelId;

    @NotBlank(message = "Ten phong khong duoc de trong")
    @Size(min = 1, max = 20, message = "Tên không dài quá 20 ký tự")
    private String name;

    @Min(value = 1,message = "Ma loai phong phai lon hon 0")
    private int typeRoomId;
    @Size(min = 0, max = 255, message = "Note không dài quá 255 ký tự")
    private String note;

    private int status;
}
