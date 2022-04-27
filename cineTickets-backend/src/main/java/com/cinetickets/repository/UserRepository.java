package com.cinetickets.repository;

import com.cinetickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User getByUsername(String username);
    Optional<User> findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
