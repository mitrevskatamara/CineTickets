package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Membership {

    @Id
    private Long id;

    private String description;

    private String image;

    private String name;

    private int price;

    @ManyToMany
    private List<User> users;

    public Membership(String description, String image, String name, int price, List<User> users) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.users = users;
    }
}