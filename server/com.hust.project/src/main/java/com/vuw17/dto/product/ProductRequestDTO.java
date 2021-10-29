package com.vuw17.dto.product;

import com.vuw17.dto.typeproduct.TypeProductResponseDTO;
import com.vuw17.dto.unit.UnitRequestDTO;
import com.vuw17.dto.unit.UnitResponseDTO;
import com.vuw17.entities.Unit;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {
    @NotNull(message = "Tên không được để trống")
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 20, message = "Tên không dài quá 30 ký tự")
    private String name;

    @Size(min = 1, max = 255, message = "Tên không dài quá 255 ký tự")
    private String note;

    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng không được âm")
    private int quantity;

    @NotNull(message = "Giá bán không được để trống")
    @NegativeOrZero(message = "Giá không được âm")
    private BigDecimal sellingPrice;

    private BigDecimal importPrice;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @PositiveOrZero(message = "Loại sản phẩm không được âm")
    private int typeProductId;

    private UnitRequestDTO unitRequestDTO;
}
