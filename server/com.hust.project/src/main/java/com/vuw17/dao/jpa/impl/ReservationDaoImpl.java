package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.ReservationDao;
import com.vuw17.entities.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ReservationDaoImpl implements ReservationDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDaoImpl.class.toString());

    @Override
    public List<Reservation> findReservationByParam(String keyword, int status){
        String sql = "SELECT * FROM reservation WHERE 1 = 1 ";

        if(keyword != null && keyword.length() !=0 ){
            sql = sql + " AND  reservation.note LIKE CONCAT('%',LCASE(:keyword),'%') ";
        }
        if(status > 0) {
            sql = sql + " AND reservation.status = :status";
        }
        Query query = entityManager.createNativeQuery(sql, Reservation.class);
        if(keyword != null && keyword.length() !=0 ){
            query.setParameter("keyword", keyword);
        }
        if(status > 0){
            query.setParameter("status", status);
        }
        List<Reservation> reservations = query.getResultList();
        return reservations;
    }
    //    //hàm tìm reservation bằng id
    @Override
    public Reservation findReservationById(int id){
        String sql = "SELECT * FROM reservation WHERE id = ?";
        try{
            return (Reservation) entityManager.createNativeQuery(sql, Reservation.class).setParameter(1, id).getSingleResult();
        }catch (Exception e){
            return new Reservation();
        }
    }


}
