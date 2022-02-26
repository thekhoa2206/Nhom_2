package com.vuw17.repositories;

import com.vuw17.entities.HostedAt;
import com.vuw17.entities.OccupiedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HostedAtRepository extends JpaRepository<HostedAt, Long>, JpaSpecificationExecutor<HostedAt> {
}
