package com.cinetickets.controller;

import com.cinetickets.model.Cinema;
import com.cinetickets.model.Hall;
import com.cinetickets.model.Seat;
import com.cinetickets.service.CinemaService;
import com.cinetickets.service.HallService;
import com.cinetickets.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class HallController {

    private final HallService hallService;

    private final CinemaService cinemaService;

    private final SeatService seatService;

    @GetMapping("/admin/halls")
    public String getAllAdmin(Model model)
    {
        List<Hall> halls = hallService.listAll();

        model.addAttribute("halls", halls);

        return "halls/list";
    }

    @GetMapping("/admin/halls/create")
    public String create(Model model) {
        List<Cinema> cinemas = cinemaService.listAll();
        Hall hall = new Hall();
        hall.setRows(10);
        hall.setSeatsInRow(10);

        model.addAttribute("hall", hall);
        model.addAttribute("cinemas", cinemas);

        return "halls/create";
    }

    @PostMapping("/admin/halls/save")
    public String store(Hall hall, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Hall existingHall = hallService.getByUsername(hall.getName());

        if (existingHall != null) {
            List<Cinema> cinemas = cinemaService.listAll();

            model.addAttribute("cinemas", cinemas);

            bindingResult.rejectValue("name", "hall.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "halls/create";
        }
        else {
            int seats = hall.getNumberOfSeats();
            int seatsInRow = seats > 120 ? 20 : 10;
            int rows = seats % seatsInRow == 0 ? seats / seatsInRow : seats / seatsInRow + 1;

            hall.setSeatsInRow(seatsInRow);
            hall.setRows(rows);
            Hall newHall = hallService.save(hall);

            for (int i = 1; i <= seats; i++) {
                Seat seat = new Seat();
                seat.setHall(newHall);
                seat.setSeatNumber(i);
                seat.setRow((i + seatsInRow - 1) / seatsInRow);

                seatService.save(seat);
            }

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/halls";
        }
    }

    @GetMapping("/admin/halls/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Hall hall = hallService.findById(id);
        List<Cinema> cinemas = cinemaService.listAll();

        model.addAttribute("hall", hall);
        model.addAttribute("cinemas", cinemas);

        return "halls/edit";
    }

    @PostMapping("/admin/halls/update")
    public String update(Hall hall, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Hall existingHall = hallService.getByUsername(hall.getName());

        if (existingHall != null && existingHall.getId() != hall.getId()) {
            List<Cinema> cinemas = cinemaService.listAll();

            model.addAttribute("cinemas", cinemas);

            bindingResult.rejectValue("name", "hall.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "halls/edit";
        }
        else {
            int seats = hall.getNumberOfSeats();
            int seatsInRow = seats > 120 ? 20 : 10;
            int rows = seats % seatsInRow == 0 ? seats / seatsInRow : seats / seatsInRow + 1;

            hall.setSeatsInRow(seatsInRow);
            hall.setRows(rows);

            hallService.update(hall);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/halls/edit/" +hall.getId();
        }
    }

    @GetMapping("/admin/halls/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        hallService.delete(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/halls";
    }
}