package com.cinetickets.model.dto;

import com.cinetickets.model.Hall;
import lombok.Data;

import java.util.List;

@Data
public class SeatDto   {
    private long id;
    private int row;
    private int number;
    private Hall hall;
    private boolean isReserved;
}
