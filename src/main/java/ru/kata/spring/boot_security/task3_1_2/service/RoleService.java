package ru.kata.spring.boot_security.task3_1_2.service;

import ru.kata.spring.boot_security.task3_1_2.model.Role;
import ru.kata.spring.boot_security.task3_1_2.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleById(long id);
    Set <Role> getRolesSet (List<Long> rolesIdList);
    List<Role> getAllRoles ();
}
