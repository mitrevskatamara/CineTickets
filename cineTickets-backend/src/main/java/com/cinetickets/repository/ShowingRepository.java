package com.cinetickets.repository;

import com.cinetickets.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Long> {
    List<Showing> findByHall_Cinema_IdAndMovie_IdAndDateAndMovie_IsShowing(long cinemaId, long movieId, LocalDate date, boolean isShowing);
    List<Showing> findByMovie_IdAndAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAscTimeAsc(long movieId, boolean isShowing, LocalDate date);

    Showing findByHallIdAndDateAndTime(long hallId, LocalDate date, LocalTime time);
}

