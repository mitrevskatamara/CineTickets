package com.cinetickets.service.impl;

import com.cinetickets.model.User;
import com.cinetickets.model.dto.UserDto;
import com.cinetickets.model.exception.UserNotFoundException;
import com.cinetickets.repository.UserRepository;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User register(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User create(UserDto userDto) {
        /*
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User(userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
                encryptedPassword, userDto.getRole(), userDto.getUsername());
        return this.userRepository.save(user);

         */
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

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->new UserNotFoundException(email));
    }

}
