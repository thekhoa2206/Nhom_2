package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.HostedAtDAO;
import com.vuw17.entities.HostedAt;
import org.springframework.stereotype.Component;

@Component
public class HostedAtDAOImpl extends BaseDAO<HostedAt> implements HostedAtDAO {
    @Override
    public int insertOne(HostedAt hostedAt) {
        String sql = "INSERT INTO hosted_at(occupied_room_id,guest_id) VALUES (?,?)";
        return insertOne(sql,hostedAt.getOccupiedRoomId(),hostedAt.getGuestId());
    }
}
