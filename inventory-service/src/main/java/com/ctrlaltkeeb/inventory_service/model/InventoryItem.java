package com.ctrlaltkeeb.inventory_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InventoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long productId;
  private int quantity;
  private LocalDateTime lastUpdated;

  public InventoryItem() {
  }

  public InventoryItem(Long productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
    this.lastUpdated = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }
}
