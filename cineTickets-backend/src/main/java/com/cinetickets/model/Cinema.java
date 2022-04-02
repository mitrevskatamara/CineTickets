package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String image;

    private String name;

    private String city;

    private String phoneNumber;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<Hall> halls;

    public Cinema(String address, String image, String name, String city, String phoneNumber) {
        this.address = address;
        this.image = image;
        this.name = name;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}