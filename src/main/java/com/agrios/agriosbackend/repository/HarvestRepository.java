package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
