package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User")
                .getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public void edit(Long id, User user) {
        User newUser = entityManager.find(User.class, id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRoleSet(user.getRoleSet());

    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String userName) {
        User user = entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roleSet WHERE u.email = :username", User.class)
                .setParameter("username", userName)
                .getSingleResult();

        return user;
    }
}
