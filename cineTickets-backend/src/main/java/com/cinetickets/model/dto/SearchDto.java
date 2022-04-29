package com.cinetickets.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchDto {
    Long cinemaId;

    Long movieId;

    String date;
}
