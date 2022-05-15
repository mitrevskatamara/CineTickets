package com.cinetickets.service;

import com.cinetickets.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    List<User> getByRoles(String roles);
    List<User> getByClubs_Id(long id);
    Optional<User> getById(long id);
    Optional<User> getByUsername(String username);
    Optional<User> getByEmail(String email);
    User save(User user);
    User update(User user);
    void deleteById(long id);

}
