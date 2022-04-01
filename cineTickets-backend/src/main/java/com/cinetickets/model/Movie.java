package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {

    @Id
    private Long id;

    private String title;

    private String country;

    private double rating;

    private String description;

    private String Director;

    private String Duration;

    private String genre;

    private boolean isShowing;

    private String trailerURL;

    private String year;


}
