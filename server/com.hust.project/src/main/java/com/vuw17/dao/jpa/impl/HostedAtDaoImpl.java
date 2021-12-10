package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.HostedAtDao;
import com.vuw17.entities.Guest;
import com.vuw17.entities.HostedAt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class HostedAtDaoImpl implements HostedAtDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(HostedAtDaoImpl.class.toString());

    @Override
    public List<HostedAt> findByOccupiedRoomId(int occupiedRoomId) {
        String sql = "SELECT * FROM hosted_at WHERE occupied_room_id = ?";
        try{
            return entityManager.createNativeQuery(sql,HostedAt.class).setParameter(1,occupiedRoomId).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<HostedAt> findByGuestId(int guestId) {
        String sql = "SELECT * FROM hosted_at WHERE guest_id = ?";
        try{
            return entityManager.createNativeQuery(sql,HostedAt.class).setParameter(1,guestId).getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
