package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.CropRequest;
import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.CropService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@CrossOrigin(origins = "http://localhost:5173")
public class CropController {

    private final CropService cropService;
    private final FarmRepository farmRepository;

    public CropController(CropService cropService,
                          FarmRepository farmRepository) {
        this.cropService = cropService;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public Crop addCrop(@RequestBody CropRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        Crop crop = new Crop(
                request.getCropName(),
                request.getVariety(),
                request.getSeason(),
                request.getPlantingDate(),
                request.getExpectedHarvestDate(),
                request.getStatus(),
                farm
        );

        return cropService.addCrop(crop);
    }

    @GetMapping
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/farm/{farmId}")
    public List<Crop> getCropsByFarm(@PathVariable Long farmId) {

        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        return cropService.getCropsByFarm(farm);
    }

    @GetMapping("/{id}")
    public Crop getCrop(@PathVariable Long id) {
        return cropService.getCropById(id);
    }

    @PutMapping("/{id}")
    public Crop updateCrop(
            @PathVariable Long id,
            @RequestBody CropRequest request) {

        Crop updatedCrop = new Crop();

        updatedCrop.setCropName(request.getCropName());
        updatedCrop.setVariety(request.getVariety());
        updatedCrop.setSeason(request.getSeason());
        updatedCrop.setPlantingDate(request.getPlantingDate());
        updatedCrop.setExpectedHarvestDate(
                request.getExpectedHarvestDate()
        );
        updatedCrop.setStatus(request.getStatus());

        return cropService.updateCrop(id, updatedCrop);
    }

    @DeleteMapping("/{id}")
    public String deleteCrop(@PathVariable Long id) {

        cropService.deleteCrop(id);

        return "Crop deleted successfully";
    }
}
