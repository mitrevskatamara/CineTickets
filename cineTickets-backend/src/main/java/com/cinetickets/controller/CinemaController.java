package com.cinetickets.controller;

import com.cinetickets.model.Cinema;
import com.cinetickets.model.Hall;
import com.cinetickets.model.dto.CinemaDto;
import com.cinetickets.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<Cinema>> listAll() {
        List<Cinema> cinemas = this.cinemaService.listAll();
        return new ResponseEntity(cinemas, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Cinema> create(@RequestBody CinemaDto cinemaDto) {
        Cinema cinema = this.cinemaService.create(cinemaDto);
        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }
}
