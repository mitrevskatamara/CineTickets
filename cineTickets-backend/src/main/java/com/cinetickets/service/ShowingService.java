package com.cinetickets.service;

import com.cinetickets.model.Seat;
import com.cinetickets.model.Showing;
import com.cinetickets.model.dto.SeatDto;
import com.cinetickets.model.dto.ShowingDto;

import java.time.LocalDate;
import java.util.List;

public interface ShowingService {
    Showing findById(Long id);

    List<Showing> listAll();

    Showing create(ShowingDto showingDto);

    Showing update(ShowingDto showingDto);

    void delete(Long id);

    List<Showing> getByCinemaIdAndMovieIdAndDateAndMovieIsShowing(long cinemaId, long movieId, LocalDate date);

    List<Showing> getByMovie_IdAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAsc(long movieId, LocalDate date);
}
