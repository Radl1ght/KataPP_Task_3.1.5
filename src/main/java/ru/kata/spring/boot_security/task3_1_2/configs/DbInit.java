package ru.kata.spring.boot_security.task3_1_2.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.task3_1_2.model.Role;
import ru.kata.spring.boot_security.task3_1_2.model.User;
import ru.kata.spring.boot_security.task3_1_2.repositories.RoleRepository;
import ru.kata.spring.boot_security.task3_1_2.service.UserService;

import javax.annotation.PostConstruct;


@Component
public class DbInit {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public DbInit(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void postConstruct() {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));
        User user = new User("user", "user", "user@mail.ru", "user", "user");
        userService.add(user);
        User admin = new User("admin", "admin", "admin@mail.ru", "admin", "admin");
        userService.add(admin);
        userService.makeAdmin(admin.getId(), admin);
        User radlight = new User("radlight", "radlight", "bm2000max@mail.ru", "Radlight", "77Uparem");
        userService.add(radlight);
        userService.makeAdmin(radlight.getId(), radlight);
    }
}
