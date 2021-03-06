package com.vuw17.repositories;

import com.vuw17.entities.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long>, JpaSpecificationExecutor<TypeRoom> {
}
