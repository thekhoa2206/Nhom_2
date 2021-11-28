package com.vuw17.dto.typeprice;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class TypePriceDTO extends BaseDTO {
    @NotEmpty(message = "Ten loai gia khong duoc de trong")
    private String name;
    private int status;
}
