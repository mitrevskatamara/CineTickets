package com.cinetickets.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Role role;

    private String username;

    @OneToMany
    private List<Reservation> reservations;

    @ManyToMany
    private List<Membership> memberships;
}