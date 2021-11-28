package com.vuw17.dto.roomprice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class RoomPriceDTO {
    private int typeRoomId;
    private int typePriceId;
    private BigDecimal price;
}
