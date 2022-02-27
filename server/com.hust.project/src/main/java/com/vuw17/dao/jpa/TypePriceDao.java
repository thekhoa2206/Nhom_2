package com.vuw17.dao.jpa;

import com.vuw17.entities.TypePrice;
import com.vuw17.entities.TypeRoom;

public interface TypePriceDao {
    //Lay TypePrice theo name
    TypePrice findByName(String name);
    //Lay TypePrice theo id
    TypePrice findById(int id);

}
