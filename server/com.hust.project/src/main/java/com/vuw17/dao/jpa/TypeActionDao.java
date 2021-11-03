package com.vuw17.dao.jpa;

import com.vuw17.entities.TypeAction;

public interface TypeActionDao {
    //Get 1 type action by name
    TypeAction findByName(String name);
}
