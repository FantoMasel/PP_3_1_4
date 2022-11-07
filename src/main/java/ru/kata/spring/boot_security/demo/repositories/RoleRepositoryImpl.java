package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.find(Role.class, name);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r ", Role.class)
                .getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);

    }
}
