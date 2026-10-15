package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByFarm(Farm farm);
}
