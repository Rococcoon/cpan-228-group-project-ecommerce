package com.ctrlaltkeeb.inventory_service.controller;

import com.ctrlaltkeeb.inventory_service.model.InventoryItem;
import com.ctrlaltkeeb.inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  private final InventoryService service;

  public InventoryController(InventoryService service) {
    this.service = service;
  }

  @GetMapping
  public List<InventoryItem> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public InventoryItem getById(@PathVariable Long id) {
    return service.getById(id).orElse(null);
  }

  @PostMapping
  public InventoryItem create(@RequestBody InventoryItem item) {
    return service.save(item);
  }

  @PutMapping("/{id}")
  public InventoryItem update(
      @PathVariable Long id,
      @RequestBody InventoryItem item) {

    InventoryItem existing = service.getById(id).orElse(null);

    if (existing == null) {
      return null;
    }

    existing.setProductId(item.getProductId());
    existing.setQuantity(item.getQuantity());

    return service.save(existing);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  @GetMapping("/search")
  public List<InventoryItem> search(
      @RequestParam Long productId,
      @RequestParam int quantity) {

    return service.search(productId, quantity);
  }
}
