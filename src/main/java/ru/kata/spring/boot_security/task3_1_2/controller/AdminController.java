package ru.kata.spring.boot_security.task3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.task3_1_2.model.Role;
import ru.kata.spring.boot_security.task3_1_2.model.User;
import ru.kata.spring.boot_security.task3_1_2.repositories.RoleRepository;
import ru.kata.spring.boot_security.task3_1_2.security.UserDetails;
import ru.kata.spring.boot_security.task3_1_2.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring Security application");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/admin")
    public String usersPage(Model model, @ModelAttribute("user") User user, Principal principal) {
        UserDetails userDetails = userService.loadUserByUsername(principal.getName());
        model.addAttribute("principal", userDetails.getUser());
        model.addAttribute("usersList", userService.listUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "role") Long role) {
        System.out.println(role);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findById(role).orElse(null));
        user.setRoles(roleSet);
        userService.add(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("usersList", userService.listUsers());
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }
}
