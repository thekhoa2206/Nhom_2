package com.vuw17.dto.unit;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UnitRequestDTO {
    private int id;

    @NotNull(message = "Đơn vị không được để trống")
    @NotBlank(message = "Đơn vị không được để trống")
    private String name;
}
