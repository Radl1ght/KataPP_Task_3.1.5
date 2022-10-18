package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
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
    public String usersPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("usersList", userService.listUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
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
