package com.ctrlaltkeeb.app.controller.shop;

import com.ctrlaltkeeb.app.model.Cart;
import com.ctrlaltkeeb.app.model.CartItem;
import com.ctrlaltkeeb.app.model.Order;
import com.ctrlaltkeeb.app.model.OrderItem;
import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.repository.CartRepository;
import com.ctrlaltkeeb.app.repository.OrderRepository;
import com.ctrlaltkeeb.app.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class CheckoutController {

  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  public CheckoutController(
      CartRepository cartRepository,
      UserRepository userRepository,
      OrderRepository orderRepository) {

    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
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

  @PostMapping("/shop/order/place")
  public String placeOrder(
      Authentication authentication,
      @RequestParam String shippingAddress,
      @RequestParam String phoneNumber,
      RedirectAttributes redirectAttributes) {

    User user = getCurrentUser(authentication);

    Cart cart = cartRepository.findByUser(user)
        .orElseThrow();

    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      redirectAttributes.addFlashAttribute(
          "error",
          "Your cart is empty.");
      return "redirect:/shop/cart";
    }

    BigDecimal total = cart.getTotal();

    Order order = new Order(
        user,
        total,
        shippingAddress,
        phoneNumber);

    for (CartItem cartItem : cart.getItems()) {

      OrderItem orderItem = new OrderItem(
          order,
          cartItem.getProduct(),
          cartItem.getQuantity(),
          cartItem.getProduct().getPrice());

      order.addItem(orderItem);
    }

    orderRepository.save(order);

    cart.getItems().clear();
    cartRepository.save(cart);

    redirectAttributes.addFlashAttribute(
        "success",
        "Order placed successfully!");

    return "redirect:/shop";
  }
}