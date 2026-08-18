package com.ctrlaltkeeb.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrderRequest {

  @NotBlank(message = "Shipping address is required.")
  @Size(max = 255, message = "Shipping address must not exceed 255 characters.")
  private String shippingAddress;

  @NotBlank(message = "Phone number is required.")
  @Size(max = 30, message = "Phone number must not exceed 30 characters.")
  private String phoneNumber;

  public OrderRequest() {
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
}
