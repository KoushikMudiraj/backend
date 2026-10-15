package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.IrrigationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IrrigationRepository
        extends JpaRepository<IrrigationRecord, Long> {

    List<IrrigationRecord> findByFarm(Farm farm);
}