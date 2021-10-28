package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diary")
@Getter
@Setter
public class Diary extends BaseEntity {
    @Column(name = "type_action_id", nullable = false)
    private int typeActionId;

    @Column(name = "table_diary_id", nullable = false)
    private int tableDiaryId;

    @Column(name = "note", nullable = true, length = 254)
    private String note;

    @Column(name = "action_date", nullable = true)
    private long actionDate;

    @Column(name = "action_by", nullable = true)
    private int actionBy;

}
