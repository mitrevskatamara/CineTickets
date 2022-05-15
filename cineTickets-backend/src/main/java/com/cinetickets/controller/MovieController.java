package com.cinetickets.controller;

import com.cinetickets.model.Movie;
import com.cinetickets.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> listAll(){
        List<Movie> movies = this.movieService.listAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}
