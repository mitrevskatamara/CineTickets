package com.cinetickets.service;

import com.cinetickets.model.User;
import com.cinetickets.model.dto.LoginDto;
import com.cinetickets.model.dto.ResponseDto;
import org.springframework.security.core.Authentication;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    ResponseDto register(User user);

    ResponseDto login(LoginDto loginDto);

    ResponseDto logout(HttpServletRequest request) throws ServletException;

    ResponseDto getCurrentUser(Authentication authentication);

}
