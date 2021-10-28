package com.vuw17.dao.jpa;

import java.util.List;

public interface GenericDAO<T> {
    T getFirstRowData(List<T> list);
}
