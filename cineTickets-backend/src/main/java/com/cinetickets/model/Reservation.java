package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {

    @Id
    private Long id;

    private LocalDateTime createdDate;

    private double totalPrice;

    private boolean isPayed;

    @ManyToOne
    private User user;

    //@ManyToOne
    //private Showing showing;

    @OneToMany
    private List<Ticket> tickets;
}