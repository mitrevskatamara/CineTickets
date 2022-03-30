package com.cinetickets.service.impl;

import com.cinetickets.model.Cinema;
import com.cinetickets.model.dto.CinemaDto;
import com.cinetickets.repository.CinemaRepository;
import com.cinetickets.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public Cinema findById(Long id) {
        return this.cinemaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cinema> listAll() {
        return this.cinemaRepository.findAll();
    }

    @Override
    public Cinema create(CinemaDto cinemaDto) {
        return null;
    }

    @Override
    public Cinema update(Long id, CinemaDto cinemaDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Cinema cinema = this.findById(id);
        this.cinemaRepository.delete(cinema);
    }
}
