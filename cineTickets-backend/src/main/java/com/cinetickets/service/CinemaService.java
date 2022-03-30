package com.cinetickets.service;

import com.cinetickets.model.Cinema;
import com.cinetickets.model.dto.CinemaDto;

import java.util.List;

public interface CinemaService {

    Cinema findById(Long id);

    List<Cinema> listAll();

    Cinema create(CinemaDto cinemaDto);

    Cinema update(Long id, CinemaDto cinemaDto);

    void delete(Long id);
}