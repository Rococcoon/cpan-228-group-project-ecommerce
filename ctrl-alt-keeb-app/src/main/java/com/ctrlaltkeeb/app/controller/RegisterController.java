package com.ctrlaltkeeb.app.controller;

import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        userService.registerUser(
                username,
                email,
                password,
                "ROLE_CUSTOMER");

        return "redirect:/login?registered=true";
    }

}