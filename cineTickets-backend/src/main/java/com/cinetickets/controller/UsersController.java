package com.cinetickets.controller;

import com.cinetickets.config.CustomUserDetails;
import com.cinetickets.model.Reservation;
import com.cinetickets.model.User;
import com.cinetickets.model.enumeration.UserRole;
import com.cinetickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;


    @GetMapping("/login")
    public String loginForm(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error) {
        model.addAttribute("user", new User());

        if (error) {
            model.addAttribute("error", "Username or password is incorrect");
        }

        return "users/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        user.setRole(UserRole.ROLE_USER);

        model.addAttribute("user", user);

        return "users/register";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView();

        User existingByUsername = userService.findByUsername(user.getUsername());
        if (existingByUsername != null) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        User existingByEmail = userService.findByEmail(user.getEmail());
        if (existingByEmail != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("users/register");
        }
        else {
            user.setRole(UserRole.ROLE_USER);

            userService.save(user);

            redirectAttributes.addFlashAttribute("isRegistered", true);

            model.setViewName("users/login");
        }

        return model;
    }

    @GetMapping("/admin/users")
    public String getAllAdmin(Model model)
    {
        List<User> users = userService.listAll();

        model.addAttribute("users", users);

        return "users/list";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/users";
    }

    @GetMapping("/profile/edit")
    public String edit(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);

        return "users/edit";
    }

    @PostMapping("/profile/update")
    public String update(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User existingByUsername = userService.findByUsername(user.getUsername());
        if (existingByUsername != null && existingByUsername.getId() != user.getId()) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        User existingByEmail = userService.findByEmail(user.getEmail());
        if (existingByEmail != null && existingByEmail.getId() != user.getId()) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        else {
            userService.save(user);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            userDetails.setUsername(user.getUsername());

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/profile/edit";
        }
    }



    @GetMapping("/admin/users/create")
    public String create(Model model) {
        model.addAttribute("user", new User());

        return "users/create";
    }

    @PostMapping("/admin/users/save")
    public String store(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User existingByUsername = userService.findByUsername(user.getUsername());
        if (existingByUsername != null) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        User existingByEmail = userService.findByEmail(user.getEmail());
        if (existingByEmail != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "users/create";
        }
        else {
            userService.save(user);

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/users";
        }
    }

}