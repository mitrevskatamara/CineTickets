package com.cinetickets.service;

import com.cinetickets.model.Reservation;
import com.cinetickets.model.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    Reservation findById(Long id);

    List<Reservation> listAll();

    Reservation create(ReservationDto reservationDto);

    Reservation update(Long id, ReservationDto reservationDto);

    void delete(Long id);

    List<Reservation> getByShowingId(Long id);
}