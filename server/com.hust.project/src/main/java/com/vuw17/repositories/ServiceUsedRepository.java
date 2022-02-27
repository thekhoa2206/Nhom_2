package com.vuw17.repositories;

import com.vuw17.entities.RoomReservation;
import com.vuw17.entities.ServiceUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUsedRepository extends JpaRepository<ServiceUsed, Long>, JpaSpecificationExecutor<ServiceUsed> {
}
