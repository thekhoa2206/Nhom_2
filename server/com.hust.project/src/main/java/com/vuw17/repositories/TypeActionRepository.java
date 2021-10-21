package com.vuw17.repositories;

import com.vuw17.entities.TypeAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeActionRepository extends JpaRepository<TypeAction, Long>, JpaSpecificationExecutor<TypeAction> {
}
