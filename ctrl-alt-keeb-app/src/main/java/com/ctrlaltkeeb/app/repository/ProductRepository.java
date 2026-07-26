package com.ctrlaltkeeb.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ctrlaltkeeb.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByLayout(String layout);

  Page<Product> findByLayout(
      String layout,
      Pageable pageable);

  Page<Product> findByKeyCount(Integer keyCount, Pageable pageable);

  Page<Product> findByLayoutAndKeyCount(
      String layout,
      Integer keyCount,
      Pageable pageable);

}
