package com.vuw17.services;

import com.vuw17.dto.roomprice.RoomPriceDTO;

public interface RoomPriceService {
    RoomPriceDTO findByTypeRoomIdAndTypePriceId(int typeRoomId,int typePriceId);
}
