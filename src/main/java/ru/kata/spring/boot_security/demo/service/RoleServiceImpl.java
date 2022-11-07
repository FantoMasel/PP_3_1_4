package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Repository
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.addRole(role);
    }
}
