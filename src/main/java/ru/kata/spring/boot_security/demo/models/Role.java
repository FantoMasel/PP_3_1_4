package ru.kata.spring.boot_security.demo.models;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Отсутствует роль")
    @UniqueElements
    @Column(name = "role_name", unique = true)
    private String roleName;
    @Transient
    Set<User> userSet;

    public Role() {

    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public String getAuthority() {
        return this.getRoleName();
    }

    @Override
    public String toString() {
        return this.getRoleName();
    }

    public String roleNameToString() {
        return roleName.replaceAll("ROLE_", "");
    }
}
