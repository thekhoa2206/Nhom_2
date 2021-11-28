package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type_room")
@Getter
@Setter
public class TypeRoom extends BaseEntity {
    @Column(name = "name", nullable = true, length = 30)
    private String name;


    @Column(name = "max_adult", nullable = true)
    private int maxAdult;

    @Column(name = "max_child", nullable = true)
    private int maxChild;

    @Column(name = "status", nullable = true)
    private int status;

}
