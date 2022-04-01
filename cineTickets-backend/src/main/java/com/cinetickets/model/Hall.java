package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Hall {

    @Id
    private Long id;

    private String name;

    private int rows;

    private int screenSize;

    private int seatsInRow;

    private int numberOfSeats;

    //cinema ID
}
