package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.GenericDAO;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.Hotel;
import com.vuw17.entities.TypeRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TypeRoomDaoImpl implements TypeRoomDao, GenericDAO<TypeRoom> {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());

    @Override
    public void insertOne(TypeRoom typeRoom) {
        String sql = "INSERT INTO type_room(name,note,number_children,number_adult,status) VALUES (?,?,?,?,?)";
        entityManager.createNativeQuery(sql).setParameter(1,typeRoom.getName()).setParameter(2,typeRoom.getNote())
                .setParameter(3,typeRoom.getNumberChildren()).setParameter(4,typeRoom.getNumberAdult())
                .setParameter(5, ConstantVariableCommon.STATUS_TYPE_ROOM_1).executeUpdate();
    }

    @Override
    public List<TypeRoom> findAll() {
        String sql = "SELECT * FROM type_room";
        return entityManager.createNativeQuery(sql,TypeRoom.class).getResultList();
    }

    @Override
    public void updateOne(TypeRoom typeRoom) {
        String sql = "UPDATE type_room SET name = ?,note = ?,number_children = ?,number_adult = ?,status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,typeRoom.getName()).setParameter(2,typeRoom.getNote())
                .setParameter(3,typeRoom.getNumberChildren()).setParameter(4,typeRoom.getNumberAdult())
                .setParameter(5, typeRoom.getStatus()).setParameter(6,typeRoom.getId()).executeUpdate();

    }

    @Override
    public void deleteOne(int id) {
        String sql = "UPDATE type_room SET status = ? WHERE id = ?";
        entityManager.createNativeQuery(sql).setParameter(1,ConstantVariableCommon.STATUS_TYPE_ROOM_3).setParameter(2,id).executeUpdate();
    }

    @Override
    public TypeRoom findById(int id) {
        String sql = "SELECT * FROM type_room WHERE id = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,TypeRoom.class).setParameter(1,id).getResultList());
    }

    @Override
    public TypeRoom findByName(String name) {
        String sql = "SELECT * FROM type_room WHERE name = ?";
        return getFirstRowData(entityManager.createNativeQuery(sql,TypeRoom.class).setParameter(1,name).getResultList());
    }

    @Override
    public TypeRoom getFirstRowData(List<TypeRoom> list) {
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
