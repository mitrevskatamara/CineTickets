package com.cinetickets.controller;

import com.cinetickets.model.*;
import com.cinetickets.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/cinemas")
    public String getAll(Model model) {
        List<Cinema> cinemas = cinemaService.listAll();

        model.addAttribute("cinemas", cinemas);

        return "cinemas/index";
    }

    @GetMapping("/cinemas/{id}")
    public String get(Model model, @PathVariable Long id) {
        Cinema cinema = cinemaService.findById(id);

        model.addAttribute("cinema", cinema);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "cinemas/details";
    }


    @GetMapping("/cinemas/{id}/halls")
    public String halls(@PathVariable long id, Model model) {
        Cinema cinema = cinemaService.findById(id);
        List<Hall> halls = cinema.getHalls();

        if (halls.isEmpty()) {
            model.addAttribute("noHalls", true);
        }

        model.addAttribute("halls", halls);
        model.addAttribute("cinema", cinema);

        return "cinemas/halls";
    }
}
