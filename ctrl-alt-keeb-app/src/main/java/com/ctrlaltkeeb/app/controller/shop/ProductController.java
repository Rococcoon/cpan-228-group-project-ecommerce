package com.ctrlaltkeeb.app.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctrlaltkeeb.app.service.ProductService;

@Controller
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/shop/{id}")
  public String product(
      @PathVariable Long id,
      Model model) {

    model.addAttribute(
        "product",
        productService.getProductById(id)
            .orElseThrow());

    return "shop/product";
  }
}
