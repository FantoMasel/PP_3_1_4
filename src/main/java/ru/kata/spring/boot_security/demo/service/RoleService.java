package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleService {
    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void addRole(Role role);
}
