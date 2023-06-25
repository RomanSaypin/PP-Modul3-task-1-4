package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
private final UserService userService;
private final RoleRepository roleRepository;
    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/allUser")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "AllUsers";
    }
    @GetMapping("/addNewUser")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "user-info";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUsers(user);
        return "redirect:/admin/allUser";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userService.getUser(id);
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "edit";
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
