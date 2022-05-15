package com.cinetickets.service.impl;

import com.cinetickets.model.Hall;
import com.cinetickets.repository.HallRepository;
import com.cinetickets.service.HallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    @Override
    public Hall findById(Long id) {
        return this.hallRepository.findById(id).orElse(null);
    }

    @Override
    public Hall getByUsername(String username) {
        return this.hallRepository.findByName(username);
    }

    @Override
    public Hall save(Hall hall) {
        return this.hallRepository.save(hall);
    }

    @Override
    public List<Hall> listAll() {
        return this.hallRepository.findAll();
    }

    @Override
    public Hall create(HallDto hallDto) {
        return null;
    }

    @Override
    public Hall update(Hall hall) {
        Hall persistedHall = this.hallRepository.save(hall);

        return persistedHall;
    }

    @Override
    public void delete(Long id) {
        Hall hall = this.findById(id);
        this.hallRepository.delete(hall);
    }
}
