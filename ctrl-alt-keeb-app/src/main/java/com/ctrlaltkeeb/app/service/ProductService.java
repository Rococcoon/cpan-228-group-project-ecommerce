package com.ctrlaltkeeb.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ctrlaltkeeb.app.model.Product;
import com.ctrlaltkeeb.app.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Page<Product> getAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Page<Product> getProductsByLayout(
      String layout,
      Pageable pageable) {
    return productRepository.findByLayout(layout, pageable);
  }

  public Page<Product> getProductsByKeyCount(
      Integer keyCount,
      Pageable pageable) {
    return productRepository.findByKeyCount(keyCount, pageable);
  }

  public Page<Product> getProductsByLayoutAndKeyCount(
      String layout,
      Integer keyCount,
      Pageable pageable) {
    return productRepository.findByLayoutAndKeyCount(
        layout,
        keyCount,
        pageable);
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
