package ru.kata.spring.boot_security.task3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring Security application");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/admin")
    public String usersPage() {
        return "admin";
    }


}
