package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.repository.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public List<Crop> getCropsByFarm(Farm farm) {
        return cropRepository.findByFarm(farm);
    }

    public Crop getCropById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));
    }

    public Crop updateCrop(Long id, Crop updatedCrop) {

        Crop crop = getCropById(id);

        crop.setCropName(updatedCrop.getCropName());
        crop.setVariety(updatedCrop.getVariety());
        crop.setSeason(updatedCrop.getSeason());
        crop.setPlantingDate(updatedCrop.getPlantingDate());
        crop.setExpectedHarvestDate(updatedCrop.getExpectedHarvestDate());
        crop.setStatus(updatedCrop.getStatus());

        return cropRepository.save(crop);
    }

    public void deleteCrop(Long id) {
        Crop crop = getCropById(id);
        cropRepository.delete(crop);
    }
}