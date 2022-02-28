package com.vuw17.dto.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
@Setter
@Getter
public class ServiceDTORequest {
    private String name;
    private String note;
    private int status;
    private BigDecimal price;
}
