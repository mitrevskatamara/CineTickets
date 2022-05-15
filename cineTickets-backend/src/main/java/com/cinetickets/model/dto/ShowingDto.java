package com.cinetickets.model.dto;

import com.cinetickets.model.Hall;
import com.cinetickets.model.Movie;
import com.cinetickets.model.ShowingType;
import com.cinetickets.model.dto.SeatDto;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ShowingDto {
    private long id;

    @NotNull(message = "Date is required")
    private String date;

    @NotNull(message = "Time is required")
    private String time;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    @Max(value = 10000, message = "Price must be less than 10001")
    private float price;

    private Movie movie;

    private Hall hall;

    private ShowingType type;
}
