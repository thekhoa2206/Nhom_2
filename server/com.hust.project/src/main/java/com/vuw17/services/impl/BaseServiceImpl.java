package com.vuw17.services.impl;

import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.entities.Diary;
import com.vuw17.repositories.DiaryRepository;
import com.vuw17.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class BaseServiceImpl implements BaseService {

    private final DiaryRepository diaryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class.toString());

    public BaseServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    //Hàm lưu nhật ký
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveDiary(DiaryDTO diaryDTO){
        Diary diary = new Diary();
        diary.setActionBy(diaryDTO.getUserId());
        Date date = new Date();
        diary.setActionDate(date.getTime());
        diary.setTypeActionId(diaryDTO.getTypeActionId());
        diary.setTableDiaryId(diaryDTO.getTableDiaryId());
        diary.setNote(diaryDTO.getNote());
        saveDiary(diary);
    }

    //Hàm lưu nhật ký bằng repository
    public void saveDiary(Diary diary){
        try {
            diaryRepository.save(diary);
        }catch (Exception e){
            LOGGER.error("ERROR || Lưu nhật kí lỗi", e.getMessage());
        }
    }
}
