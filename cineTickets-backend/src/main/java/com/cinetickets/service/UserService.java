package com.cinetickets.service;

import com.cinetickets.model.Membership;
import com.cinetickets.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> listAll();

    User register(User user);

    User findByUsername(String username);

    void delete(Long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User save(User user);

}
