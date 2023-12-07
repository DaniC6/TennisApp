package com.DaniC.TennisApp.repositories;

import com.DaniC.TennisApp.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court,Integer> {
    boolean existsByCourtName(String courtName);
}
