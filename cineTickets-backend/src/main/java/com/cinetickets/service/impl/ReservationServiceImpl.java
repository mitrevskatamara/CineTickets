package com.cinetickets.service.impl;

import com.cinetickets.model.Reservation;
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
    public List<Reservation> getByIsPayed(boolean isPayed) {
        return reservationRepository.findByIsPayed(isPayed);    }



    @Override
    public List<Reservation> getByUser_UsernameOrderByShowing_DateDesc(String username) {
        return reservationRepository.findByUser_UsernameOrderByShowing_DateDesc(username);    }



    @Override
    public Reservation save(Reservation reservation) {
        Reservation persistedReservation = reservationRepository.save(reservation);

        return persistedReservation;    }

    @Override
    public Reservation update(Reservation reservation) {
        Reservation persistedReservation = reservationRepository.save(reservation);

        return persistedReservation;    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.findById(id);
        this.reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getByShowingId(Long id) {
        return reservationRepository.findByShowingId(id);
    }
}