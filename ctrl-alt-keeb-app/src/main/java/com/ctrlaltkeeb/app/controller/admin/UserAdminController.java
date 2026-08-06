package com.ctrlaltkeeb.app.controller.admin;

import com.ctrlaltkeeb.app.model.Cart;
import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.repository.CartRepository;
import com.ctrlaltkeeb.app.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserAdminController(UserRepository userRepository,
            CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    // Show edit page
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);

        return "admin/edit-user";
    }

    // Save edited user
    @PostMapping("/edit/{id}")
    public String updateUser(
            @PathVariable Long id,
            @ModelAttribute User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);

        return "redirect:/admin/users";
    }

    // Delete user
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Delete cart linked to this user
        cartRepository.findByUser(user).ifPresent(cart -> {
            cartRepository.delete(cart);
        });

        // Delete user
        userRepository.delete(user);

        return "redirect:/admin/users";
    }
}