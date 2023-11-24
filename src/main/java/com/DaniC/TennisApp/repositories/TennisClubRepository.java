package com.DaniC.TennisApp.repositories;

import com.DaniC.TennisApp.entities.TennisClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisClubRepository extends JpaRepository<TennisClub,Integer> {
}
