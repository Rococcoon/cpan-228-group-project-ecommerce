package com.ctrlaltkeeb.inventory_service.repository;

import com.ctrlaltkeeb.inventory_service.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
