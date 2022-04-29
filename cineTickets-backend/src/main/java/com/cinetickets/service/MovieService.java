package com.cinetickets.service;

import com.cinetickets.model.Movie;
import com.cinetickets.model.dto.MovieDto;

import java.util.List;

public interface MovieService {

    Movie findById(Long id);

    List<Movie> listAll();

    Movie create(MovieDto movieDto);

    Movie update(Movie movie);

    void delete(Long id);

    Movie getByTitle(String title);

    Movie save(Movie movie);
}
