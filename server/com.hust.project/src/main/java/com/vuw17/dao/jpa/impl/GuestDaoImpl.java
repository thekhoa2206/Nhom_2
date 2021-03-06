package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.entities.Guest;
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
public class GuestDaoImpl implements GuestDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(GuestDaoImpl.class.toString());
    //Lay tat ca cac guests theo room id,kiem tra check in time <= thoi gian hien tai <= check out time
    @Override
    public List<Guest> findByRoomId(int roomId) {

        return null;
    }

    @Override
    public Guest findById(int id) {
        String sql = "SELECT * FROM guest WHERE id = ?";
        try{
            return (Guest) entityManager.createNativeQuery(sql,Guest.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Guest findByIdCard(String idCard) {
        String sql = "SELECT * FROM guest WHERE status = 1 AND id_card = ?";
        try{
            return (Guest) entityManager.createNativeQuery(sql,Guest.class).setParameter(1,idCard).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Guest findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM guest WHERE status = 1 AND phone_number = ?";
        try{
            return (Guest) entityManager.createNativeQuery(sql,Guest.class).setParameter(1,phoneNumber).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Guest> findAll() {
        String sql = "SELECT * FROM guest WHERE status = 1";
        try{
            return entityManager.createNativeQuery(sql,Guest.class).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Guest> findByKeyword(String keyword) {
        String sql = "SELECT * FROM guest WHERE status = 1 AND phone_number LIKE '%"+keyword+"%' OR "+"id_card LIKE '%"+keyword+"%'";
        try{
            return entityManager.createNativeQuery(sql,Guest.class).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean update(Guest guest) {
        String sql = "UPDATE guest SET first_name = ?,last_name = ?,birthday = ?,nationality = ?,address = ?,phone_number = ?,email = ?,id_card = ? WHERE id = ?";
        try{
            entityManager.createNativeQuery(sql).setParameter(1,guest.getFirstName()).setParameter(2,guest.getLastName()).setParameter(3,guest.getBirthday()).setParameter(4,guest.getNationality()).setParameter(5,guest.getAddress()).setParameter(6,guest.getPhoneNumber()).setParameter(7,guest.getEmail()).setParameter(8,guest.getIdCard()).setParameter(9,guest.getId()).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "UPDATE guest SET status = 2 WHERE id = ?";
        try{
            entityManager.createNativeQuery(sql).setParameter(1,id).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
