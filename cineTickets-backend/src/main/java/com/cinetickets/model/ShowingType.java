package com.cinetickets.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "showing_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowingType {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Name is required")
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Showing> showings;
}