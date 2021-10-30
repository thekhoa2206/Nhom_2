package com.vuw17.dto.price;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Setter
@Getter
public class PriceDTORequest {
    @NotNull(message = "Tên giá không để trống")
    @NotBlank(message = "Tên giá không để trống")
    @Size(min = 1, max = 20, message = "Tên không dài quá 20 ký tự")
    private String name;

    @NotNull(message = "Giá không để trống")
    private BigDecimal price;
}
