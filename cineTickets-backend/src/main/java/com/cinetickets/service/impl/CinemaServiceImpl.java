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
        Cinema cinema = new Cinema(cinemaDto.getAddress(), cinemaDto.getImage(), cinemaDto.getName(),
                cinemaDto.getCity(), cinemaDto.getPhoneNumber());
        return this.cinemaRepository.save(cinema);
    }

    @Override
    public Cinema update(Long id, CinemaDto cinemaDto) {
        Cinema cinema = this.findById(id);

        cinema.setAddress(cinemaDto.getAddress());
        cinema.setImage(cinemaDto.getImage());
        cinema.setName(cinemaDto.getName());
        cinema.setCity(cinemaDto.getCity());
        cinema.setPhoneNumber(cinemaDto.getPhoneNumber());

        return this.cinemaRepository.save(cinema);
    }

    @Override
    public void delete(Long id) {
        Cinema cinema = this.findById(id);
        this.cinemaRepository.delete(cinema);
    }
}