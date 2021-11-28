package com.vuw17.services.impl;

import com.vuw17.dao.jpa.RoomPriceDao;
import com.vuw17.dto.roomprice.RoomPriceDTO;
import com.vuw17.entities.RoomPrice;
import com.vuw17.services.RoomPriceService;
import org.springframework.stereotype.Service;

@Service
public class RoomPriceServiceImpl implements RoomPriceService {
    private final RoomPriceDao roomPriceDao;

    public RoomPriceServiceImpl(RoomPriceDao roomPriceDao) {
        this.roomPriceDao = roomPriceDao;
    }

    @Override
    public RoomPriceDTO findByTypeRoomIdAndTypePriceId(int typeRoomId, int typePriceId) {
        return toDTO(roomPriceDao.findByTypeRoomIdAndTypePriceId(typeRoomId,typePriceId));
    }
    public RoomPriceDTO toDTO(RoomPrice roomPrice){
        return new RoomPriceDTO(roomPrice.getTypeRoomId(),roomPrice.getTypePriceId(),roomPrice.getPrice());
    }
}
