package com.cinetickets.controller;

import com.cinetickets.model.User;
import com.cinetickets.model.dto.LoginDto;
import com.cinetickets.model.dto.ResponseDto;
import com.cinetickets.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseDto register(@RequestBody User user) {
        return this.authService.register(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginDto loginDto) {
        return this.authService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) throws ServletException {
        return this.authService.logout(request);
    }

    @GetMapping("/current-user")
    public ResponseDto getCurrentUser(Authentication authentication) {
        return this.authService.getCurrentUser(authentication);
    }
}
