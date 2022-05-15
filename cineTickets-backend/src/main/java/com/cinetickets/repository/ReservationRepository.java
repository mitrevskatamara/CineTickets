package com.cinetickets.repository;

import com.cinetickets.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByShowingId(Long id);


    List<Reservation> findByIsPayed(boolean isPayed);

    List<Reservation> findByUser_UsernameOrderByShowing_DateDesc(String username);
}
