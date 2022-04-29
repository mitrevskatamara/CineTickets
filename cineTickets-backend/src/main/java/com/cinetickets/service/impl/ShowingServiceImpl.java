package com.cinetickets.service.impl;

import com.cinetickets.model.Showing;
import com.cinetickets.model.dto.ShowingDto;
import com.cinetickets.repository.ShowingRepository;
import com.cinetickets.service.ShowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowingServiceImpl implements ShowingService {
    private final ShowingRepository showingRepository;
    @Override
    public Showing findById(Long id) {
        return null;
    }

    @Override
    public List<Showing> listAll() {
        return null;
    }

    @Override
    public Showing create(ShowingDto showingDto) {
        return null;
    }

    @Override
    public Showing update(ShowingDto showingDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Showing> getByCinemaIdAndMovieIdAndDateAndMovieIsShowing(long cinemaId, long movieId, LocalDate date) {
        return showingRepository.findByHall_Cinema_IdAndMovie_IdAndDateAndMovie_IsShowing(cinemaId, movieId, date, true);
    }

    @Override
    public List<Showing> getByMovie_IdAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAsc(long movieId, LocalDate date) {
        return showingRepository.findByMovie_IdAndAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAscTimeAsc(movieId, true, date);
    }

}
