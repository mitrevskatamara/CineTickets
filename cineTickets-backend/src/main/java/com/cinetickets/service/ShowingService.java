package com.cinetickets.service;

import com.cinetickets.model.Seat;
import com.cinetickets.model.Showing;
import com.cinetickets.model.dto.SeatDto;
import com.cinetickets.model.dto.ShowingDto;

import java.util.List;

public interface ShowingService {
    Showing findById(Long id);

    List<Showing> listAll();

    Showing create(ShowingDto showingDto);

    Showing update(ShowingDto showingDto);

    void delete(Long id);

}
