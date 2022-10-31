package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("usersList", userService.getAllUsers());
        String name = principal.getName();
        User user = userService.getUserByUsername(name);
        model.addAttribute("user", user);
        model.addAttribute("rolesList", user.getRoleSet());

        return "/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/admin";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false) String roles) {
        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleService.getRoleById(1L));
        if (roles.equals("2")) {
            roles1.add(roleService.getRoleById(2L));
        }
        user.setRoleSet(roles1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id, @RequestParam(required = false) String roles) {
        System.out.println("PIZDA");
        System.out.println(roles);
        System.out.println(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleService.getRoleById(1L));
        if (roles.equals("2")) {
            roles1.add(roleService.getRoleById(2L));
        }
        user.setRoleSet(roles1);
        userService.edit(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
