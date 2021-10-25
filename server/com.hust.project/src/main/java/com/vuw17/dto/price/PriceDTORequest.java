package com.vuw17.dto.price;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class PriceDTORequest {
    @NotNull(message = "Tên giá không để trống")
    @NotBlank(message = "Tên giá không để trống")
    private String name;

    @NotNull(message = "Giá không để trống")
    private BigDecimal price;
}
