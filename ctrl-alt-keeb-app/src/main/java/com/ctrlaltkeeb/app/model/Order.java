package com.ctrlaltkeeb.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull(message = "Total amount is required.")
  @Column(nullable = false)
  private BigDecimal totalAmount;

  @NotBlank(message = "Shipping address is required.")
  @Column(nullable = false)
  private String shippingAddress;

  @NotBlank(message = "Phone number is required.")
  @Column(nullable = false)
  private String phoneNumber;

  @Column(name = "order_date", nullable = false, updatable = false)
  private LocalDateTime orderDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  public Order() {
  }

  public Order(User user, BigDecimal totalAmount, String shippingAddress, String phoneNumber) {
    this.user = user;
    this.totalAmount = totalAmount;
    this.shippingAddress = shippingAddress;
    this.phoneNumber = phoneNumber;
  }

  @PrePersist
  public void setOrderDateTimestamp() {
    if (orderDate == null) {
      orderDate = LocalDateTime.now();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public void addItem(OrderItem item) {
    items.add(item);
    item.setOrder(this);
  }
}
