package ru.kata.spring.boot_security_bootstrap.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security_bootstrap.demo.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
