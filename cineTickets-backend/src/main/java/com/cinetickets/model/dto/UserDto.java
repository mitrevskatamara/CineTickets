package com.cinetickets.model.dto;

import com.cinetickets.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Role role;

    private String username;

    private List<Long> reservationsId;

    private List<Long> membershipsId;
}