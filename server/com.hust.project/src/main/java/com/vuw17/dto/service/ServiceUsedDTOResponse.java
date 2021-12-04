package com.vuw17.dto.service;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ServiceUsedDTOResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private boolean paid;
}
