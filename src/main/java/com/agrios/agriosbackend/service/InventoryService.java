package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Inventory;
import com.agrios.agriosbackend.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory add(Inventory item) {
        return repository.save(item);
    }

    public List<Inventory> getAll() {
        return repository.findAll();
    }

    public Inventory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));
    }

    public Inventory update(Long id, Inventory updated) {
        Inventory item = getById(id);

        item.setItemName(updated.getItemName());
        item.setCategory(updated.getCategory());
        item.setQuantity(updated.getQuantity());
        item.setUnit(updated.getUnit());
        item.setCost(updated.getCost());

        return repository.save(item);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
