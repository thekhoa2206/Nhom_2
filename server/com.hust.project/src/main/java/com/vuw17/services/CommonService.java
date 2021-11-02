package com.vuw17.services;

import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.entities.TableDiary;
import com.vuw17.entities.TypeAction;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    private final TableDiaryDAO tableDiaryDAO;
    private final TypeActionDAO typeActionDAO;
    private final TypeActionDao typeActionDao;
    private final TableDiaryDao tableDiaryDao;

    public CommonService(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao) {
        this.tableDiaryDAO = tableDiaryDAO;
        this.typeActionDAO = typeActionDAO;
        this.typeActionDao = typeActionDao;
        this.tableDiaryDao = tableDiaryDao;
    }

    public DiaryDTO checkDiary(String typeActionName, int newRowId, String tableName) {
        int typeActionId = checkTypeAction(typeActionName);
        int tableDiaryId = checkTableDiary(newRowId, tableName);
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setTableDiaryId(tableDiaryId);
        diaryDTO.setTypeActionId(typeActionId);
        diaryDTO.setNote(typeActionName + " " + tableName);
        return diaryDTO;

    }

    //Kiem tra type action ton tai,neu chua ton tai thi them moi
    public int checkTypeAction(String typeActionName) {
        TypeAction typeAction = typeActionDao.findByName(typeActionName);
//        System.out.println("Find By Name = " + typeAction.toString());
        int typeActionId = 0;
        if (typeAction != null) {
            typeActionId = typeAction.getId();
        } else {
            typeAction = new TypeAction();
            typeAction.setName(typeActionName);
            typeAction.setNote("");
            typeActionId = typeActionDAO.insertOne(typeAction);
        }
        return typeActionId;
    }

    //Kiem tra table diary ton tai rowId va table name,neu chua ton tai thi them moi
    public int checkTableDiary(int newRowId, String tableName) {
        TableDiary tableDiary = tableDiaryDao.findByRowIdAndTableName(newRowId, tableName);
        int tableDiaryId = 0;
        if (tableDiary != null) {
            tableDiaryId = tableDiary.getId();
        } else {
            tableDiaryId = tableDiaryDAO.insertOne(new TableDiary(newRowId, tableName));
        }
        return tableDiaryId;
    }
}
