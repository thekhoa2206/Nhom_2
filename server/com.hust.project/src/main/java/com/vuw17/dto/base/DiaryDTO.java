package com.vuw17.dto.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiaryDTO {
    private String note;
    private int userId;
    private int tableId;
    private int rowId;
    private int actionId;
}
