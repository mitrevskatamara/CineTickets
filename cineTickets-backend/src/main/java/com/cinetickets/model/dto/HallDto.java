package com.cinetickets.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallDto  {
    private  Long id;
    private  String name;
    private  int rows;
    private  int screenSize;
    private  int seatsInRow;
    private  int numberOfSeats;
}
