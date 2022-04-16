package com.cinetickets.model.dto;

import com.cinetickets.model.dto.SeatDto;
import lombok.Data;

import java.util.List;

@Data
public class ShowingDto   {
    private  String date;
    private  String price;
    private  String time;
    private List<Long> movie;
    private  List<Long> hall;
    private  List<Long> reservations;
    private  List<Long> tickets;
}
