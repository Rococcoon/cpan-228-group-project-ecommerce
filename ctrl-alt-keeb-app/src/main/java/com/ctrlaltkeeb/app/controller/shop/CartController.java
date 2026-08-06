package com.ctrlaltkeeb.app.controller.shop;

import com.ctrlaltkeeb.app.model.Cart;
import com.ctrlaltkeeb.app.model.CartItem;
import com.ctrlaltkeeb.app.model.Product;
import com.ctrlaltkeeb.app.model.User;

import com.ctrlaltkeeb.app.repository.CartRepository;
import com.ctrlaltkeeb.app.repository.ProductRepository;
import com.ctrlaltkeeb.app.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  public CartController(
      CartRepository cartRepository,
      ProductRepository productRepository,
      UserRepository userRepository) {

    this.cartRepository = cartRepository;
    this.productRepository = productRepository;
    this.userRepository = userRepository;
  }

  private User getCurrentUser(Authentication authentication) {

    return userRepository
        .findByUsername(authentication.getName())
        .orElseThrow();
  }

  @GetMapping("/shop/cart")
  public String cart(
      Authentication authentication,
      Model model) {

    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseGet(() -> cartRepository.save(
            new Cart(user)));

    model.addAttribute("cart", cart);

    return "shop/cart";
  }

  @PostMapping("/shop/cart/add/{id}")
  public String addToCart(
      @PathVariable Long id,
      Authentication authentication) {

    User user = getCurrentUser(authentication);

    Product product = productRepository.findById(id)
        .orElseThrow();

    Cart cart = cartRepository.findByUser(user)
        .orElseGet(() -> new Cart(user));

    cart.addItem(product, 1);

    cartRepository.save(cart);

    return "redirect:/shop/cart";
  }

  @PostMapping("/shop/cart/remove/{id}")
  public String remove(
      @PathVariable Long id,
      Authentication authentication) {

    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseThrow();

    cart.removeItem(id);

    cartRepository.save(cart);

    return "redirect:/shop/cart";
  }

  @PostMapping("/shop/cart/increase/{id}")
  public String increase(
      @PathVariable Long id,
      Authentication authentication) {

    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseThrow();

    for (CartItem item : cart.getItems()) {

      if (item.getId().equals(id)) {

        item.setQuantity(
            item.getQuantity() + 1);

      }

    }

    cartRepository.save(cart);

    return "redirect:/shop/cart";
  }

  @PostMapping("/shop/cart/decrease/{id}")
  public String decrease(
      @PathVariable Long id,
      Authentication authentication) {

    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseThrow();

    for (CartItem item : cart.getItems()) {

      if (item.getId().equals(id)) {

        if (item.getQuantity() > 1) {

          item.setQuantity(
              item.getQuantity() - 1);

        }

      }

    }

    cartRepository.save(cart);

    return "redirect:/shop/cart";
  }
}