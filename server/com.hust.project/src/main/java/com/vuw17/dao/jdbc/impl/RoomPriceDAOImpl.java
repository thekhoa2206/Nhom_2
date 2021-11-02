package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.RoomPriceDAO;
import com.vuw17.entities.RoomPrice;
import org.springframework.stereotype.Component;

@Component
public class RoomPriceDAOImpl extends BaseDAO<RoomPrice> implements RoomPriceDAO {

    @Override
    public int insertOne(RoomPrice roomPrice) {
        String sql = "INSERT INTO room_price(type_room_id,type_price_id) VALUES(?,?)";
        return insertOne(sql,roomPrice.getTypeRoomId(),roomPrice.getTypePriceId());
    }
}
