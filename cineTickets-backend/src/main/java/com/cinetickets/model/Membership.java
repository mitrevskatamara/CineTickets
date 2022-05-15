package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String image;

    private String name;

    private int price;

    @ManyToMany(mappedBy = "memberships")
    private List<User> users;

    public Membership(String description, String image, String name, int price) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
    }
}