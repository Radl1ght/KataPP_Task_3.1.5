package ru.kata.spring.boot_security.task3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.task3_1_2.security.UserDetails;
import ru.kata.spring.boot_security.task3_1_2.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
