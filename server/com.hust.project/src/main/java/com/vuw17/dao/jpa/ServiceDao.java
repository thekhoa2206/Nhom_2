package com.vuw17.dao.jpa;

import com.vuw17.entities.Service;

import java.util.List;

public interface ServiceDao {
    //Lay thong tin cua service qua id
    Service findById(int id);
    //Lay thong tin cua service qua name
    Service findByName(String name);
    //lay danh sach service
    List<Service> findAll();
}
