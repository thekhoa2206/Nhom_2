package com.vuw17.dao.jpa;

import com.vuw17.entities.TableDiary;

public interface TableDiaryDao {

    //Lay thong tin cua table diary qua rowid va table name
    TableDiary findByRowIdAndTableName(int rowId,String tableName);
}
