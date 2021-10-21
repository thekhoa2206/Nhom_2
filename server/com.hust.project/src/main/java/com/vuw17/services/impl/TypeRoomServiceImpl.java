package com.vuw17.services.impl;

import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.services.TypeRoomService;
import org.springframework.stereotype.Service;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    private final TypeRoomDao typeRoomDao;

    public TypeRoomServiceImpl(TypeRoomDao typeRoomDao) {
        this.typeRoomDao = typeRoomDao;
    }
}
