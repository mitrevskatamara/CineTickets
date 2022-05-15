package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String image;
    private String writer;

    private String stars;

    private String trailerUrl;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Showing> showings;
}