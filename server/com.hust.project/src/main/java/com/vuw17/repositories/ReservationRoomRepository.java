package com.vuw17.repositories;

import com.vuw17.entities.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long > {
}
