package com.vuw17.dao.jpa;

import com.vuw17.entities.OccupiedRoom;

import java.util.List;

public interface OccupiedRoomDao {
    //lay ra danh sach phong dang co khach
    List<OccupiedRoom> findOccupiedRooms();
    //Lay ra theo room id
    OccupiedRoom findByRoomId(int roomId);
}
