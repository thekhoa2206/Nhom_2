package com.vuw17.dto.room;

import com.vuw17.dto.BaseDTO;
import com.vuw17.dto.typeprice.PriceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RoomUpdateDTO extends BaseDTO {
    private RoomDTO roomDTO;
    private List<PriceDTO> prices;
}
