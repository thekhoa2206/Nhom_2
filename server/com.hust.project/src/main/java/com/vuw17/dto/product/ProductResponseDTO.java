package com.vuw17.dto.product;

import com.vuw17.dto.typeproduct.TypeProductResponseDTO;
import com.vuw17.dto.unit.UnitResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDTO {
    private String name;
    private String note;
    private int quantity;
    private BigDecimal sellingPrice;
    private BigDecimal importPrice;
    private TypeProductResponseDTO typeProductResponseDTO;
    private UnitResponseDTO unitResponseDTO;

}
