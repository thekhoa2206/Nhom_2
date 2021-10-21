package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status extends BaseEntity {

    @Column(name = "status_name", nullable = true, length = 20)
    private String statusName;

    @Column(name = "table_name", nullable = true, length = 20)
    private String tableName;
}
