package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.User;
import com.agrios.agriosbackend.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Farm addFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public List<Farm> getFarmsByOwner(User owner) {
        return farmRepository.findByOwner(owner);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));
    }

    public Farm updateFarm(Long id, Farm updatedFarm) {

        Farm farm = getFarmById(id);

        farm.setFarmName(updatedFarm.getFarmName());
        farm.setLocation(updatedFarm.getLocation());
        farm.setArea(updatedFarm.getArea());
        farm.setSoilType(updatedFarm.getSoilType());
        farm.setIrrigationType(updatedFarm.getIrrigationType());

        return farmRepository.save(farm);
    }

    public void deleteFarm(Long id) {
        Farm farm = getFarmById(id);
        farmRepository.delete(farm);
    }
}
