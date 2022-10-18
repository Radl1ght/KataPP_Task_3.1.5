package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.UserDetails;

import java.util.*;

@Transactional(readOnly = true)
@Service
public class UserServiceRepImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceRepImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void add(User user) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void update(long id, User updatedUser) {
        User userOnUpdate = userRepository.findById(id).orElse(null);
        updatedUser.setId(id);
        assert userOnUpdate != null;
        updatedUser.setRoles(userOnUpdate.getRoles());
        if (updatedUser.getPassword() == null || updatedUser.getPassword().equals("")) {
            updatedUser.setPassword(userOnUpdate.getPassword());
        } else {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(updatedUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void makeAdmin(long id, User adminCandidate) {
        Set<Role> set = adminCandidate.getRoles();
        set.add(new Role(2L, "ROLE_ADMIN"));
        adminCandidate.setRoles(set);
        userRepository.save(adminCandidate);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ru.kata.spring.boot_security.demo.security.UserDetails(user.get());

    }
}
