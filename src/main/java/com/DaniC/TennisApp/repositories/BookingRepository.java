package com.DaniC.TennisApp.repositories;

import com.DaniC.TennisApp.entities.Booking;
import com.DaniC.TennisApp.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {



    boolean existsByCourtAndEndBookGreaterThanEqualAndStartBookLessThanEqual(Court court, LocalDateTime startBook, LocalDateTime endBook);
}
