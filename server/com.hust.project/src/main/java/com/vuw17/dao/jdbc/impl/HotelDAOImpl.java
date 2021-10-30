package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.HotelDAO;
import com.vuw17.entities.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelDAOImpl extends BaseDAO<Hotel> implements HotelDAO {
    @Override
    public int insertOne(Hotel hotel) {
        String sql = "INSERT INTO hotel(name,address,note,phone_number,status) VALUES (?,?,?,?,?)";
        return insertOne(sql,hotel.getName(),hotel.getAddress(),hotel.getNote(),hotel.getPhoneNumber(), ConstantVariableCommon.STATUS_HOTEL_1);
    }
}
