package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ticket {

    @Id
    private Long id;

    @ManyToOne
    private Reservation reservation;

    // private Seat seat;

    // private Showing showing;
}