package com.cinetickets.service;

import com.cinetickets.model.Ticket;
import com.cinetickets.model.dto.TicketDto;

import java.util.List;

public interface TicketService {

    Ticket findById(Long id);

    List<Ticket> listAll();

    Ticket create(TicketDto ticketDto);

    Ticket update(Long id, TicketDto ticketDto);

    void delete(Long id);
}