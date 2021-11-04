package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.TableDiary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class TableDiaryDaoImpl implements TableDiaryDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.toString());

    @Override
    public TableDiary findByRowIdAndTableName(int rowId, String tableName) {
        String sql = "SELECT * FROM table_diary WHERE row_id = ? AND table_name = ?";
        try {
            return (TableDiary) entityManager.createNativeQuery(sql,TableDiary.class).setParameter(1,rowId).setParameter(2,tableName).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
