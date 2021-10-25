package com.vuw17.services;

import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.entities.Diary;
import org.springframework.stereotype.Service;

@Service
public interface BaseService {
    //Hàm lưu nhật ký
    void saveDiary(DiaryDTO diaryDTO);
}
