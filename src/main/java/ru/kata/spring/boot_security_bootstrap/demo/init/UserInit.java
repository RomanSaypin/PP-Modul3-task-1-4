package ru.kata.spring.boot_security_bootstrap.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security_bootstrap.demo.entity.Role;
import ru.kata.spring.boot_security_bootstrap.demo.entity.User;
import ru.kata.spring.boot_security_bootstrap.demo.service.RoleService;
import ru.kata.spring.boot_security_bootstrap.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class UserInit {
    private final UserService userService;

    public UserInit(UserService userService) {
        this.userService = userService;

    }

    @PostConstruct
    private void postConstruct() {
        User adminUser = new User("admin", "admin", "admin@mail.ru", "100");
        User userUser = new User("user", "user", "user@mail.ru", "101");
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");
        adminUser.addRole(admin);
        userUser.addRole(user);
        userService.saveUsers(adminUser);
        userService.saveUsers(userUser);
    }

}
