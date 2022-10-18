package ru.kata.spring.boot_security.task3_1_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.task3_1_2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
