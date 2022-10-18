package ru.kata.spring.boot_security.task3_1_2.service;


import ru.kata.spring.boot_security.task3_1_2.model.User;
import ru.kata.spring.boot_security.task3_1_2.security.UserDetails;

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
