package com.vuw17.dto.tablediary;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableDiaryDTO extends BaseDTO {
    private int rowId;

    private String tableName;
}
