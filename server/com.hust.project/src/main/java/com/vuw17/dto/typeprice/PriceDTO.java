package com.vuw17.dto.typeprice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PriceDTO {
    private int typePriceId;
    private String typePriceName;
    private BigDecimal price;
}
