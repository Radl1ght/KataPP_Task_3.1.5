package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetails;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void update(long id, User updatedUser);

    void delete(long id);

    User getUserById(long id);

    void makeAdmin(long id, User updatedUser);

    UserDetails loadUserByUsername(String username);
}
