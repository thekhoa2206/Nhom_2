package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.ServiceUsedDAO;
import com.vuw17.entities.ServiceUsed;
import org.springframework.stereotype.Component;

@Component
public class ServiceUsedDAOImpl extends BaseDAO<ServiceUsed> implements ServiceUsedDAO {
    @Override
    public int insertOne(ServiceUsed serviceUsed) {
        String sql = "INSERT INTO service_used(occupied_room_id,service_id,quantity,paid) VALUES(?,?,?,?)";
        return insertOne(sql,serviceUsed.getOccupiedRoomId(),serviceUsed.getServiceId(),serviceUsed.getQuantity(),serviceUsed.isPaid());
    }
}
