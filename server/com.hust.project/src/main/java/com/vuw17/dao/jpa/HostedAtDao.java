package com.vuw17.dao.jpa;

import com.vuw17.entities.HostedAt;

import java.util.List;

public interface HostedAtDao {
    //lay danh sach thong tin theo occupied_room_id
    List<HostedAt> findByOccupiedRoomId(int occupiedRoomId);
}
