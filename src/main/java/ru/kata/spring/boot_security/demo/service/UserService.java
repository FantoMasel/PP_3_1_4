package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void add(User user);

    void edit(Long id, User user);

    void delete(Long id);

    User getById(Long id);

    User getUserByUsername(String userName);

    UserDetails loadUserByUsername(String username);
}
