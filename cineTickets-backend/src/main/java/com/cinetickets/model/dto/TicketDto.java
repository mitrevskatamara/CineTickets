package com.cinetickets.model.dto;

import lombok.Data;

@Data
public class TicketDto {

    private Long reservationId;

    private Long seatId;

    private Long showingId;
}