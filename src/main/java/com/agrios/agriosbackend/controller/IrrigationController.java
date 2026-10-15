package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.IrrigationRequest;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.IrrigationRecord;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.IrrigationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irrigation")
@CrossOrigin(origins = "http://localhost:5173")
public class IrrigationController {

    private final IrrigationService irrigationService;
    private final FarmRepository farmRepository;

    public IrrigationController(
            IrrigationService irrigationService,
            FarmRepository farmRepository) {
        this.irrigationService = irrigationService;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public IrrigationRecord add(@RequestBody IrrigationRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        IrrigationRecord record = new IrrigationRecord(
                request.getIrrigationDate(),
                request.getWaterQuantity(),
                request.getMethod(),
                request.getNotes(),
                farm
        );

        return irrigationService.add(record);
    }

    @GetMapping
    public List<IrrigationRecord> getAll() {
        return irrigationService.getAll();
    }

    @GetMapping("/{id}")
    public IrrigationRecord getById(@PathVariable Long id) {
        return irrigationService.getById(id);
    }

    @PutMapping("/{id}")
    public IrrigationRecord update(
            @PathVariable Long id,
            @RequestBody IrrigationRequest request) {

        IrrigationRecord updated = new IrrigationRecord();

        updated.setIrrigationDate(request.getIrrigationDate());
        updated.setWaterQuantity(request.getWaterQuantity());
        updated.setMethod(request.getMethod());
        updated.setNotes(request.getNotes());

        return irrigationService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        irrigationService.delete(id);
        return "Irrigation record deleted successfully";
    }
}
 