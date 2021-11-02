package com.vuw17.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "table_diary")
@Getter
@Setter
@AllArgsConstructor
public class TableDiary extends BaseEntity {
    public TableDiary() {
    }

    @Column(name = "row_id", nullable = true)
    private int rowId;

    @Column(name = "table_name", nullable = true, length = 20)
    private String tableName;
}
