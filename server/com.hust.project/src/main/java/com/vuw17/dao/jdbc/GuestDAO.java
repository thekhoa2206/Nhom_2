package com.vuw17.dao.jdbc;

import com.vuw17.entities.Guest;
import com.vuw17.entities.Hotel;

public interface GuestDAO {
    int insertOne(Guest guest);
}
