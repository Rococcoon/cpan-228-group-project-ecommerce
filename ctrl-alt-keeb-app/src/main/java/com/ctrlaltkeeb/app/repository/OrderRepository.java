package com.ctrlaltkeeb.app.repository;

import com.ctrlaltkeeb.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}