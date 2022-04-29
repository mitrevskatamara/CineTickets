package com.cinetickets.service;

import com.cinetickets.model.Hall;
import com.cinetickets.model.Movie;
import com.cinetickets.model.dto.HallDto;
import com.cinetickets.model.dto.MovieDto;

import java.util.List;

public interface HallService {
    Hall findById(Long id);

    List<Hall> listAll();

    Hall create(HallDto hallDto);

    Hall update(Hall hall);

    void delete(Long id);

    Hall getByUsername(String username);

    Hall save(Hall hall);
}
