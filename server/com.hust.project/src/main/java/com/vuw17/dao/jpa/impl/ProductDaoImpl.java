package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.ProductDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ProductDaoImpl implements ProductDao {
}
