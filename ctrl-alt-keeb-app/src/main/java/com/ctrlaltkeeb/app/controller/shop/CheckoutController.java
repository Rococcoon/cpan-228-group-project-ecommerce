package com.ctrlaltkeeb.app.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

  @GetMapping("/shop/checkout")
  public String checkout() {
    return "shop/checkout";
  }
}
