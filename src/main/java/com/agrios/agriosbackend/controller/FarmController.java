package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.FarmRequest;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.User;
import com.agrios.agriosbackend.entity.Role;
import com.agrios.agriosbackend.repository.UserRepository;
import com.agrios.agriosbackend.service.FarmService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@CrossOrigin(origins = "http://localhost:5173")
public class FarmController {

    private final FarmService farmService;
    private final UserRepository userRepository;

    public FarmController(FarmService farmService,
                          UserRepository userRepository) {
        this.farmService = farmService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Farm addFarm(@RequestBody FarmRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Farm farm = new Farm(
                request.getFarmName(),
                request.getLocation(),
                request.getArea(),
                request.getSoilType(),
                request.getIrrigationType(),
                owner
        );

        return farmService.addFarm(farm);
    }

    @GetMapping
    public List<Farm> getMyFarms() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (owner.getRole() == Role.ADMIN || owner.getRole() == Role.OFFICER) {
            return farmService.getAllFarms();
        }

        List<Farm> myFarms = farmService.getFarmsByOwner(owner);
        if (myFarms == null || myFarms.isEmpty()) {
            // Fallback: If this farmer has no registered farms yet, return all available platform farms
            return farmService.getAllFarms();
        }

        return myFarms;
    }

    @GetMapping("/{id}")
    public Farm getFarm(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PutMapping("/{id}")
    public Farm updateFarm(
            @PathVariable Long id,
            @RequestBody FarmRequest request) {

        Farm updatedFarm = new Farm();

        updatedFarm.setFarmName(request.getFarmName());
        updatedFarm.setLocation(request.getLocation());
        updatedFarm.setArea(request.getArea());
        updatedFarm.setSoilType(request.getSoilType());
        updatedFarm.setIrrigationType(request.getIrrigationType());

        return farmService.updateFarm(id, updatedFarm);
    }

    @DeleteMapping("/{id}")
    public String deleteFarm(@PathVariable Long id) {

        farmService.deleteFarm(id);

        return "Farm deleted successfully";
    }
}