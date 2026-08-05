package com.ctrlaltkeeb.app.controller.shop;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctrlaltkeeb.app.model.Product;
import com.ctrlaltkeeb.app.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

  private static final List<String> LAYOUT_OPTIONS = List.of(
      "Advantage",
      "Chocofi",
      "Corne",
      "Ergodox",
      "Helix",
      "Keyball",
      "Kyria",
      "Let's Split",
      "Lily58",
      "Lulu",
      "Moonlander",
      "Nyquist",
      "Piantor",
      "Preonic",
      "Redox",
      "Sofle",
      "Sweep",
      "Totem",
      "Voyager");

  private static final List<Integer> KEY_COUNT_OPTIONS = List.of(
      34,
      36,
      42,
      48,
      50,
      52,
      58,
      60,
      61,
      64,
      72,
      76,
      80);

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/shop/{id}")
  public String product(
      @PathVariable Long id,
      Model model) {

    Product product = productService.getProductById(id)
        .orElseThrow(() ->
            new IllegalArgumentException(
                "Product not found with ID: " + id));

    model.addAttribute("product", product);

    return "shop/product";
  }

  @GetMapping("/products/new")
  public String showCreateProductForm(Model model) {

    model.addAttribute("product", new Product());
    addFormOptions(model);

    return "shop/product-form";
  }

  @PostMapping("/products")
  public String createProduct(
      @Valid @ModelAttribute("product") Product product,
      BindingResult bindingResult,
      Model model) {

    if (product.getLayout() != null
        && !product.getLayout().isBlank()
        && !LAYOUT_OPTIONS.contains(product.getLayout())) {

      bindingResult.rejectValue(
          "layout",
          "invalid.layout",
          "Please select a valid keyboard layout.");
    }

    if (product.getKeyCount() != null
        && !KEY_COUNT_OPTIONS.contains(product.getKeyCount())) {

      bindingResult.rejectValue(
          "keyCount",
          "invalid.keyCount",
          "Please select a valid number of keys.");
    }

    if (bindingResult.hasErrors()) {
      addFormOptions(model);
      return "shop/product-form";
    }

    Product savedProduct = productService.saveProduct(product);

    return "redirect:/shop/"
        + savedProduct.getId()
        + "?created=true";
  }

  private void addFormOptions(Model model) {
    model.addAttribute("layoutOptions", LAYOUT_OPTIONS);
    model.addAttribute("keyCountOptions", KEY_COUNT_OPTIONS);
  }
}