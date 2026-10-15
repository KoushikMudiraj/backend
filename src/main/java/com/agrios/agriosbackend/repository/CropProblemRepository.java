package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.CropProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropProblemRepository
        extends JpaRepository<CropProblem, Long> {
}
