package ru.kata.spring.boot_security.task3_1_2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.task3_1_2.model.Role;
import ru.kata.spring.boot_security.task3_1_2.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
@Service
public class RoleServiceRepImp implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceRepImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Role> getRolesSet(List<Long> rolesIdList) {
        Set<Role> roleSet = new HashSet<>();
        for (Long e : rolesIdList) {
            roleSet.add(getRoleById(e));
        }
        return roleSet;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
