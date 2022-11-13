package ru.kata.spring.boot_security.task3_1_2.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.task3_1_2.model.Role;
import ru.kata.spring.boot_security.task3_1_2.model.User;
import ru.kata.spring.boot_security.task3_1_2.security.UserDetails;
import ru.kata.spring.boot_security.task3_1_2.service.RoleService;
import ru.kata.spring.boot_security.task3_1_2.service.UserService;

import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final UserService userService;
    private final RoleService roleService;


    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/api/principal")
    public ResponseEntity<User> usersPage(Principal principal) {
        UserDetails userDetails = userService.loadUserByUsername(principal.getName());
        return new ResponseEntity<>(userDetails.getUser(), HttpStatus.OK);
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.listUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/api/allRoles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping(value = "/api/user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.update(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/api/newUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
