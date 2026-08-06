package com.ctrlaltkeeb.app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public Cart() {
  }

  public Cart(User user) {
    this.user = user;
  }

  public void addItem(Product product, int quantity) {

    for (CartItem item : items) {

      if (item.getProduct().getId()
          .equals(product.getId())) {

        item.setQuantity(
            item.getQuantity() + quantity);

        return;
      }
    }

    CartItem newItem = new CartItem(
        this,
        product,
        quantity);

    items.add(newItem);
  }

  public void removeItem(Long itemId) {

    items.removeIf(
        item -> item.getId()
            .equals(itemId));

  }

  public BigDecimal getTotal() {

    return items.stream()
        .map(item -> item.getProduct()
            .getPrice()
            .multiply(
                BigDecimal.valueOf(
                    item.getQuantity())))
        .reduce(
            BigDecimal.ZERO,
            BigDecimal::add);

  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

}