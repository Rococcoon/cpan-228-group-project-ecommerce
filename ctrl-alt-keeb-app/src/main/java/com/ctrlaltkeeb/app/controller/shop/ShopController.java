package com.ctrlaltkeeb.app.controller.shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctrlaltkeeb.app.model.Product;
import com.ctrlaltkeeb.app.service.ProductService;

@Controller
public class ShopController {

  private final ProductService productService;

  public ShopController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/shop")
  public String shop(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "6") int size,
      @RequestParam(defaultValue = "price") String sort,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "") String layout,
      @RequestParam(defaultValue = "0") Integer keyCount,
      @RequestParam(defaultValue = "all") String filterType,
      Model model) {

    Sort.Direction sortDirection;

    try {
      sortDirection = Sort.Direction.fromString(direction);
    } catch (IllegalArgumentException e) {
      sortDirection = Sort.Direction.ASC;
      direction = "ASC";
    }

    PageRequest pageable = PageRequest.of(
        page,
        size,
        Sort.by(sortDirection, sort));

    Page<Product> productPage = switch (filterType) {

      case "layout" ->
        productService.getProductsByLayout(
            layout,
            pageable);

      case "keyCount" ->
        productService.getProductsByKeyCount(
            keyCount,
            pageable);

      case "layoutAndKeyCount" ->
        productService.getProductsByLayoutAndKeyCount(
            layout,
            keyCount,
            pageable);

      default ->
        productService.getAllProducts(pageable);
    };

    model.addAttribute("products", productPage);

    model.addAttribute("layout", layout);
    model.addAttribute("keyCount", keyCount);
    model.addAttribute("filterType", filterType);
    model.addAttribute("sort", sort);
    model.addAttribute("direction", direction);

    return "shop/shop";
  }
}
