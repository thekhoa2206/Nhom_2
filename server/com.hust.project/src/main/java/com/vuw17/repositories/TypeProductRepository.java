package com.vuw17.repositories;

import com.vuw17.entities.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProduct, Long>, JpaSpecificationExecutor<TypeProduct> {
}
