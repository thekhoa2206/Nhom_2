package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.GuestDao;
import com.vuw17.entities.Guest;
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
}
