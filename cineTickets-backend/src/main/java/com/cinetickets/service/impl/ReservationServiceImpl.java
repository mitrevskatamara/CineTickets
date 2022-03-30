package com.cinetickets.service.impl;

import com.cinetickets.model.Reservation;
import com.cinetickets.model.dto.ReservationDto;
import com.cinetickets.repository.ReservationRepository;
import com.cinetickets.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> listAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation create(ReservationDto reservationDto) {
        return null;
    }

    @Override
    public Reservation update(Long id, ReservationDto reservationDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.findById(id);
        this.reservationRepository.delete(reservation);
    }
}