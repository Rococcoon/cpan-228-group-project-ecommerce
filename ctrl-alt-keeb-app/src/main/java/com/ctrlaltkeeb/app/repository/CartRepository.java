package com.ctrlaltkeeb.app.repository;

import com.ctrlaltkeeb.app.model.Cart;
import com.ctrlaltkeeb.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

}