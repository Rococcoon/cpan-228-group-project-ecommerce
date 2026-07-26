package com.ctrlaltkeeb.app.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

  @GetMapping("/shop/cart")
  public String cart() {
    return "shop/cart";
  }
}
