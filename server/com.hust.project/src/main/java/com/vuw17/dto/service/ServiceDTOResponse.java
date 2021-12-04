package com.vuw17.dto.service;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
public class ServiceDTOResponse {
    private String name;
    private String note;
    private int status;
    private BigDecimal price;
}
