package com.cinetickets.service.impl;

import com.cinetickets.model.Showing;
import com.cinetickets.model.dto.ShowingDto;
import com.cinetickets.service.ShowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShowingServiceImpl implements ShowingService {
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
}
