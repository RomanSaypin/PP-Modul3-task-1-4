package ru.kata.spring.boot_security_bootstrap.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    @Column(name = "name", unique = true)
    private String role;

    private static final String ROLE_PREFIX = "ROLE_";

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return ROLE_PREFIX + role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
