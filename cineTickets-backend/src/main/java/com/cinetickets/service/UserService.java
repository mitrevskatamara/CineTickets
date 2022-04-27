package com.cinetickets.service;

import com.cinetickets.model.User;
import com.cinetickets.model.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> listAll();

    User register(User user);

    User create(UserDto userDto);

    User update(Long id, UserDto userDto);

    void delete(Long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
