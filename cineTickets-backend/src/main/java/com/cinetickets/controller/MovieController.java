package com.cinetickets.controller;

import com.cinetickets.model.Cinema;
import com.cinetickets.model.Movie;
import com.cinetickets.model.Showing;
import com.cinetickets.model.dto.MovieDto;
import com.cinetickets.model.dto.SearchDto;
import com.cinetickets.service.CinemaService;
import com.cinetickets.service.MovieService;
import com.cinetickets.service.ShowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class MovieController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/images";

    private final MovieService movieService;

    private final CinemaService cinemaService;

    private final ShowingService showingService;

    @GetMapping("/movies")
    public String getAll(Model model) {
        List<Movie> movies = movieService.listAll();

        model.addAttribute("movies", movies);

        return "movies/index";
    }

    @GetMapping("/admin/movies")
    public String getAllAdmin(Model model)
    {
        List<Movie> movies = movieService.listAll();

        model.addAttribute("movies", movies);

        return "movies/list";
    }

    @GetMapping("/movies/{id}")
    public String get(Model model, @PathVariable Long id) {
        Movie movie = movieService.findById(id);
        List<Cinema> cinemas = cinemaService.listAll();
        SearchDto search = new SearchDto();
        search.setMovieId(id);

        model.addAttribute("movie", movie);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("search", search);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "movies/details";
    }

    @GetMapping("/admin/movies/create")
    public String create(Model model) {
        model.addAttribute("movieDto", new MovieDto());

        return "movies/create";
    }

    @PostMapping("/admin/movies/save")
    public String store(MovieDto movieDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Movie existingMovie = movieService.getByTitle(movieDto.getTitle());

        if (existingMovie != null) {
            bindingResult.rejectValue("title", "movie.title", "Title already exists");
        }

        if (bindingResult.hasErrors()) {
            return "movies/create";
        }
        else {
            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setCountry(movieDto.getCountry());
            movie.setDescription(movieDto.getDescription());
            movie.setDirector(movieDto.getDirector());
            movie.setDuration(movieDto.getDuration());
            movie.setGenre(movieDto.getGenre());
            movie.setRating(movieDto.getRating());
            movie.setTrailerURL(movieDto.getTrailerURL());
            movie.setDirector(movieDto.getDirector());
            movie.setYear(movieDto.getYear());
            movie.setShowing(true);

            try {
                String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/images";
                Path path = Paths.get(uploadDirectory, movieDto.getImage().getOriginalFilename());
                byte[] bytes = movieDto.getImage().getBytes();
                Files.write(path, bytes);
                String shortUrl = "/uploads/images/" +movieDto.getImage().getOriginalFilename();

                movie.setImage(shortUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            movieService.save(movie);

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/movies";
        }
    }

    @GetMapping("/admin/movies/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Movie movie = movieService.findById(id);

        model.addAttribute("movie", movie);

        return "movies/edit";
    }

    @PostMapping("/admin/movies/update")
    public String update(Movie movie, @RequestParam MultipartFile imageFile, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Movie existingMovie = movieService.getByTitle(movie.getTitle());

        if (existingMovie != null && existingMovie.getId() != movie.getId()) {
            bindingResult.rejectValue("title", "movie.title", "Title already exists");
        }

        if (bindingResult.hasErrors()) {
            return "movies/edit";
        }
        else {

            if (!imageFile.isEmpty()) {
                try {
                    String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/images";
                    Path path = Paths.get(uploadDirectory, imageFile.getOriginalFilename());
                    byte[] bytes = imageFile.getBytes();
                    Files.write(path, bytes);
                    String shortUrl = "/uploads/images/" +imageFile.getOriginalFilename();

                    movie.setImage(shortUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            movie.setShowing(true);
            movieService.update(movie);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/movies/edit/" +movie.getId();
        }
    }

    @GetMapping("/admin/movies/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        movieService.delete(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/movies";
    }

    @PostMapping("/movies/showings/search")
    public String search(SearchDto search, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/movies/" +search.getMovieId()  +"#here";
        }

        List<Showing> showings = showingService.getByCinemaIdAndMovieIdAndDateAndMovieIsShowing(search.getCinemaId(), search.getMovieId(), LocalDate.parse(search.getDate()));

        if (showings.size() == 0) {
            redirectAttributes.addFlashAttribute("noShowings", showings);
        }

        redirectAttributes.addFlashAttribute("showings", showings);

        return "redirect:/movies/" +search.getMovieId() +"#here";
    }

    @GetMapping("/movies/{id}/showings")
    public String allShowings(@PathVariable long id, Model model) {
        List<Showing> showings = showingService.getByMovie_IdAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAsc(id, LocalDate.now());
        Movie movie = movieService.findById(id);

        if (showings.isEmpty()) {
            model.addAttribute("noShowings", true);
        }

        model.addAttribute("showings", showings);
        model.addAttribute("movie", movie);

        return "movies/showings";
    }
}