package com.cinetickets.service.impl;

import com.cinetickets.model.Ticket;
import com.cinetickets.model.dto.TicketDto;
import com.cinetickets.repository.TicketRepository;
import com.cinetickets.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Ticket findById(Long id) {
        return this.ticketRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> listAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket create(TicketDto ticketDto) {
        return null;
    }

    @Override
    public Ticket update(Long id, TicketDto ticketDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = this.findById(id);
        this.ticketRepository.delete(ticket);
    }
}
