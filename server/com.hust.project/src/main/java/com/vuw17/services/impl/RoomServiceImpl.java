package com.vuw17.services.impl;

import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.services.RoomService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }
}
