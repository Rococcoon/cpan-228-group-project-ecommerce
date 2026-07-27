package com.ctrlaltkeeb.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Product name is required.")
  @Size(
      max = 100,
      message = "Product name must not exceed 100 characters.")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "Description is required.")
  @Size(
      max = 1000,
      message = "Description must not exceed 1000 characters.")
  @Column(nullable = false, length = 1000)
  private String description;

  @NotNull(message = "Price is required.")
  @DecimalMin(
      value = "1.00",
      message = "Price must be at least $1.00.")
  @DecimalMax(
      value = "5000.00",
      message = "Price must not exceed $5,000.00.")
  @Column(nullable = false)
  private BigDecimal price;

  @NotBlank(message = "Layout is required.")
  @Column(nullable = false)
  private String layout;

  @NotNull(message = "Key count is required.")
  @Positive(message = "Key count must be greater than zero.")
  @Max(
      value = 120,
      message = "Key count must not exceed 120.")
  @Column(nullable = false)
  private Integer keyCount;

  @NotNull(message = "Stock quantity is required.")
  @PositiveOrZero(message = "Stock cannot be negative.")
  @Max(
      value = 10000,
      message = "Stock must not exceed 10,000.")
  @Column(nullable = false)
  private Integer stock;

  @Size(
      max = 255,
      message = "Image URL must not exceed 255 characters.")
  private String imageUrl;

  /*
   * The database default allows data.sql to omit this column.
   * @PrePersist ensures products added through the form also receive
   * a timestamp before being saved.
   */
  @Column(
      name = "created_at",
      nullable = false,
      updatable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  public Product() {
  }

  public Product(
      String name,
      String description,
      BigDecimal price,
      String layout,
      Integer keyCount,
      Integer stock,
      String imageUrl) {

    this.name = name;
    this.description = description;
    this.price = price;
    this.layout = layout;
    this.keyCount = keyCount;
    this.stock = stock;
    this.imageUrl = imageUrl;
  }

  @PrePersist
  public void setCreationTimestamp() {
    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getLayout() {
    return layout;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public Integer getKeyCount() {
    return keyCount;
  }

  public void setKeyCount(Integer keyCount) {
    this.keyCount = keyCount;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", layout='" + layout + '\'' +
        ", keyCount=" + keyCount +
        ", price=" + price +
        ", stock=" + stock +
        ", createdAt=" + createdAt +
        '}';
  }
}