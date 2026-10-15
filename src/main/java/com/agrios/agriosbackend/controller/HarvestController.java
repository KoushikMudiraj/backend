package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.HarvestRequest;
import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.entity.Harvest;
import com.agrios.agriosbackend.repository.CropRepository;
import com.agrios.agriosbackend.service.HarvestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@CrossOrigin(origins = "http://localhost:5173")
public class HarvestController {

    private final HarvestService service;
    private final CropRepository cropRepository;

    public HarvestController(HarvestService service,
                             CropRepository cropRepository) {
        this.service = service;
        this.cropRepository = cropRepository;
    }

    @PostMapping
    public Harvest add(@RequestBody HarvestRequest request) {

        Crop crop = cropRepository.findById(request.getCropId())
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        Harvest harvest = new Harvest(
                request.getHarvestDate(),
                request.getQuantity(),
                request.getUnit(),
                request.getRevenue(),
                request.getQuality(),
                crop
        );

        return service.add(harvest);
    }

    @GetMapping
    public List<Harvest> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Harvest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Harvest update(
            @PathVariable Long id,
            @RequestBody HarvestRequest request) {

        Harvest harvest = new Harvest();

        harvest.setHarvestDate(request.getHarvestDate());
        harvest.setQuantity(request.getQuantity());
        harvest.setUnit(request.getUnit());
        harvest.setRevenue(request.getRevenue());
        harvest.setQuality(request.getQuality());

        return service.update(id, harvest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Harvest record deleted successfully";
    }
}
