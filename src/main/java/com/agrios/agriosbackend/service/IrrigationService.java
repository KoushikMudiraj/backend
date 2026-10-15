package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.IrrigationRecord;
import com.agrios.agriosbackend.repository.IrrigationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationService {

    private final IrrigationRepository irrigationRepository;

    public IrrigationService(IrrigationRepository irrigationRepository) {
        this.irrigationRepository = irrigationRepository;
    }

    public IrrigationRecord add(IrrigationRecord record) {
        return irrigationRepository.save(record);
    }

    public List<IrrigationRecord> getAll() {
        return irrigationRepository.findAll();
    }

    public IrrigationRecord getById(Long id) {
        return irrigationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Irrigation record not found"));
    }

    public IrrigationRecord update(Long id,
                                   IrrigationRecord updated) {

        IrrigationRecord record = getById(id);

        record.setIrrigationDate(updated.getIrrigationDate());
        record.setWaterQuantity(updated.getWaterQuantity());
        record.setMethod(updated.getMethod());
        record.setNotes(updated.getNotes());

        return irrigationRepository.save(record);
    }

    public void delete(Long id) {
        irrigationRepository.delete(getById(id));
    }
}