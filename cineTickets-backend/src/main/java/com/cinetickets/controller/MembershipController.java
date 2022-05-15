package com.cinetickets.controller;

import com.cinetickets.model.Membership;
import com.cinetickets.model.User;
import com.cinetickets.service.MembershipService;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/membership")
public class MembershipController {
    private final MembershipService membershipService;
    private final UserService userService;

    @GetMapping("/memberships")
    public String getAll(Model model, Principal principal) {
        List<Membership> clubs = membershipService.lisAll();

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());

            model.addAttribute("user", user);
        }

        model.addAttribute("clubs", clubs);

        return "clubs/index";
    }

    @GetMapping("/membership/{id}/join")
    public String join(@PathVariable long id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(principal.getName());
        Membership membership = membershipService.findById(id);

        user.getMemberships().add(membership);
        userService.save(user);

        redirectAttributes.addFlashAttribute("isJoined", true);

        return "redirect:/clubs";
    }
}
