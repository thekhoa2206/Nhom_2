package com.vuw17.dao.jpa;

import com.vuw17.entities.ServiceUsed;

import java.util.List;

public interface ServiceUsedDao {
    //lay thong tin service_used qua occupied_room_id
    List<ServiceUsed> findServicesUsedByOccupiedRoomId(int occupiedRoomId);
    //Cap nhat quantity cua service used
    boolean update(ServiceUsed serviceUsed);
}
