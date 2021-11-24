package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.TypeRoomDAO;
import com.vuw17.entities.TypeRoom;
import org.springframework.stereotype.Component;

@Component
public class TypeRoomDAOImpl extends BaseDAO<TypeRoom> implements TypeRoomDAO {

    @Override
    public int insertOne(TypeRoom typeRoom) {
        String sql = "INSERT INTO type_room(name,note,number_children,number_adult,status) VALUES(?,?,?,?,?)";
        return insertOne(sql, typeRoom.getName(), typeRoom.getNote(),typeRoom.getNumberChildren(),typeRoom.getNumberAdult(), ConstantVariableCommon.STATUS_PRICE_1);
    }
}
