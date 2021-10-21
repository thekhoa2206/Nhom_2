package com.vuw17.repositories;

import com.vuw17.entities.TypePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePriceRepository  extends JpaRepository<TypePrice, Long>, JpaSpecificationExecutor<TypePrice> {
}
