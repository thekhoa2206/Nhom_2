package com.vuw17.dto.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class DiaryDTO {
    public DiaryDTO(){

    }
    public DiaryDTO(int typeActionId, int tableDiaryId, String note, int userId) {
        this.typeActionId = typeActionId;
        this.tableDiaryId = tableDiaryId;
        this.note = note;
        this.userId = userId;
    }

    private int typeActionId;

    private int tableDiaryId;

    private String note;

    private int userId;
}
