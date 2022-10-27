package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> getAllUsers();

    void add(User user);

    void edit(Long id, User user);

    void delete(Long id);

    User getById(Long id);

    User getUserByUsername(String userName);

}
