package com.vuw17.repositories;

import com.vuw17.entities.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long>, JpaSpecificationExecutor<RoomReservation> {
}
