package com.vuw17.dto.typeroom;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TypeRoomDTO extends BaseDTO {
    @NotBlank(message = "Ten khong duoc de trong")
    @Size(min = 1, max = 20, message = "Tên không dài quá 20 ký tự")
    private String name;
    @Size(min = 0, max = 255, message = "Note không dài quá 255 ký tự")
    private String note;

    @Min(value = 1,message = "So luong tre em phai lon hon 0")
    @Max(value = 2,message = "So luong tre em khong fuoc vuot qua 2")
    private int maxChild;

    @Min(value = 1,message = "So luong nguoi lon phai lon hon 0")
    @Max(value = 2,message = "So luong nguoi lon khong fuoc vuot qua 2")
    private int maxAdult;

    private int status;

    @NotEmpty(message = "Gia khong duoc de trong")
    private List<TypeRoomPriceDTO> typeRoomPriceList;
}
