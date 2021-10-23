package com.vuw17.dto.price;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PriceDTOResponse {
    private String name;
    private BigDecimal price;

}
