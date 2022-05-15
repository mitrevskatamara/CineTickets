package com.cinetickets.service;

import com.cinetickets.model.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findById(Long id);

    List<Reservation> listAll();


    List<Reservation> getByIsPayed(boolean isPayed);

    Reservation save(Reservation reservation);

    List<Reservation> getByUser_UsernameOrderByShowing_DateDesc(String username);

    Reservation update(Reservation reservation);

    void delete(Long id);

    List<Reservation> getByShowingId(Long id);
}