package com.vuw17.dao.jpa.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.UnitDao;
import com.vuw17.entities.TypeProduct;
import com.vuw17.entities.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UnitDaoImpl implements UnitDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitDaoImpl.class.toString());


    @Override
    public Unit findUnitById(int id){
        String sql = "SELECT * FROM unit WHERE 1 = 1 ";
        if(id > 0){
            sql = sql + " AND unit.id = :id";
        }
        Query query = entityManager.createNativeQuery(sql, Unit.class);
        if(id > 0){
            query.setParameter("id", id);
        }
        Unit unit = (Unit) query.getSingleResult();
        return unit;
    }

    @Override
    public List<Unit> findAllUnitByStatus() {
        String sql = "SELECT * FROM unit WHERE 1 = 1 AND unit.status ="+ ConstantVariableCommon.STATUS_UNIT_1;
        Query query = entityManager.createNativeQuery(sql, Unit.class);
        List<Unit> units = query.getResultList();
        return units;
    }

    @Override
    public Unit findUnitByName(String name){
        String sql = "SELECT * FROM unit WHERE unit.name = ?1";
        Query query = entityManager.createNativeQuery(sql, Unit.class);
        query.setParameter(1, name);
        Unit unit = (Unit) query.getSingleResult();
        return unit;
    }
}
