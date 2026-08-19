package com.ctrlaltkeeb.inventory_service.service;

import com.ctrlaltkeeb.inventory_service.model.InventoryItem;
import com.ctrlaltkeeb.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

  private final InventoryRepository repository;

  public InventoryService(InventoryRepository repository) {
    this.repository = repository;
  }

  public List<InventoryItem> getAll() {
    return repository.findAll();
  }

  public Optional<InventoryItem> getById(Long id) {
    return repository.findById(id);
  }

  public InventoryItem save(InventoryItem item) {
    return repository.save(item);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public List<InventoryItem> search(Long productId, int quantity) {
    return repository.findByProductIdAndQuantity(productId, quantity);
  }

}
