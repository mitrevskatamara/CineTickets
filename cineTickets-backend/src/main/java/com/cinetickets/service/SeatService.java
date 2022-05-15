package com.cinetickets.service;

import com.cinetickets.model.Seat;
import com.cinetickets.model.dto.SeatDto;

import java.util.List;

public interface SeatService {
    Seat findById(Long id);

    List<Seat> listAll();

    Seat create(SeatDto seatDto);

    Seat save(Seat seat);

    void delete(Long id);

    List<Seat> getByHall_IdOrderByNumberAsc(Long id);
}
