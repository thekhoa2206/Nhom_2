package com.vuw17.dao.jpa;

import com.vuw17.entities.RoomPrice;

public interface RoomPriceDao {
    boolean updateOne(RoomPrice roomPrice);

    RoomPrice findByTypeRoomId(int typeRoomId);
    RoomPrice findByTypeRoomIdAndTypePriceId(int typeRoomId, int typePriceId);
}
