package com.cinetickets.service.impl;

import com.cinetickets.model.User;
import com.cinetickets.model.dto.UserDto;
import com.cinetickets.repository.UserRepository;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        return null;
    }

    @Override
    public User update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        User user = this.findById(id);
        this.userRepository.delete(user);
    }
}
