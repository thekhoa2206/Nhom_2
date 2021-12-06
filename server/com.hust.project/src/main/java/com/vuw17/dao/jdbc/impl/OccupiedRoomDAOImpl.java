package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.OccupiedRoomDAO;
import com.vuw17.entities.OccupiedRoom;
import org.springframework.stereotype.Component;

@Component
public class OccupiedRoomDAOImpl extends BaseDAO<OccupiedRoom> implements OccupiedRoomDAO {
    @Override
    public int insertOne(OccupiedRoom occupiedRoom) {
        String sql = "INSERT INTO occupied_room(check_in_time,check_out_time,status,deposit,room_id) VALUES(?,?,?,?,?)";
        return insertOne(sql,occupiedRoom.getCheckInTime(),occupiedRoom.getCheckOutTime(), ConstantVariableCommon.STATUS_OCCUPIED_ROOM_1,occupiedRoom.getDeposit(),occupiedRoom.getRoomId());
    }
}
