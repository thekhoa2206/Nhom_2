package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.entities.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestDAOImpl extends BaseDAO<Guest> implements GuestDAO {
    @Override
    public int insertOne(Guest guest) {
        String sql = "INSERT INTO guest(first_name,last_name,birthday,nationality,address,phone_number,email,id_card,status) VALUES(?,?,?,?,?,?,?,?,?)";
        int a =  insertOne(sql,guest.getFirstName(),guest.getLastName(),guest.getBirthday(),guest.getNationality(),guest.getAddress()
                ,guest.getPhoneNumber(),guest.getEmail(),guest.getIdCard(), ConstantVariableCommon.STATUS_GUEST_1);
        return a;
    }
}
