package com.vuw17.dao.jpa;

import com.vuw17.entities.Hotel;
import com.vuw17.entities.TypeRoom;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface TypeRoomDao {
    //Them moi 1 TypeRoom
//    void insertOne(TypeRoom typeRoom);
    //Lay tat ca cac TypeRooms
    List<TypeRoom> findAll();
    //Sua thong tin cua TypeRoom
    void updateOne(TypeRoom typeRoom);
    //Xoa TypeRoom
    void deleteOne(int id);
    //Lay TypeRoom theo id
    TypeRoom findById(int id);
    //Lay TypeRoom theo name
    TypeRoom findByName(String name);
}
