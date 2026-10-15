package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.InventoryRequest;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.Inventory;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryController {

    private final InventoryService service;
    private final FarmRepository farmRepository;

    public InventoryController(InventoryService service,
                                FarmRepository farmRepository) {
        this.service = service;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public Inventory add(@RequestBody InventoryRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        Inventory item = new Inventory(
                request.getItemName(),
                request.getCategory(),
                request.getQuantity(),
                request.getUnit(),
                request.getCost(),
                farm
        );

        return service.add(item);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable Long id,
                            @RequestBody InventoryRequest request) {

        Inventory item = new Inventory();

        item.setItemName(request.getItemName());
        item.setCategory(request.getCategory());
        item.setQuantity(request.getQuantity());
        item.setUnit(request.getUnit());
        item.setCost(request.getCost());

        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Inventory item deleted successfully";
    }
}
