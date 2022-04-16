package com.cinetickets.service;

import com.cinetickets.model.Seat;
import com.cinetickets.model.dto.SeatDto;

public interface SeatService {
    Seat findById(Long id);

    Seat create(SeatDto seatDto);

    Seat update(SeatDto seatDto);

    void delete(Long id);
}
