package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.ServiceUsedDao;
import com.vuw17.entities.ServiceUsed;
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
public class ServiceUsedDaoImpl implements ServiceUsedDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUsedDaoImpl.class.toString());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ServiceUsed> findServicesUsedByOccupiedRoomId(int occupiedRoomId) {
        String sql = "SELECT * FROM service_used WHERE occupied_room_id = ?";
        try {
            return entityManager.createNativeQuery(sql, ServiceUsed.class).setParameter(1, occupiedRoomId).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(ServiceUsed serviceUsed) {
        String sql = "UPDATE service_used SET occupied_room_id = ?,service_id = ?,quantity = ?,paid = ? WHERE id = ?";
        try {
            entityManager.createNativeQuery(sql).setParameter(1, serviceUsed.getOccupiedRoomId()).setParameter(2, serviceUsed.getServiceId()).setParameter(3, serviceUsed.getQuantity()).setParameter(4, serviceUsed.isPaid()).setParameter(5, serviceUsed.getId()).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
