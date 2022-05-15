package com.cinetickets.service.impl;

import com.cinetickets.model.Movie;
import com.cinetickets.model.dto.MovieDto;
import com.cinetickets.repository.MovieRepository;
import com.cinetickets.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie findById(Long id) {
       return this.movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie create(MovieDto movieDto) {
        return null;
    }

    @Override
    public Movie save(Movie movie) {
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie getByTitle(String title) {
        return this.movieRepository.findByTitle(title);
    }

    @Override
    public void delete(Long id) {
        Movie movie = this.findById(id);
        this.movieRepository.delete(movie);
    }
}
