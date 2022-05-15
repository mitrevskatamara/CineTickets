package com.cinetickets.controller;

import com.cinetickets.model.*;
import com.cinetickets.model.dto.SearchDto;
import com.cinetickets.model.dto.ShowingDto;
import com.cinetickets.service.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/showings")
public class ShowingController {

    private ShowingService showingService;

    private CinemaService cinemaService;

    private MovieService movieService;

    private HallService hallService;

   // private ShowingTypeService showingTypeService;

    private ReservationService reservationService;

    @GetMapping("/showings")
    public String getAll(Model model) {
        List<Cinema> cinemas = cinemaService.listAll();
        List<Movie> movies = movieService.listAll();

        model.addAttribute("search", new SearchDto());
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("movies", movies);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "showings/index";
    }

    @PostMapping("/showings/search")
    public String search(SearchDto search, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "showings/index";
        }

        List<Showing> showings = showingService.getByCinemaIdAndMovieIdAndDateAndMovieIsShowing(search.getCinemaId(), search.getMovieId(), LocalDate.parse(search.getDate()));

        if (showings.size() == 0) {
            redirectAttributes.addFlashAttribute("noShowings", showings);
        }

        redirectAttributes.addFlashAttribute("showings", showings);

        return "redirect:/showings";
    }

    @GetMapping("/admin/showings")
    public String getAllAdmin(Model model)
    {
        List<Showing> showings = showingService.listAll();

        model.addAttribute("showings", showings);

        return "showings/list";
    }

    @GetMapping("/showings/{id}")
    public String get(Model model, @PathVariable Long id) {
        Showing showing = showingService.findById(id);

        model.addAttribute("showing", showing);

        return "showings/details";
    }

    @GetMapping("/admin/showings/create")
    public String create(Model model) {
        List<Movie> movies = movieService.listAll();
        List<Hall> halls = hallService.listAll();
       // List<ShowingType> types = showingTypeService.getAll();

        model.addAttribute("showingDto", new ShowingDto());
        model.addAttribute("movies", movies);
        model.addAttribute("halls", halls);
      //  model.addAttribute("types", types);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "showings/create";
    }

    @PostMapping("/admin/showings/save")
    public String store(@Valid ShowingDto showingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        LocalDate date = LocalDate.parse(showingDto.getDate());
        LocalTime time = LocalTime.parse(showingDto.getTime());

        Showing existingShowing = showingService.getByHallIdAndDateAndTime(showingDto.getHall().get(), date, time);

        if (existingShowing != null) {
            List<Movie> movies = movieService.listAll();
            List<Hall> halls = hallService.listAll();
            //List<ShowingType> types = showingTypeService.getAll();

            model.addAttribute("movies", movies);
            model.addAttribute("halls", halls);
            //model.addAttribute("types", types);

            bindingResult.rejectValue("time", "time.occupied", "Hall is occupied for selected time. Try another one.");
        }

        if (bindingResult.hasErrors()) {
            return "showings/create";
        }
        else {
            Showing showing = new Showing();
            showing.setHall((Hall) showingDto.getHall());
            showing.setMovie(showingDto.getMovie());
            //showing.setType(showingDto.getType());
            showing.setPrice(showingDto.getPrice());
            showing.setDate(date);
            showing.setTime(time);

            showingService.save(showing);

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/showings";
        }
    }

    @GetMapping("/admin/showings/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Showing showing = showingService.findById(id);
        ShowingDto showingDto = new ShowingDto();
        showingDto.setId(showing.getId());
        showingDto.setHall(showing.getHall());
        showingDto.setMovie(showing.getMovie());
       // showingDto.setType(showing.getType());
        showingDto.setDate(showing.getDate().toString());
        showingDto.setTime(showing.getTime().toString());
        showingDto.setPrice(showing.getPrice());

        List<Movie> movies = movieService.listAll();
        List<Hall> halls = hallService.listAll();
        //List<ShowingType> types = showingTypeService.getAll();

        model.addAttribute("showingDto", showingDto);
        model.addAttribute("movies", movies);
        model.addAttribute("halls", halls);
       // model.addAttribute("types", types);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "showings/edit";
    }

    @PostMapping("/admin/showings/update")
    public String update(@Valid ShowingDto showingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        LocalDate date = LocalDate.parse(showingDto.getDate());
        LocalTime time = LocalTime.parse(showingDto.getTime());

        Showing existingShowing = showingService.getByHallIdAndDateAndTime(showingDto.getHall().getId(), date, time);

        if (existingShowing != null && existingShowing.getId() != showingDto.getId()) {
            List<Movie> movies = movieService.listAll();
            List<Hall> halls = hallService.listAll();
           // List<ShowingType> types = showingTypeService.getAll();

            model.addAttribute("movies", movies);
            model.addAttribute("halls", halls);
       //     model.addAttribute("types", types);

            bindingResult.rejectValue("time", "time.occupied", "Hall is occupied for selected time. Try another one.");
        }

        if (bindingResult.hasErrors()) {
            return "showings/edit";
        }
        else {
            Showing showing = new Showing();
            showing.setId(showingDto.getId());
            showing.setHall(showingDto.getHall());
            showing.setMovie(showingDto.getMovie());
          //  showing.setType(showingDto.getType());
            showing.setPrice(showingDto.getPrice());
            showing.setDate(date);
            showing.setTime(time);

            showingService.update(showing);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/showings/edit/" +showing.getId();
        }
    }

    @GetMapping("/admin/showings/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        showingService.delete(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/showings";
    }

    @GetMapping("/admin/showings/{id}/reservations")
    public String reservations(@PathVariable long id, Model model) {
        List<Reservation> reservations = reservationService.getByShowingId(id);
        Showing showing = showingService.findById(id);

        if (reservations.isEmpty()) {
            model.addAttribute("noReservations", true);
        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("showing", showing);

        return "showings/reservations";
    }
}
