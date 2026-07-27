package com.ctrlaltkeeb.app.controller.shop;

import java.util.Set;

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

  private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
      "name",
      "price",
      "stock",
      "keyCount",
      "createdAt");

  private final ProductService productService;

  public ShopController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/shop")
  public String shop(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "6") int size,
      @RequestParam(defaultValue = "name") String sort,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "") String layout,
      @RequestParam(defaultValue = "0") Integer keyCount,
      Model model) {

    int safePage = Math.max(page, 0);
    int safeSize = Math.min(Math.max(size, 1), 24);

    String safeSort = ALLOWED_SORT_FIELDS.contains(sort)
        ? sort
        : "name";

    Sort.Direction safeDirection;

    try {
      safeDirection = Sort.Direction.fromString(direction);
    } catch (IllegalArgumentException exception) {
      safeDirection = Sort.Direction.ASC;
    }

    PageRequest pageable = PageRequest.of(
        safePage,
        safeSize,
        Sort.by(safeDirection, safeSort));

    boolean hasLayout =
        layout != null && !layout.isBlank();

    boolean hasKeyCount =
        keyCount != null && keyCount > 0;

    Page<Product> productPage;

    if (hasLayout && hasKeyCount) {
      productPage =
          productService.getProductsByLayoutAndKeyCount(
              layout,
              keyCount,
              pageable);
    } else if (hasLayout) {
      productPage =
          productService.getProductsByLayout(
              layout,
              pageable);
    } else if (hasKeyCount) {
      productPage =
          productService.getProductsByKeyCount(
              keyCount,
              pageable);
    } else {
      productPage =
          productService.getAllProducts(pageable);
    }

    model.addAttribute("products", productPage);
    model.addAttribute("layout", layout);
    model.addAttribute("keyCount", keyCount);
    model.addAttribute("sort", safeSort);
    model.addAttribute(
        "direction",
        safeDirection.name());

    return "shop/shop";
  }
}