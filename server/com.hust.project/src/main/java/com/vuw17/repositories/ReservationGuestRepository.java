package com.vuw17.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationGuestRepository extends JpaRepository<ReservationGuest, Long>, JpaSpecificationExecutor<ReservationGuest> {
}
