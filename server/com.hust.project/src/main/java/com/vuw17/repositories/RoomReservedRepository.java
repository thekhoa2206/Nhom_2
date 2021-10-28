package com.vuw17.repositories;

import com.vuw17.entities.RoomReserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservedRepository extends JpaRepository<RoomReserved, Long>, JpaSpecificationExecutor<RoomReserved> {
}
