package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Cinema {

    @Id
    private Long id;

    private String address;

    private String image;

    private String name;

    private String city;

    private String phoneNumber;

    // private List<Hall> halls;
}