package com.cinetickets.service;

import com.cinetickets.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket findById(Long id);

    List<Ticket> listAll();

    Ticket create(TicketDto ticketDto);

    Ticket update(Long id, TicketDto ticketDto);

    void delete(Long id);
}