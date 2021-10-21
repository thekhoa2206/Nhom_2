package com.vuw17.repositories;

import com.vuw17.entities.TableDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TableDiaryRepository  extends JpaRepository<TableDiary, Long>, JpaSpecificationExecutor<TableDiary> {
}
