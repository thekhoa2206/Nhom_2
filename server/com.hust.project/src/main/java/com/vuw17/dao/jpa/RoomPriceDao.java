package com.vuw17.dao.jpa;

import com.vuw17.entities.RoomPrice;

import java.util.List;

public interface RoomPriceDao {
    boolean updateOne(RoomPrice roomPrice);

    List<RoomPrice> findByTypeRoomId(int typeRoomId);
    RoomPrice findByTypeRoomIdAndTypePriceId(int typeRoomId, int typePriceId);
}
