package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity {
    @Column(name = "name" , nullable = true, length = 30)
    private String name;

    @Column(name = "note" , nullable = true, length = 254)
    private String note;

    @Column(name = "status" , nullable = true)
    private int status;

    @Column(name = "quantity" , nullable = true)
    private int quantity;

    @Column(name = "selling_price" , nullable = true)
    private BigDecimal sellingPrice;

    @Column(name = "import_price" , nullable = true)
    private BigDecimal importPrice;

    @Column(name = "unit_id" , nullable = false)
    private int unitId;

    @Column(name = "type_product_id" , nullable = false)
    private int typeProductId;
}
