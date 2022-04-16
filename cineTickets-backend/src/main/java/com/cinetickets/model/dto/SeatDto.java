package com.cinetickets.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatDto   {
    private  int seatNumber;
    private  int row;
    private List<Long> hall;
    private  List<Long> tickets;

}
