package com.ctrlaltkeeb.app.controller.shop;

import com.ctrlaltkeeb.app.model.Cart;
import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.repository.CartRepository;
import com.ctrlaltkeeb.app.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

  private final CartRepository cartRepository;
  private final UserRepository userRepository;

  public CheckoutController(
      CartRepository cartRepository,
      UserRepository userRepository) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
  }

  private User getCurrentUser(Authentication authentication) {
    return userRepository
        .findByUsername(authentication.getName())
        .orElseThrow();
  }

  @GetMapping("/shop/checkout")
  public String checkout(Authentication authentication, Model model) {
    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseGet(() -> cartRepository.save(new Cart(user)));

    model.addAttribute("cart", cart);

    return "shop/checkout";
  }
}
