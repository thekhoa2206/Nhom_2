package com.vuw17.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ServiceDTOResponse {
    private String name;
    private String note;
    private int status;
    private BigDecimal price;
}
