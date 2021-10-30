package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.TypeRoomDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.TypeRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TypeRoomDaoImpl implements TypeRoomDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());

    @Override
    public List<TypeRoom> findAll() {
        String sql = "SELECT * FROM type_room";
        return entityManager.createNativeQuery(sql,TypeRoom.class).getResultList();
    }

    @Override
    public boolean updateOne(TypeRoom typeRoom) {
        String sql = "UPDATE type_room SET name = ?,note = ?,number_children = ?,number_adult = ?,status = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1, typeRoom.getName()).setParameter(2, typeRoom.getNote())
                    .setParameter(3, typeRoom.getNumberChildren()).setParameter(4, typeRoom.getNumberAdult())
                    .setParameter(5, typeRoom.getStatus()).setParameter(6, typeRoom.getId()).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deleteOne(int id) {
        String sql = "UPDATE type_room SET status = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1, ConstantVariableCommon.STATUS_TYPE_ROOM_3).setParameter(2, id).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TypeRoom findById(int id) {
        String sql = "SELECT * FROM type_room WHERE id = ?";
        try{
            return (TypeRoom) entityManager.createNativeQuery(sql,TypeRoom.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public TypeRoom findByName(String name) {
        String sql = "SELECT * FROM type_room WHERE name = ?";
        try{
            return (TypeRoom) entityManager.createNativeQuery(sql,TypeRoom.class).setParameter(1,name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
