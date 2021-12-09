package com.vuw17.dto.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
@Setter
@Getter
public class ServiceDTORequest {
    @NotEmpty(message = "Ten dich vu khong duoc de trong")
    private String name;
    private String note;
    private int status;
    @Min(value = 1,message = "Gia khong duoc < 0")
    private BigDecimal price;
}
