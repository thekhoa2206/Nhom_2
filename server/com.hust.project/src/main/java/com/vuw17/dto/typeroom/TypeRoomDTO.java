package com.vuw17.dto.typeroom;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class TypeRoomDTO extends BaseDTO {
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;
    @Size(min = 0, max = 255, message = "Note không dài quá 255 ký tự")
    private String note;

    @Min(value = 1,message = "So luong tre em phai lon hon 0")
    @Max(value = 2,message = "So luong tre em khong fuoc vuot qua 2")
    private int numberChildren;

    @Min(value = 1,message = "So luong nguoi lon phai lon hon 0")
    @Max(value = 2,message = "So luong nguoi lon khong fuoc vuot qua 2")
    private int numberAdult;

    private int status;
    @NotNull(message = "Giá không để trống")
    private BigDecimal price;
}
