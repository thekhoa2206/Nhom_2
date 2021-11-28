package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.RoomDAO;
import com.vuw17.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomDAOImpl extends BaseDAO<Room> implements RoomDAO {
    @Override
    public int insertOne(Room room) {
        String sql = "INSERT INTO room(name,type_room_id,status) VALUES (?,?,?)";
        return insertOne(sql,room.getName(),room.getTypeRoomId(), ConstantVariableCommon.STATUS_ROOM_1);
    }
}
