package ru.kata.spring.boot_security_bootstrap.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security_bootstrap.demo.entity.User;
import ru.kata.spring.boot_security_bootstrap.demo.service.RoleService;
import ru.kata.spring.boot_security_bootstrap.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/allUser")
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userAdmin", userService.findByUsername(principal.getName()));
        model.addAttribute("listRole", roleService.findAll());
        return "admin";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("userAdmin") User user) {
        userService.saveUsers(user);
        return "redirect:/admin/allUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/allUser";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/allUser";
    }
}
