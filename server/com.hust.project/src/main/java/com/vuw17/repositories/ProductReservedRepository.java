package com.vuw17.repositories;

import com.vuw17.entities.Diary;
import com.vuw17.entities.ProductReserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReservedRepository extends JpaRepository<ProductReserved, Long>, JpaSpecificationExecutor<ProductReserved> {
}
