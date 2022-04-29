package com.cinetickets.service.impl;

import com.cinetickets.model.Seat;
import com.cinetickets.model.dto.SeatDto;
import com.cinetickets.repository.SeatRepository;
import com.cinetickets.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Override
    public Seat findById(Long id) {
        return this.seatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seat> listAll() {
        return this.seatRepository.findAll();
    }

    @Override
    public Seat create(SeatDto seatDto) {
        return null;
    }

    @Override
    public Seat save(Seat seat) {
        return this.seatRepository.save(seat);
    }

    @Override
    public void delete(Long id) {
        Seat seat = this.findById(id);
        this.seatRepository.delete(seat);
    }
}
