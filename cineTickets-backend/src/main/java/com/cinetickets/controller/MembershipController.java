package com.cinetickets.controller;

import com.cinetickets.model.Membership;
import com.cinetickets.model.User;
import com.cinetickets.service.MembershipService;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    private final UserService userService;

    @GetMapping("/memberships")
    public String getAll(Model model, Principal principal) {
        List<Membership> memberships = membershipService.lisAll();

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());

            model.addAttribute("user", user);
        }

        model.addAttribute("memberships", memberships);

        return "memberships/index";
    }

    @GetMapping("/memberships/{id}/join")
    public String join(@PathVariable long id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(principal.getName());
        Membership membership = membershipService.findById(id);

        user.getMemberships().add(membership);
        userService.save(user);

        redirectAttributes.addFlashAttribute("isJoined", true);

        return "redirect:/clubs";
    }
}
