package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.GuestDAO;
import com.vuw17.entities.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestDAOImpl extends BaseDAO<Guest> implements GuestDAO {
    @Override
    public int insertOne(Guest guest) {
        String sql = "INSERT INTO guest(first_name,last_name,phone_number) VALUES(?,?,?)";
        return insertOne(sql,guest.getFirstName(),guest.getLastName(),guest.getPhoneNumber());
    }
}
