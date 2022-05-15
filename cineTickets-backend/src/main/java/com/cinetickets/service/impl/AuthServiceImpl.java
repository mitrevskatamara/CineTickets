package com.cinetickets.service.impl;

import com.cinetickets.model.User;
import com.cinetickets.model.enumeration.UserRole;
import com.cinetickets.service.AuthService;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseDto register(User user) {
        String email = user.getEmail();
        ResponseDto response = new ResponseDto();

        if (this.userService.existsByEmail(email)) {
            response.setStatusCode(401);
            response.setMessage(String.format("The user with the provided email (%s) already exists!", email));
            response.setUser(null);
        } else {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRole.ROLE_USER);
            user = this.userService.register(user);

            response.setStatusCode(200);
            response.setMessage("Successful registration!");
            response.setUser(new UserDto(user));
        }

        return response;
    }

    @Override
    public ResponseDto login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        ResponseDto response = new ResponseDto();

        if (!this.userService.existsByEmail(email)) {
            response.setStatusCode(401);
            response.setMessage(String.format("The user with the provided email (%s) does not exist!", email));
            response.setUser(null);
        } else {
            User user = this.userService.findByEmail(email);
            String password = loginDto.getPassword();

            if (!this.passwordEncoder.matches(password, user.getPassword())) {
                response.setStatusCode(401);
                response.setMessage("Passwords do not match!");
                response.setUser(null);
            } else {
                Authentication authentication = this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password));
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                response.setStatusCode(200);
                response.setMessage("Successful registration!");
                response.setUser(new UserDto(user));
            }
        }

        return response;
    }

    @Override
    public ResponseDto logout(HttpServletRequest request) throws ServletException {
        ResponseDto response = new ResponseDto();

        request.logout();

        response.setStatusCode(200);
        response.setMessage("Successful logging out!");
        response.setUser(null);

        return response;
    }

    @Override
    public ResponseDto getCurrentUser(Authentication authentication) {
        ResponseDto response = new ResponseDto();
        if (authentication == null) {
            response.setStatusCode(404);
            response.setMessage("There are no currently signed in users!");
            response.setUser(null);
        } else {
            String userEmail = (String) authentication.getPrincipal();
            User user = this.userService.findByEmail(userEmail);

            response.setStatusCode(200);
            response.setMessage("User found!");
            response.setUser(new UserDto(user));
        }

        return response;
    }

}
