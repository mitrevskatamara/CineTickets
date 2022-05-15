package com.cinetickets.service.impl;

import com.cinetickets.model.User;
import com.cinetickets.repository.UserRepository;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByRoles(String roles) {
        return userRepository.findByRoles(roles);
    }

    @Override
    public List<User> getByClubs_Id(long id) {
        return userRepository.findByClubs_Id(id);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        User persistedUser = userRepository.save(user);

        return persistedUser;
    }

    @Override
    public User update(User user) {
        User persistedUser = userRepository.save(user);

        return persistedUser;
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }




}
