package ru.katacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.katacademy.service.UserService;
import ru.katacademy.model.User;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(ModelMap model) {
        List<User> usr = userService.listUsers();
        System.out.println("CONTROLLER" + Arrays.toString(usr.toArray()));

        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PutMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

}