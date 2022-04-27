package com.cinetickets.model.dto;

import com.cinetickets.model.Role;
import com.cinetickets.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String repeatPassword;

    private UserRole role;

    private String username;

    private List<Long> reservationsId;

    private List<Long> membershipsId;

    public UserDto(User user){
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();

    }
}