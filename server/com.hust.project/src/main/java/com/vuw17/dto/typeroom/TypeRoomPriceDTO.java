package com.vuw17.dto.typeroom;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Getter
@Setter
public class TypeRoomPriceDTO {
    @Size(min = 1,message = "Type Price Id must be greater than 0")
    private int typePriceId;
    @NotNull(message = "Giá không để trống")
    @Min(value = 1,message = "Gia tien phai lon hon 0")
    private BigDecimal price;
}
