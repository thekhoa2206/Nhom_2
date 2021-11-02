package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.entities.TableDiary;
import org.springframework.stereotype.Component;

@Component
public class TableDiaryDAOImpl extends BaseDAO<TableDiary> implements TableDiaryDAO {
    @Override
    public int insertOne(TableDiary tableDiary) {
        String sql = "INSERT INTO table_diary(row_id,table_name) VALUES(?,?)";
        return insertOne(sql, tableDiary.getRowId(), tableDiary.getTableName());
    }
}
