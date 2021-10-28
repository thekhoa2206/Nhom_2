package com.vuw17.dto.typeproduct;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class TypeProductRequestDTO {
    @NotNull(message = "Tên của loại sản phẩm không được để trống")
    @NotBlank(message = "Tên của loại sản phẩm không được để trống")
    @Size(min = 1, max = 20, message = "Tên không dài quá 20 ký tự")
    private String name;

    @Size(min = 0, max = 255, message = "Tên không dài quá 255 ký tự")
    private String note;
}
