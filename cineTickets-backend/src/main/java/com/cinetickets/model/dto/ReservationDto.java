package com.cinetickets.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDto {

    private LocalDateTime createdDate;

    private double totalPrice;

    private boolean isPayed;

    private Long showingId;

    private Long userId;

    private List<Long> ticketsId;
}