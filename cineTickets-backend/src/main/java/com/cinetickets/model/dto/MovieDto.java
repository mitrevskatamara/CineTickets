package com.cinetickets.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovieDto   {
    private  String title;
    private  String country;
    private  double rating;
    private  String description;
    private  String Director;
    private  String Duration;
    private  String genre;
    private  boolean isShowing;
    private  String trailerURL;
    private  String year;
    private  List<Long> showings;

}
