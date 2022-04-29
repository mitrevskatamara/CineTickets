package com.cinetickets.controller;

import com.cinetickets.model.*;
import com.cinetickets.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final MovieService movieService;

    private final CinemaService cinemaService;

    @GetMapping("/")
    public String home(Model model) {
        List<Movie> movies = movieService.listAll();
        List<Cinema> cinemas = cinemaService.listAll();

        model.addAttribute("movies", movies.subList(0, 2));
        model.addAttribute("cinemas", cinemas.subList(0, 3));

        return "home/index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        return "home/admin";
    }
}