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

    Hall update(HallDto hallDto );

    void delete(Long id);

}
