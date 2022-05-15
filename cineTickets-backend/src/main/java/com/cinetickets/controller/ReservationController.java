package com.cinetickets.controller;

import com.cinetickets.model.*;
import com.cinetickets.model.dto.SeatDto;
import com.cinetickets.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ReservationController {
    private ReservationService reservationService;
    private TicketService ticketService;
    private ShowingService showingService;
    private SeatService seatService;
    private MembershipService membershipService;


    @GetMapping("/admin/reservations")
    public String getAllAdmin(Model model)
    {
        List<Reservation> reservations = reservationService.listAll();

        model.addAttribute("reservations", reservations);

        return "reservations/list";
    }

    @GetMapping("/showings/{id}/reservations/create")
    public String create(@PathVariable long id, Model model) {
        Reservation reservation = new Reservation();
        reservation.setCreatedDate(LocalDate.now());
        reservation.setCreatedTime(LocalTime.now());
        reservation.setTotalPrice(10);

        Showing showing = showingService.findById(id).get();

        List<Seat> seats = seatService.getByHall_IdOrderByNumberAsc(showing.getHall().getId());
        List<SeatDto> seatsDto = new ArrayList<SeatDto>();

        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto();
            seatDto.setId(seat.getId());
            seatDto.setNumber(seat.getNumber());
            seatDto.setRow(seat.getRow());
            seatDto.setReserved(false);

            seatsDto.add(seatDto);
        }

        List<Ticket> tickets = ticketService.getByShowing_Id(showing.getId());

        for (Ticket rs : tickets) {
            for (SeatDto seat : seatsDto) {
                if (seat.getId() == rs.getSeat().getId()) {
                    seat.setReserved(true);
                }
            }
        }

        model.addAttribute("reservation", reservation);
        model.addAttribute("showing", showing);
        model.addAttribute("seats", seatsDto);

        return "reservations/create";
    }

    @PostMapping("/reservations/save")
    public String store(@Valid Reservation reservation, @RequestParam String showingId, @RequestParam(required = false) List<String> seats,
                        Principal principal, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reservations/create";
        }
        else {
            if (seats == null) {
                redirectAttributes.addFlashAttribute("seatsError", true);

                return "redirect:/showings/" +showingId +"/reservations/create";
            }

            if (seats.size() > 6) {
                redirectAttributes.addFlashAttribute("maxSeats", true);

                return "redirect:/showings/" +showingId +"/reservations/create";
            }

            reservation.setCreatedDate(LocalDate.now());
            reservation.setCreatedTime(LocalTime.now());
            reservation.setPayed(false);

            User user = userService.getByUsername(principal.getName());
            reservation.setUser(user);

            Showing showing = showingService.getById(Long.parseLong(showingId)).get();
            reservation.setShowing(showing);

            Membership membership = membershipService.findById(1);
            if (user.getMemberships().contains(membership)) {
                reservation.setTotalPrice(seats.size() * showing.getPrice() * 0.8f);
            } else {
                reservation.setTotalPrice(seats.size() * showing.getPrice());
            }

            Reservation newReservation = reservationService.save(reservation);

            for (String seatIdString : seats) {
                long seatId = Long.parseLong(seatIdString);
                Seat seat = seatService.findById(seatId);

                Ticket ticket = new Ticket();
                ticket.setReservation(newReservation);
                ticket.setSeat(seat);
                ticket.setShowing(showing);

                ticketService.save(ticket);
            }

            redirectAttributes.addFlashAttribute("isCreated", true);

            if (user.getRole().contains("ROLE_ADMIN")) {
                return "redirect:/admin/reservations";
            } else {
                return "redirect:/profile/reservations";
            }
        }
    }

    @GetMapping("/admin/reservations/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        reservationService.delete(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/reservations";
    }

    @GetMapping("/admin/reservations/{id}/tickets")
    public String tickets(@PathVariable long id, Model model)
    {
        Reservation reservation = reservationService.getById(id);
        Set<Ticket> tickets = reservation.getTickets();

        model.addAttribute("reservation", reservation);
        model.addAttribute("tickets", tickets);

        return "reservations/tickets";
    }

    @GetMapping("/admin/reservations/{id}/tickets/delete/{ticketId}")
    public String deleteTicket(@PathVariable long id, @PathVariable long ticketId, RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.findById(id);
        float oldPrice = reservation.getTotalPrice();
        float newPrice = oldPrice - (oldPrice / reservation.getTickets().size());
        reservation.setTotalPrice(newPrice);

        Ticket ticket = ticketService.findById(ticketId);
        reservation.getTickets().remove(ticket);

        reservationService.save(reservation);
        ticketService.delete(ticketId);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/reservations/" +id +"/tickets";
    }

    @GetMapping("/admin/reservations/{id}/buy")
    public String buy(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.findById(id);

        reservation.setPayed(true);
        reservationService.save(reservation);

        redirectAttributes.addFlashAttribute("isBought", true);

        return "redirect:/admin/reservations";
    }
}
